package com.blocksports.assignment.exceptions;


import com.blocksports.assignment.enums.NotificationInfo;

public class UnAuthenticatedException extends BlokRuntimeException {

    private static final long serialVersionUID = -706783001803490307L;

    public UnAuthenticatedException(String message) {
        super(message);
    }

    public UnAuthenticatedException(String message, String moreInfo) {
        super(message, moreInfo);
    }

    @Override
    public String toString() {
        return "UnAuthenticatedException{" +
                "exceptionMessage=" + exceptionMessage +
                '}';
    }

    @Override
    protected void setNotificationAndErrorCode() {
        this.notificationInfo = NotificationInfo.ERROR;
        this.errorCode = 401;
    }
}