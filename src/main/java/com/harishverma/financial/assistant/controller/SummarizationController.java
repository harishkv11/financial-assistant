package com.harishverma.financial.assistant.controller;

import com.harishverma.financial.assistant.service.SummarizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/summarize")
@RequiredArgsConstructor
public class SummarizationController {
    private final SummarizationService summarizationService;

    @GetMapping("/{docId}")
    public ResponseEntity<String> summarize(@PathVariable String docId) {
        return new ResponseEntity<>(summarizationService.summarize(docId), HttpStatus.OK);
    }
}
