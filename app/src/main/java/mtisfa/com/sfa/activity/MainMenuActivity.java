package mtisfa.com.sfa.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mtisfa.com.sfa.R;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }
    public void goToAssignment(View v) {
        startActivity(new Intent(getApplicationContext(),AssignmentActivity.class));
    }
}
