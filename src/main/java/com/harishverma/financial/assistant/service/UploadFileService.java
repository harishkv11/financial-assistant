package com.harishverma.financial.assistant.service;

import com.harishverma.financial.assistant.entity.UploadedFile;
import com.harishverma.financial.assistant.repository.UploadedFileRepository;
import com.harishverma.financial.assistant.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UploadFileService {
    private final JwtUtil jwtUtil;
    private final UploadedFileRepository uploadedFileRepository;
    private final OCRService ocrService;

    public void uploadFile(String authHeader, MultipartFile file) throws IOException {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        UploadedFile uploadedFile = UploadedFile.builder()
                .userId(username)
                .fileName(file.getOriginalFilename())
                .contentType(file.getContentType())
                .data(file.getBytes())
                .uploadedAt(new Date())
                .build();

        String extractedText = ocrService.extractText(uploadedFile.getData(), file.getContentType());
        uploadedFile.setExtractedText(extractedText);

        uploadedFileRepository.save(uploadedFile);
    }

    public List<String> getUploadFiles(String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtUtil.extractUsername(token);

        List<UploadedFile> files = uploadedFileRepository.findByUserId(username);
        List<String> filenames = files.stream()
                .map(UploadedFile::getFileName)
                .collect(Collectors.toList());

        return filenames;
    }
}
