package com.shoppingmall.web.controller.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
public class UploadController {

    private final ImageStorageService imageStorageService;

    @PostMapping
    public ResponseEntity<Map<String, String>> create(@RequestParam("image") MultipartFile file) {
        log.info("Received file: {}", file.getOriginalFilename());

        // 이미지를 저장하고 URL을 반환받습니다.
        String imageUrl = imageStorageService.store(file);
        log.info("Image stored at URL: {}", imageUrl);

        // 응답 메시지와 이미지 URL을 맵에 저장합니다.
        Map<String, String> response = new HashMap<>();
        response.put("message", "이미지가 성공적으로 업로드 되었습니다.");
        response.put("imageUrl", imageUrl);

        // 클라이언트에 JSON 형태로 응답을 반환합니다.
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<String>> findAll() {
        List<String> files = imageStorageService.listAllFiles();
        return ResponseEntity.ok(files);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("filename") String filename) {
        imageStorageService.delete(filename);
        return ResponseEntity.ok("File deleted successfully");
    }
}
