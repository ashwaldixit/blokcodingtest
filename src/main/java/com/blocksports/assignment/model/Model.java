package com.blocksports.assignment.model;


import com.blocksports.assignment.enums.RecordStatus;

import java.time.Instant;

public interface Model {
    long getId();

    void setId(long id);

    String getKey();

    void setKey(String key);

    Instant getCreatedDate();

    void setCreatedDate(Instant createdDate);

    Instant getLastModifiedDate();

    void setLastModifiedDate(Instant lastModifiedDate);

    RecordStatus getStatus();

    void setStatus(RecordStatus status);
}
