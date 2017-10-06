package mtisfa.com.sfa.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import mtisfa.com.sfa.R;
import mtisfa.com.sfa.Utility.MyCookieJar;
import mtisfa.com.sfa.Utility.Utility;
import mtisfa.com.sfa.model.API;
import mtisfa.com.sfa.model.LoginDetail;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        loading=(ProgressBar)findViewById(R.id.loading);
    }
    String user,pw;
    SharedPreferences mPrefs;
    ProgressBar loading;
    public void clicklogin(View v) {

        EditText username= (EditText)findViewById(R.id.username);
        user= username.getText().toString();
        EditText password= (EditText)findViewById(R.id.password);
        pw= password.getText().toString();

        if(user.isEmpty()||pw.isEmpty()){
            Context c = getApplicationContext();
            Toast.makeText(c,"username or password cannot be empty", Toast.LENGTH_SHORT).show();
        }
        else {
            loading.setVisibility(View.VISIBLE);
            doLogin();
        }
    }


    public void doLogin() {
        final MyCookieJar cookieJar = new MyCookieJar();
        API api = Utility.utility.getAPIWithCookie(cookieJar);
        Call<LoginDetail> login = api.login(user,pw,"mobile");
        final Activity activity = this;
        login.enqueue(new Callback<LoginDetail>() {
            @Override
            public void onResponse(Call<LoginDetail> call, Response<LoginDetail> response) {
                if (Utility.utility.catchResponse(getApplicationContext(), response)) {
                    //LoginDetail login = response.body();
                    Utility.utility.saveLoggedName(user, activity);
                    Utility.utility.saveCookieJarToPreference(cookieJar, activity);
                    startActivity(new Intent(getApplicationContext(),MainMenuActivity.class));
                    finish();
                }
                loading.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<LoginDetail> call, Throwable throwable) {
                loading.setVisibility(View.GONE);
                Utility.utility.showConnectivityUnstable(getApplicationContext());
            }
        });
    }
}
