package mtisfa.com.sfa.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import io.realm.Realm;
import mtisfa.com.sfa.R;
import mtisfa.com.sfa.Utility.GPSActivity;

public class MainActivity extends GPSActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(this);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                SharedPreferences cookie = getSharedPreferences("myprefs", Context.MODE_PRIVATE);
                String cookieJar = cookie.getString("cookieJar", "null");
                if(cookieJar.equals("null")){
                    Intent mainIntent = new Intent(MainActivity.this,SigninActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
                else {
                    Intent mainIntent = new Intent(MainActivity.this,MainMenuActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            }
        }, 1000);
    }

    @Override
    public void onLocationChanged(Location location) {
        super.onLocationChanged(location);

    }
}
