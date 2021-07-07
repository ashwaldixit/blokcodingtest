package com.blocksports.assignment.exceptions;


import com.blocksports.assignment.enums.NotificationInfo;

public class BlokServerException extends BlokRuntimeException {

    public BlokServerException(String message) {
        super(message);
    }

    @Override
    protected void setNotificationAndErrorCode() {

        this.notificationInfo = NotificationInfo.ERROR;
        this.errorCode = 500;
    }
}
