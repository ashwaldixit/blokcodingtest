package com.blocksports.assignment.exceptions;

import com.blocksports.assignment.enums.NotificationInfo;

public class BlokNotFoundException extends BlokRuntimeException {

    private static final long serialVersionUID = -706783001803490307L;

    public BlokNotFoundException(String message) {
        super(message);
    }

    public BlokNotFoundException(String message, String moreInfo) {
        super(message, moreInfo);
    }

    @Override
    protected void setNotificationAndErrorCode() {
        this.notificationInfo = NotificationInfo.ERROR;
        this.errorCode = 404;
    }
}