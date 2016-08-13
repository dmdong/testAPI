package com.manhdong.testapi.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.manhdong.testapi.model.User;


/**
 * Created by Saphiro on 7/28/2016.
 */
public class GetPreference {

    SharedPreferences preferences;
    Context context;

    public GetPreference(Context context) {
        this.context = context;
       // this.preferences = preferences;
    }

    public SharedPreferences getLoginPref(){
        preferences = context.getSharedPreferences("LoginStatus", Context.MODE_PRIVATE);
        return preferences;
    }

    public void setLogin(Boolean logStatus){
//        preferences = context.getSharedPreferences("LoginStatus", Context.MODE_PRIVATE);
        getLoginPref().edit().clear().putBoolean("isLoggedIn", logStatus).apply();
    }

    public boolean getLogin(){
//        preferences = context.getSharedPreferences("LoginStatus", Context.MODE_PRIVATE);
        return getLoginPref().getBoolean("isLoggedIn", false);
    }


    public SharedPreferences getUserPref(){
        preferences = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        return preferences;
    }

    public SharedPreferences getTokenPref(){
        preferences = context.getSharedPreferences("Token", Context.MODE_PRIVATE);
        return preferences;
    }
    public User getUser(String username){
        User user = new User();
    //    user.setUserName(preferences.getString("username", ""));
        // user.setUserName(username);
        user.setPassword(getUserPref().getString("username", ""));
        return user;
    }

    public String getUserPass(String username){
        User user = new User();
        //    user.setUserName(preferences.getString("username", ""));
        // user.setUserName(username);
        user.setPassword(getUserPref().getString("username", ""));
        String userpass = getUserPref().getString(username,"");
        return userpass;
    }

    public void putUser(String username, String password){
//        SharedPreferences userPref = getUserPref();
//        SharedPreferences.Editor editor = userPref.edit();
//        editor.putString(username, password);
//        editor.apply();
        getUserPref().edit().putString(username, password).commit();

        String putuser = getUser(username).getUserName();
        String passuser = getUser(username).getPassword();
        Log.d("USER+PASS", putuser + " / " + passuser);

    }

    public String getToken(String userA){
       // String token = "";
        //Xử lý get Token từ preference nếu userA hợp lệ


        //SharedPreferences tokenPref = getTokenPref();
        //String token =
        return getTokenPref().getString(userA, "");
    }

    public void putToken(String userA, String token){
        //Xử lý lấy chuỗi Token từ server
        String tokenA = "AAABBCCC";
        tokenA = token;

        SharedPreferences tokenPref = getTokenPref();
        SharedPreferences.Editor editor = tokenPref.edit();
        editor.putString(userA, tokenA);
        editor.apply();
    }
}
