package com.harishverma.financial.assistant.controller;

import com.harishverma.financial.assistant.entity.UploadedFile;
import com.harishverma.financial.assistant.repository.UploadedFileRepository;
import com.harishverma.financial.assistant.security.JwtUtil;
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

    private final UploadedFileRepository fileRepo;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestHeader("Authorization") String authHeader) throws IOException {

        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        UploadedFile upload = UploadedFile.builder()
                .userId(username)
                .fileName(file.getOriginalFilename())
                .contentType(file.getContentType())
                .data(file.getBytes())
                .uploadedAt(new Date())
                .build();

        fileRepo.save(upload);
        return ResponseEntity.ok("✅ File uploaded successfully.");
    }

    @GetMapping
    public ResponseEntity<List<String>> getUploadedFiles(
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        List<UploadedFile> files = fileRepo.findByUserId(username);
        List<String> filenames = files.stream()
                .map(UploadedFile::getFileName)
                .collect(Collectors.toList());

        return ResponseEntity.ok(filenames);
    }
}

