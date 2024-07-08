package com.gechan.todoapi.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Log4j2
@RequiredArgsConstructor
public class CustomFileUtil {

    @Value("${com.gechan.upload.path}")
    private String uploadPath;

    @PostConstruct
    public void init() {
        File tmpFolder = new File(uploadPath);

        // application.properties 에서 설정한 upload 폴더가 존재하지 않으면 생성
        if (!tmpFolder.exists()) {
            tmpFolder.mkdir();
        }

        // upload 폴더가 생성된 실제 경로
        uploadPath = tmpFolder.getAbsolutePath(); // 실제 디스크에 저장되는 경로

        log.debug("-----------------------------------");
        log.debug("업로드 경로 {}", uploadPath);
        log.debug("-----------------------------------");
    }

    public List<String> saveFiles(List<MultipartFile> files) throws RuntimeException {

        if (files == null || files.size() == 0) {
            return null;
        }

        List<String> uploadNames = new ArrayList<>();
        for (MultipartFile file : files) {
            // 파일명 중복 방지를 위해 UUID 생
            String saveName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

            // uploadPath 경로에 saveName 이름으로 파일을 만든다.
            Path savePath = Paths.get(uploadPath, saveName);

            try {
                Files.copy(file.getInputStream(), savePath);
                uploadNames.add(saveName);
            }
            catch (IOException e) {
                throw new RuntimeException();
            }

        }
        return null;
    }
}
