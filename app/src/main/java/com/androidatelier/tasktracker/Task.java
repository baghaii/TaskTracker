package com.androidatelier.tasktracker;

import java.util.Date;
import java.util.UUID;

/**
 * Created by baghaii on 2/17/16.
 */
public class Task {
    private UUID mId;
    private String mTitle;
    private Date mDueDate;
    private boolean mCompleted;

    public Task() {
        mId = UUID.randomUUID();
        mDueDate = new Date();
        mCompleted = false;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDueDate() {
        return mDueDate;
    }

    public void setDueDate(Date dueDate) {
        mDueDate = dueDate;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public void setCompleted(boolean completed) {
        mCompleted = completed;
    }

}
