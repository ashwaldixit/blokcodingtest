package com.blocksports.assignment.exceptions;

import com.blocksports.assignment.enums.NotificationInfo;
import com.blocksports.assignment.util.ObjectUtil;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

@Component
public class ExceptionMessage {

    private Set<String> messages;

    private int code;

    private NotificationInfo notificationInfo;

    private String moreInfo;

    private String stackTrace;

    @SuppressWarnings("unused")
    private ExceptionMessage() {

    }

    public ExceptionMessage(String messages, NotificationInfo notificationInfo, int statusCode) {
        this.messages = Collections.singleton(messages);
        this.code = statusCode;
        this.notificationInfo = notificationInfo;
    }

    public ExceptionMessage(String messages, NotificationInfo notificationInfo, int statusCode, String moreInfo) {
        this.messages = Collections.singleton(messages);
        this.code = statusCode;
        this.notificationInfo = notificationInfo;
        this.moreInfo = moreInfo;
    }

    public ExceptionMessage(Set<String> messages, NotificationInfo notificationInfo, int statusCode, String moreInfo) {
        this.messages = messages;
        this.code = statusCode;
        this.notificationInfo = notificationInfo;
        this.moreInfo = moreInfo;
    }


    public ExceptionMessage(Set<String> messages, NotificationInfo notificationInfo, int statusCode, String moreInfo, String stacktrace) {
        this.messages = messages;
        this.code = statusCode;
        this.notificationInfo = notificationInfo;
        this.moreInfo = moreInfo;
        this.stackTrace = stacktrace;
    }

    public ExceptionMessage(Set<String> messages, NotificationInfo notificationInfo, int statusCode) {
        this.messages = messages;
        this.code = statusCode;
        this.notificationInfo = notificationInfo;
        this.moreInfo = "";
    }

    public Set<String> getMessages() {
        return messages;
    }

    public void setMessages(Set<String> messages) {
        this.messages = messages;
    }

    public void setMessage(Set<String> errorMessage) {
        this.messages = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int errorCode) {
        this.code = errorCode;
    }

    public String getMoreInfo() {
        if (ObjectUtil.isNull(moreInfo)) return "";
        return moreInfo;
    }

    public void setMoreInfo(String exceptionStackTrace) {
        this.moreInfo = exceptionStackTrace;
    }

    public NotificationInfo getNotificationInfo() {
        return notificationInfo;
    }

    public void setNotificationInfo(NotificationInfo notificationInfo) {
        this.notificationInfo = notificationInfo;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    @Override
    public String toString() {
        return "{" +
                "messages=" + messages +
                ", code=" + code +
                ", type=" + notificationInfo +
                ", moreInfo='" + moreInfo + '\'' +
                '}';
    }
}
