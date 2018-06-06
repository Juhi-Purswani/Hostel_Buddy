package com.example.android.notification.models;

import android.support.annotation.NonNull;

/**
 * Created by Riya Khandelwal on 21-05-2018.
 */

public class RequestsId {
    public String userId;
    public<T extends RequestsId> T toId(@NonNull final String id){
        this.userId=id;
        return (T) this;
    }
}
