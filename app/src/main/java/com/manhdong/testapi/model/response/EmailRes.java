package com.manhdong.testapi.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manhdong.testapi.model.Email;

import java.util.List;


/**
 * Created by Saphiro on 7/27/2016.
 */
public class EmailRes {

    @SerializedName("dataEmail")
    @Expose
    List<Email> emailList;

}
