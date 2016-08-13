package com.manhdong.testapi.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manhdong.testapi.model.User;


/**
 * Created by Saphiro on 7/24/2016.
 */
public class LoginRes extends BaseResponse{

    @SerializedName("data")
    @Expose
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
