package com.example.android.notification;

import com.example.android.notification.models.RequestsId;

/**
 * Created by Riya Khandelwal on 19-05-2018.
 */

class Requests  {
    String To;
    String Message;
    public Requests()
    {

    }
    public Requests(String To, String Message) {
        this.To = To;
        this.Message = Message;

    }

    public String getTo() {
        return To;
    }

    public void setTo(String To) {
        this.To = To;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }


}
