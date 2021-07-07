package com.blocksports.assignment.exceptions;

import com.blocksports.assignment.enums.NotificationInfo;

import java.util.Set;

public class BlokBadRequestException extends BlokRuntimeException {

    private static final long serialVersionUID = -706783001803490307L;

    public BlokBadRequestException(String message) {
        super(message);
    }

    public BlokBadRequestException(String message, String moreInfo) {
        super(message, moreInfo);
    }

    public BlokBadRequestException(Set<String> messages) {
        super(messages);
    }


    @Override
    public String toString() {
        return "BlokBadRequestException{" +
                "exceptionMessage=" + exceptionMessage +
                '}';
    }

    @Override
    protected void setNotificationAndErrorCode() {
        this.notificationInfo = NotificationInfo.ERROR;
        this.errorCode = 400;
    }
}