package com.blocksports.assignment.exceptions;

import com.blocksports.assignment.enums.NotificationInfo;

import java.util.Set;

public abstract class BlokRuntimeException extends RuntimeException{


    protected ExceptionMessage exceptionMessage;
    protected int errorCode;
    protected NotificationInfo notificationInfo;


    protected BlokRuntimeException(String message) {
        super(message);
        setNotificationAndErrorCode();
        populateExceptionMessage(message);
    }

    protected BlokRuntimeException(String message, String moreInfo) {
        super(message);
        setNotificationAndErrorCode();
        populateExceptionMessage(message, moreInfo);
    }

    protected BlokRuntimeException(Set<String> messages) {
        super(messages.isEmpty() ? "" : messages.stream().findFirst().get());
        setNotificationAndErrorCode();
        populateExceptionMessage(messages);
    }

    private void populateExceptionMessage(String message) {
        exceptionMessage = new ExceptionMessage(message, this.notificationInfo, this.errorCode);
    }

    protected void populateExceptionMessage(String message, String moreInfo) {
        exceptionMessage = new ExceptionMessage(message, this.notificationInfo, this.errorCode, moreInfo);
    }

    protected void populateExceptionMessage(Set<String> messages) {
        exceptionMessage = new ExceptionMessage(messages, this.notificationInfo, this.errorCode);
    }

    protected abstract void setNotificationAndErrorCode();

    public ExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public NotificationInfo getNotificationInfo() {
        return notificationInfo;
    }

    public void setNotificationInfo(NotificationInfo notificationInfo) {
        this.notificationInfo = notificationInfo;
    }

}
