package com.manhdong.testapi.network;

import android.content.Context;


import com.manhdong.testapi.model.response.BaseResponse;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import retrofit2.adapter.rxjava.HttpException;

import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Saphiro on 7/27/2016.
 */
public class NetworkRequest {

    public static <T> Subscription performAsyncRequest(final Context context,
                                                       Observable<T> observable,
                                                       Func1<? super T, BaseResponse> onDoInBackground,
                                                       Action1<BaseResponse> onNext){
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(onDoInBackground)
                .onErrorReturn(throwable -> {
                    BaseResponse baseResponse = new BaseResponse();
                    android.util.Log.i("errorNetwork", throwable.getMessage());
                    if(throwable instanceof HttpException){
                        HttpException response = (HttpException) throwable;
                        int code = response.code();
                        baseResponse.setStatus(code);
                        baseResponse.setMessage(response.getMessage());
                    }else {
                        baseResponse.setMessage(throwable.getMessage());
                        baseResponse.setStatus(0);
                    }
                    return baseResponse;
                })
                .subscribe(onNext);
    }

}
