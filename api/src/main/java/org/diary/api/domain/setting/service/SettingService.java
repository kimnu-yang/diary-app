package org.diary.api.domain.setting.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.diary.api.common.error.ErrorCode;
import org.diary.api.common.exception.ApiException;
import org.diary.api.domain.setting.controller.model.CheckList;
import org.diary.api.domain.setting.controller.model.CheckMyArtList;
import org.diary.api.domain.setting.controller.model.CheckSyncData;
import org.diary.api.domain.setting.controller.model.DiaryWithColorAndTag;
import org.diary.db.MyArt.MyArtEntity;
import org.diary.db.MyArt.MyArtRepository;
import org.diary.db.diary.*;
import org.diary.db.setting.SettingEntity;
import org.diary.db.setting.SettingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Setting 도메인 로직을 처리하는 서비스
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SettingService {

    private final SettingRepository settingRepository;
    private final DiaryRepository diaryRepository;
    private final DiaryColorRepository diaryColorRepository;
    private final DiaryTagRepository diaryTagRepository;
    private final MyArtRepository myArtRepository;

    /**
     * 설정 등록 처리
     * 해당 유저에게 동일한 설정이 있는 경우 업데이트 처리
     *
     * @param settings - 설정 Entity 리스트
     */
    @Transactional
    public List<SettingEntity> setSettings(List<SettingEntity> settings) {
        return Optional.ofNullable(settings)
                .map(settingEntities -> {
                    // 기존 설정값 체크
                    for (SettingEntity entity : settingEntities) {
                        Optional<SettingEntity> check = settingRepository.findFirstByUserIdAndName(entity.getUserId(), entity.getName());
                        check.ifPresent(e -> entity.setId(e.getId()));

                        settingRepository.save(entity);
                    }

                    return settingEntities;
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "setting data null"));
    }

    /**
     * 설정 조회
     *
     * @param userId - 유저 ID
     * @return UserEntity
     */
    public List<SettingEntity> getSetting(Long userId) {
        return settingRepository.findByUserId(userId);
    }

    public Map<String, Object> checkSyncDate(Long userId, String lastSyncTime, CheckSyncData checkData) {
        List<DiaryEntity> savedData;
        List<MyArtEntity> artSavedData;

        if (!Objects.equals(lastSyncTime, "")) {
            // 기준 일자 있는 경우
            savedData = diaryRepository.findByUserIdAndCheckLastSyncTimeOrderById(userId, LocalDateTime.parse(lastSyncTime));
            artSavedData = myArtRepository.findByUserIdAndCheckLastSyncTimeOrderById(userId, LocalDateTime.parse(lastSyncTime));
        } else {
            // 기준 일자 없는 경우 전체 데이터
            savedData = diaryRepository.findByUserIdOrderById(userId);
            artSavedData = myArtRepository.findByUserIdOrderById(userId);
        }

        List<DiaryWithColorAndTag> downloadList = new ArrayList<>();
        List<MyArtEntity> myArtDownloadList = new ArrayList<>();
        List<Long> uploadList = new ArrayList<>();

        Map<String, Object> resultMap = new HashMap<>();

        Map<String, Object> dateMap = new HashMap<>();
        Map<String, CheckList> checkMap = new HashMap<>();
        Map<String, DiaryEntity> savedMap = new HashMap<>();


        Map<String, Object> artDateMap = new HashMap<>();
        Map<String, CheckMyArtList> artCheckMap = new HashMap<>();
        Map<String, MyArtEntity> artSavedMap = new HashMap<>();

        ///// 데이터 가공
        // diary 데이터 세팅
        for (CheckList data : checkData.getCheckLists()) {
            checkMap.put(data.getRegisteredAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), data);
            dateMap.put(data.getRegisteredAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), null);
        }
        for (DiaryEntity data : savedData) {
            savedMap.put(data.getRegisteredAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), data);
            dateMap.put(data.getRegisteredAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), null);
        }

        // my_art 데이터 세팅
        for (CheckMyArtList data : checkData.getCheckArtList()) {
            artCheckMap.put(data.getBaseDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), data);
            artDateMap.put(data.getBaseDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), null);
        }
        for (MyArtEntity data : artSavedData) {
            artSavedMap.put(data.getBaseDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), data);
            artDateMap.put(data.getBaseDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), null);
        }

        ////

        dateMap.forEach((date, value) -> {
            boolean isDownload = false;
            boolean isUpload = false;
            boolean isDelete = false;

            if (checkMap.containsKey(date) && savedMap.containsKey(date)) {
                // 앱, DB 둘 다 있는 경우
                if (checkMap.get(date).getDeletedAt() == null) {
                    // 앱에서 삭제된 데이터도 아닌 경우
                    if (checkMap.get(date).getRegisteredAt().isBefore(savedMap.get(date).getRegisteredAt())) {
                        // DB가 최신 데이터인 경우
                        isDownload = true;
                    } else if (savedMap.get(date).getRegisteredAt().isBefore(checkMap.get(date).getRegisteredAt())) {
                        // 앱이 최신 데이터인 경우
                        isUpload = true;
                    } else {
                        // 같은 경우는 이미 동기화가 되어있다고 판단
                        if (checkMap.get(date).getUpdatedAt() != null) {
                            if (checkMap.get(date).getUpdatedAt().isBefore(savedMap.get(date).getUpdatedAt())) {
                                // DB가 최신 데이터인 경우
                                isDownload = true;
                            } else if (savedMap.get(date).getUpdatedAt().isBefore(checkMap.get(date).getUpdatedAt())) {
                                // 앱이 최신 데이터인 경우
                                isUpload = true;
                            }
                        }
                    }
                } else {
                    // deleteAt이 존재하는 경우 앱에서는 삭제된 데이터 이므로 삭제 처리
                    isDelete = true;
                }
            } else if (checkMap.containsKey(date) && !savedMap.containsKey(date)) {
                // 앱에만 있는 데이터인 경우 (삭제된 날짜 비어있는 경우에만)
                // DB 저장 처리
                if (checkMap.get(date).getDeletedAt() == null) {
                    isUpload = true;
                } else {
                    // 삭제 날짜가 있는 경우 해당 날짜 일기 삭제 처리
                    isDelete = true;
                }
            } else if (!checkMap.containsKey(date) && savedMap.containsKey(date)) {
                // DB에만 있는 데이터인 경우
                // 삭제일자가 있기 때문에 DB에만 데이터가 있는 경우에는 다운로드
                isDownload = true;
            } else {
                // 둘 다 없는 경우??
                // 없을거라고 생각됨
                System.out.println("[동기화 오류]\r\nresultMap: " + resultMap + "\r\ncheckMap: " + checkMap + "\r\nsavedMap: " + savedMap);
                log.error("[동기화 오류]\r\nresultMap: " + resultMap + "\r\ncheckMap: " + checkMap + "\r\nsavedMap: " + savedMap);
            }

            // 업로드 리스트 Return
            if (isUpload) {
                uploadList.add(checkMap.get(date).getId());
            }

            // 다운로드 처리
            if (isDownload) {
                DiaryWithColorAndTag diaryWithColorAndTag = new DiaryWithColorAndTag();
                diaryWithColorAndTag.setDiary(savedMap.get(date));
                diaryWithColorAndTag.setColor(diaryColorRepository.findByUserIdAndDiaryId(userId, savedMap.get(date).getId()));
                diaryWithColorAndTag.setTag(diaryTagRepository.findByUserIdAndDiaryId(userId, savedMap.get(date).getId()));

                // 데이터 null 처리
                diaryWithColorAndTag.getDiary().setId(null);
                diaryWithColorAndTag.getDiary().setUserId(null);
                if (!diaryWithColorAndTag.getColor().isEmpty()) {
                    for (DiaryColorEntity color : diaryWithColorAndTag.getColor()) {
                        color.setId(null);
                        color.setDiaryId(null);
                    }
                }
                if (!diaryWithColorAndTag.getTag().isEmpty()) {
                    for (DiaryTagEntity tag : diaryWithColorAndTag.getTag()) {
                        tag.setId(null);
                        tag.setDiaryId(null);
                    }
                }

                downloadList.add(diaryWithColorAndTag);
            }

            // 삭제 처리
            if (isDelete) {
                DiaryEntity checkEntity = diaryRepository.findByUserIdAndRegisteredAtStartsWith(userId, date);

                if (checkEntity != null) {
                    diaryRepository.delete(checkEntity);
                    diaryColorRepository.deleteAll(diaryColorRepository.findByUserIdAndDiaryId(userId, checkEntity.getId()));
                    diaryTagRepository.deleteAll(diaryTagRepository.findByUserIdAndDiaryId(userId, checkEntity.getId()));
                }
            }

            System.out.println("[동기화 " + date + "] isUpload: " + isUpload + " isDownload: " + isDownload + " isDelete: " + isDelete);
        });

        // my_art db 비교
        artDateMap.forEach((date, value) -> {
            boolean isDownload = false;
            boolean isSave = false;

            if (artCheckMap.containsKey(date) && artSavedMap.containsKey(date)) {
                // 앱, DB 둘 다 있는 경우
                if (artCheckMap.get(date).getRegisteredAt().isBefore(artSavedMap.get(date).getRegisteredAt())) {
                    // DB가 최신 데이터인 경우
                    isDownload = true;
                } else if (artSavedMap.get(date).getRegisteredAt().isBefore(artCheckMap.get(date).getRegisteredAt())) {
                    // 앱이 최신 데이터인 경우
                    isSave = true;
                } // else 같은 경우는 이미 동기화가 되어있다고 판단
            } else if (artCheckMap.containsKey(date) && !artSavedMap.containsKey(date)) {
                // 앱에만 있는 데이터인 경우 (삭제된 날짜 비어있는 경우에만)
                // DB 저장 처리
                isSave = true;
            } else if (!artCheckMap.containsKey(date) && artSavedMap.containsKey(date)) {
                // DB에만 있는 데이터인 경우
                isDownload = true;
            } else {
                // 둘 다 없는 경우??
                // 없을거라고 생각됨
                System.out.println("[동기화 오류]\r\nresultMap: " + resultMap + "\r\nartCheckMap: " + artCheckMap + "\r\nartSavedMap: " + artSavedMap);
                log.error("[동기화 오류]\r\nresultMap: " + resultMap + "\r\nartCheckMap: " + artCheckMap + "\r\nartSavedMap: " + artSavedMap);
            }

            if (isSave) {
                // DB 저장 처리
                MyArtEntity myArtEntity = MyArtEntity.builder()
                        .id(myArtRepository.findIdByUserIdAndBaseDateStartsWith(userId, date))
                        .userId(userId)
                        .artId(artCheckMap.get(date).getArtId())
                        .registeredAt(artCheckMap.get(date).getRegisteredAt())
                        .baseDate(artCheckMap.get(date).getBaseDate())
                        .build();

                myArtRepository.save(myArtEntity);
            }

            // 다운로드 처리
            if (isDownload) {
                artSavedMap.get(date).setId(null);
                artSavedMap.get(date).setUserId(null);
                myArtDownloadList.add(artSavedMap.get(date));
            }

            System.out.println("[동기화 " + date + "] isSave: " + isSave + " isDownload: " + isDownload);
        });

        resultMap.put("downloadList", downloadList);
        resultMap.put("uploadList", uploadList);

        resultMap.put("myArtDownloadList", myArtDownloadList);

        return resultMap;
    }

    @Transactional
    public void saveDiaryWithColorAndTag(Long userId, List<DiaryWithColorAndTag> request) {
        for (DiaryWithColorAndTag it : request) {
            // 업로드 일자에 이미 작성한 일기가 있다면 해당 id 조회 후 업데이트 처리, 없다면 insert
            Long checkDiaryId = diaryRepository.findIdByUserIdAndRegisteredAtStartsWith(userId, it.getDiary().getRegisteredAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            it.getDiary().setId(checkDiaryId);
            it.getDiary().setUserId(userId);
            DiaryEntity diary = diaryRepository.save(it.getDiary());

            // 해당 일기로 작성된 태그, 색상 데이터 제거
            diaryTagRepository.deleteByUserIdAndDiaryId(userId, diary.getId());
            diaryColorRepository.deleteByUserIdAndDiaryId(userId, diary.getId());


            for (DiaryTagEntity tag : it.getTag()) {
                tag.setDiaryId(diary.getId());
                tag.setUserId(userId);
            }

            for (DiaryColorEntity color : it.getColor()) {
                color.setDiaryId(diary.getId());
                color.setUserId(userId);
            }

            diaryTagRepository.saveAll(it.getTag());
            diaryColorRepository.saveAll(it.getColor());
        }
    }
}
