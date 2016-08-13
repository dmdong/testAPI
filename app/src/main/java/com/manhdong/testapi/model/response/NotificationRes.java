package com.manhdong.testapi.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manhdong.testapi.model.Notification;

import java.util.List;


/**
 * Created by Saphiro on 7/27/2016.
 */
public class NotificationRes {
    @SerializedName("notidata")
    @Expose
    List<Notification> notificationList;

    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }
}
