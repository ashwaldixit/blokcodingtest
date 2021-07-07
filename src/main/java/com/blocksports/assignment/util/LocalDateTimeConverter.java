package com.blocksports.assignment.util;

import javax.persistence.AttributeConverter;
import java.time.Instant;

public class LocalDateTimeConverter implements AttributeConverter<Instant, Long> {
    @Override
    public Long convertToDatabaseColumn(Instant instant) {

        return instant != null ? instant.toEpochMilli() : null;
    }

    @Override
    public Instant convertToEntityAttribute(Long epochTime) {
        return epochTime != null ? Instant.ofEpochMilli(epochTime) : null;
    }
}