package com.blocksports.assignment.model;


import com.blocksports.assignment.enums.RecordStatus;
import com.blocksports.assignment.util.InstantJsonDeSerializer;
import com.blocksports.assignment.util.InstantJsonSerializer;
import com.blocksports.assignment.util.LocalDateTimeConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;

@MappedSuperclass
public class ModelImpl implements Model {

    @Id
    @SequenceGenerator(name="seq-gen",sequenceName="MY_SEQ_GEN", initialValue=205, allocationSize=12)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq-gen")
    private long id;

    @Column(unique = true)
    private String key;

    @CreatedDate
    @Column(name = "createddate")
    @Convert(converter = LocalDateTimeConverter.class)
    @JsonDeserialize(using = InstantJsonDeSerializer.class)
    @JsonSerialize(using = InstantJsonSerializer.class)
    private Instant createdDate;

    @LastModifiedDate
    @Column(name = "lastmodifieddate")
    @Convert(converter = LocalDateTimeConverter.class)
    @JsonDeserialize(using = InstantJsonDeSerializer.class)
    @JsonSerialize(using = InstantJsonSerializer.class)
    private Instant lastModifiedDate;

    @Enumerated(EnumType.STRING)
    private RecordStatus status = RecordStatus.ACTIVE;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @JsonIgnore
    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    @JsonIgnore
    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public RecordStatus getStatus() {
        return status;
    }

    public void setStatus(RecordStatus status) {
        this.status = status;
    }
}
