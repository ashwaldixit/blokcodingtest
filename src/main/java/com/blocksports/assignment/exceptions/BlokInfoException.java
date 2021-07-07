package com.blocksports.assignment.exceptions;


import com.blocksports.assignment.enums.NotificationInfo;

public class BlokInfoException extends BlokRuntimeException {


    private static final long serialVersionUID = -706783001803490307L;

    public BlokInfoException(String message) {
        super(message);
    }

    @Override
    protected void setNotificationAndErrorCode() {
        this.errorCode = 200;
        this.notificationInfo = NotificationInfo.INFO;
    }


}
