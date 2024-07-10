package com.gechan.mallapi.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
            // 파일명 중복 방지를 위해 UUID 생성
            String saveName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            log.debug("saveName : {}", saveName);
            // uploadPath 경로에 saveName 이름으로 파일을 만든다.
            Path savePath = Paths.get(uploadPath, saveName);

            try {
                Files.copy(file.getInputStream(), savePath);

                String contentType = file.getContentType(); // Mime type

                // 이미지 파일이라면
                if (contentType != null && contentType.startsWith("image")) {
                    Path thumbnailPath = Paths.get(uploadPath, "s_" + saveName);

                    // 사이즈를 가로 200, 세로 200으로 맞춰서 저장
                    Thumbnails.of(savePath.toFile()).size(200, 200).toFile(thumbnailPath.toFile());
                }

                uploadNames.add(saveName);
            }
            catch (IOException e) {
                throw new RuntimeException();
            }

        }
        return uploadNames;
    }

    public ResponseEntity<Resource> getFile(String fileName) {

        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        if (!resource.isReadable()) {
            resource = new FileSystemResource(uploadPath + File.separator + "dogs.jpeg");
        }

        HttpHeaders headers = new HttpHeaders();
        try {
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok().headers(headers).body(resource);

    }

    public void deleteFiles(List<String> fileNames) {

        if(fileNames == null || fileNames.isEmpty()) return;

        fileNames.forEach(fileName -> {

            // thumbnail 삭제
            String thumbnailFileName = "s_" + fileName;
            Path thumbnailPath = Paths.get(uploadPath, thumbnailFileName);
            // 원본 삭제
            Path filePath = Paths.get(uploadPath, fileName);

            try {
                Files.deleteIfExists(filePath);
                Files.deleteIfExists(thumbnailPath);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }

        });
    }
}
