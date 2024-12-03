package com.appstra.users.implementation;

import com.appstra.users.entity.DocumentType;
import com.appstra.users.repository.DocumentTypeRepository;
import com.appstra.users.service.DocumentTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DocumentTypeImpl implements DocumentTypeService {
    private final DocumentTypeRepository documentTypeRepository;

    public DocumentTypeImpl(DocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    @Override
    public List<DocumentType> listDocumentType() {
        return documentTypeRepository.findAll();
    }
}
