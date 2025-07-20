package com.harishverma.financial.assistant.service;

import com.harishverma.financial.assistant.entity.UploadedFile;
import com.harishverma.financial.assistant.repository.UploadedFileRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SummarizationService {
    private final CohereService cohereService;
    private final UploadedFileRepository uploadedFileRepository;

    public String summarize(String docId) {
        Optional<UploadedFile> optionalFile = uploadedFileRepository.findById(docId);
        if(optionalFile.isPresent()) {
            UploadedFile file = optionalFile.get();
            if(StringUtils.isEmpty(file.getSummarizedText())) {
                String summarizedText = cohereService.getSummaryFromCohere(file.getExtractedText());
                file.setSummarizedText(summarizedText);
                UploadedFile savedFile = uploadedFileRepository.save(file);
                return savedFile.getSummarizedText();
            } else return file.getSummarizedText();
        }
        return Strings.EMPTY;
    }
}
