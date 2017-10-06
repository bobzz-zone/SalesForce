package mtisfa.com.sfa.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toolbar;

import mtisfa.com.sfa.R;

public class AssignmentWorkLogActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_work_log);
        setTitle(getIntent().getStringExtra("Customer"));

        //setActionBar(toolbar);
    }
}
