package com.manhdong.testapi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.manhdong.testapi.model.User;
import com.manhdong.testapi.model.response.BaseResponse;
import com.manhdong.testapi.network.APIServiceBuilder;
import com.manhdong.testapi.network.DemoAPIService;
import com.manhdong.testapi.network.NetworkRequest;
import com.manhdong.testapi.util.GetPreference;
import com.manhdong.testapi.view.NotificationActivity;

import rx.Subscription;

public class MainActivity extends AppCompatActivity {


    final static String DOMAIN = "http://freelancer-1325.appspot.com/"; //SERVER CỦA THẦY

    Button  login;
    EditText email, password;
    CheckBox checkbox;
    GetPreference getPref;

    Subscription subscription;
    DemoAPIService demoAPIService;

//    SharedPreferences userPref;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription !=null){
            subscription.unsubscribe();
            subscription = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPref = new GetPreference(this);
        checklogin();
        initUser(); //put user to preference

        //////Set up kết nối server với retrofit

        login = (Button) findViewById(R.id.login);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        checkbox = (CheckBox) findViewById(R.id.checkbox);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = email.getText().toString();
                String pass = password.getText().toString();
                startAPI(user,pass);

                //Kiểm tra tính hợp lệ
//                if (validateUser(user, pass, getPref)){
//                    //Lấy token từ Server?
//
//                    //Chuyển vào màn hình trong
////                    Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
////                    startActivity(intent);
//                    startAPI(user,pass);
//                };

            }
        });




    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        checklogin();
//    }

    private void checklogin() {
//        SharedPreferences getlog = getSharedPreferences("LoginStatus", MODE_PRIVATE);

        boolean log = getPref.getLogin();
        if(log){
            Intent intent = new Intent(this, NotificationActivity.class);
            startActivity(intent);
           // finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        SharedPreferences getlog = getSharedPreferences("LoginStatus", MODE_PRIVATE);

//        boolean log = getlog.getBoolean("isLoggedIn", false);
        boolean log = getPref.getLogin();
        if(log){
            finish();
        }
    }

    private void startAPI(String user, String pass) {
        Log.d("Login Info", "Login");

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();
        User user1 = new User();
        user1.setUserName(user);
        user1.setPassword(pass);

        demoAPIService = APIServiceBuilder.buildAPIServices(this, DOMAIN);
        subscription = NetworkRequest.performAsyncRequest(this, demoAPIService.login(user1),
                data ->{
                    //lưu token
                    if(data.getStatus() == BaseResponse.STATUS_SUCCESS){
                        String token = data.getUser().getToken();
                        getPref.putToken(user, token);
                    }
                    return data;},
                next ->{
                    progressDialog.dismiss();
                    if(next.getStatus() == 1){
                        Log.d("getstatus", Integer.parseInt(String.valueOf(next.getStatus()))+"");
                        Intent intent = new Intent(this, NotificationActivity.class);
                        startActivity(intent);
//                        SharedPreferences preferences = getSharedPreferences("LoginStatus", MODE_PRIVATE);
//                        preferences.edit().putBoolean("isLoggedIn", true).commit();
                        getPref.setLogin(true);
                        finish();

                    }else {
                        Snackbar.make(login, next.getMessage(), Snackbar.LENGTH_LONG).show();
                    }
                });

    }

    private boolean validateUser(String user, String pass, GetPreference pref) {
        boolean valid = false;

        //Kiểm tra đã điền email & pass
        if(user.isEmpty() || user.length()<4){
            email.setError("Username cannot be empty and must have at least 8 characters!");
        }
        else if (pass.isEmpty() || pass.length()<4 || pass.length() > 10){
            password.setError("Password cannot be empty and must have at 4-10 characters!");
        }
        else {
            email.setError(null);
            password.setError(null);
            Log.d("Email", user);
            Log.d("pass", pass);


            //Kiểm tra trùng khớp preference
            // String prefUser = pref.getUser().getUserName();
            String prefPass = pref.getUser(user).getPassword();
            Log.d("prefpass", prefPass);

            if (pass.equals(prefPass)){
                valid = true;
            }
        }

        return valid;

    }
    private void initUser() {
        getPref.putUser("dongmanh", "1234");
        getPref.putUser("dongdong", "12345");
        getPref.putUser("dongabc", "abcd");
        getPref.putUser("dong", "1234");
        getPref.putUser("dongm", "1234");

    }
}
