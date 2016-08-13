package com.manhdong.testapi.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.manhdong.testapi.MainActivity;
import com.manhdong.testapi.R;
import com.manhdong.testapi.util.GetPreference;


public class NotificationActivity extends AppCompatActivity {

    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        logout = (Button) findViewById(R.id.btnLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetPreference pref = new GetPreference(getApplicationContext());
                pref.setLogin(false);
//                MainActivity.log = false;
                Toast.makeText(NotificationActivity.this, "ABC", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
