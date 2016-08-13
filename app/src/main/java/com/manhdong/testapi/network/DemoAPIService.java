package com.manhdong.testapi.network;


import com.manhdong.testapi.model.User;
import com.manhdong.testapi.model.request.BaseRequest;
import com.manhdong.testapi.model.response.EmailRes;
import com.manhdong.testapi.model.response.LoginRes;
import com.manhdong.testapi.model.response.NotificationRes;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Saphiro on 7/24/2016.
 */
public interface DemoAPIService {

    //DS các API
    @POST("login")
    Observable<LoginRes> login(@Body User user);

    @POST("getEmails")
    Observable<EmailRes> getEmails(@Body BaseRequest baseRequest);

    @POST("getNotifications")
    Observable<NotificationRes> getNotifications(@Body BaseRequest baseRequest);

//    @POST("addUsers")
//    Observable<>

}
