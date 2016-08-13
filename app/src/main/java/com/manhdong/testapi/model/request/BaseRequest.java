package com.manhdong.testapi.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Saphiro on 7/24/2016.
 */
public class BaseRequest {

    @SerializedName("token")
    @Expose
    String Token;

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }



}
