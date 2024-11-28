package com.intec.users.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "DOCUMENT_TYPE",schema = "PARAMETERIZATION")
public class DocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DCTY_ID")
    private Integer documentTypeId;

    @Column(name = "DCTY_NAME")
    private String documentTypeName;

    @Column(name = "DCTY_INITIALS")
    private String documentTypeInitial;

    @Column(name = "DCTY_DESCRIPTION")
    private String documentDescription;

    @Column(name = "DCTY_CREATION_DATE")
    private Timestamp documentTypeCreationDate;

    @Column(name = "DCTY_EDIT_DATE")
    private Timestamp documentTypeEditDate;

    @Column(name = "DCTY_EDIT_USER_ID")
    private Integer documentTypeEditUserID;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "documentType")
    private List<Person> personDocumentList;
}
