package test.openerp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    int SPLASH_TIME_OUT = 3000;
    SharedPreferences sp;
    private final String MY_PREFERENCES = "AUTHENTICATION";
    boolean loginstatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sp = getSharedPreferences(MY_PREFERENCES,MODE_PRIVATE);
        loginstatus = sp.getBoolean("LOGIN",false);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                Intent i;
                if(loginstatus){
                i = new Intent(getApplicationContext(), MainActivity.class);}
                else{
                    i = new Intent(getApplicationContext(), LoginActivity.class);}
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
