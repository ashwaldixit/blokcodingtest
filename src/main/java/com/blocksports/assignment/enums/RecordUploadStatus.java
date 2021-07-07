package com.blocksports.assignment.enums;

public enum RecordUploadStatus {

    NOTSTARTED("Not Started"), INPROGRESS("In Progress"), COMPLETED("Completed"), FAILED("Failed");

    private String desc;

    RecordUploadStatus(String description) {
        this.desc = description;
    }

    public String getDescription() {
        return this.desc;
    }
}
