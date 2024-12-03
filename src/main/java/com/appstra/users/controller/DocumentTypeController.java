package com.appstra.users.controller;

import com.appstra.users.entity.DocumentType;
import com.appstra.users.service.DocumentTypeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/documenttype")
public class DocumentTypeController {
    private final DocumentTypeService documentTypeService;

    public DocumentTypeController(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }
    @GetMapping("/listdocumenttype")
    @Operation(summary = "Lista tipos de documentos", description = "Lista tipos de documentos")
    public ResponseEntity<List<DocumentType>> listDocumenType(){
        return ResponseEntity.ok(documentTypeService.listDocumentType());
    }
}
