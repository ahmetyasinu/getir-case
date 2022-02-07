package com.getir.readingisgood.general.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public abstract class AbstractAuditedEntity implements Serializable {

    @Id
    protected String id;

    @CreatedDate
    public LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

}
