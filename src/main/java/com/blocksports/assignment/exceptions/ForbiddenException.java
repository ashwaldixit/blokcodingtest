package com.blocksports.assignment.exceptions;

import com.blocksports.assignment.enums.NotificationInfo;

public class ForbiddenException extends BlokRuntimeException {

    private static final long serialVersionUID = -706783001803490307L;

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String message, String moreInfo) {
        super(message, moreInfo);
    }

    @Override
    public String toString() {
        return "ForbiddenException{" +
                "exceptionMessage=" + exceptionMessage +
                '}';
    }

    @Override
    protected void setNotificationAndErrorCode() {
        this.notificationInfo = NotificationInfo.ERROR;
        this.errorCode = 403;
    }
}