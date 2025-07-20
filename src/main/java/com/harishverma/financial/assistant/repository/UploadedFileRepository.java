package com.harishverma.financial.assistant.repository;

import com.harishverma.financial.assistant.entity.UploadedFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UploadedFileRepository extends MongoRepository<UploadedFile, String> {
    List<UploadedFile> findByUserId(String userId);
}
