package com.harishverma.financial.assistant.controller;

import com.harishverma.financial.assistant.entity.UploadedFile;
import com.harishverma.financial.assistant.repository.UploadedFileRepository;
import com.harishverma.financial.assistant.security.JwtUtil;
import com.harishverma.financial.assistant.service.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class FileUploadController {
    private final UploadFileService uploadFileService;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestHeader("Authorization") String authHeader) throws IOException {

        uploadFileService.uploadFile(authHeader, file);
        return ResponseEntity.ok("✅ File uploaded successfully.");
    }

    @GetMapping
    public ResponseEntity<List<String>> getUploadedFiles(
            @RequestHeader("Authorization") String authHeader) {
        List<String> filenames = uploadFileService.getUploadFiles(authHeader);
        return ResponseEntity.ok(filenames);
    }
}

