package test.openerp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.openerp.utils.Connectivity;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sp;
    private final String MY_PREFERENCES = "AUTHENTICATION";
    String email,password;

    @BindView(R.id.activity_login)
    LinearLayout activity_login;

    @BindView(R.id.login_et_email) EditText etemail;

    @BindView(R.id.login_et_password) EditText etpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        sp = getSharedPreferences(MY_PREFERENCES,MODE_PRIVATE);
    }

    @OnClick(R.id.btnLogin)
    void onClickLoginButton() {


        if (TextUtils.isEmpty(etemail.getEditableText().toString())) {
            showSnackMessage(R.string.username_cannot_empty);
        } else if(!etemail.getEditableText().toString().contains("@")){
            showSnackMessage(R.string.invalid_email);
        } else if (TextUtils.isEmpty(etpassword.getEditableText().toString())){
            showSnackMessage(R.string.password_cannot_empty);
        } else if (Connectivity.isConnected(this)) {
           login();
        } else {
            showSnackMessage(R.string.no_network_connection);
        }
    }

    private void login() {

        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("LOGIN",true);
        editor.commit();

        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();


    }

    public void showSnackMessage(int message) {
        Snackbar snackbar = Snackbar
                .make(activity_login,message, Snackbar.LENGTH_LONG);

        snackbar.show();
    }
}
