package mtisfa.com.sfa.activity;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import mtisfa.com.sfa.R;
import mtisfa.com.sfa.Utility.MyCookieJar;
import mtisfa.com.sfa.Utility.Utility;
import mtisfa.com.sfa.activity.ListItem.AssignmentAdapter;
import mtisfa.com.sfa.control.AssignmentControl;
import mtisfa.com.sfa.model.API;
import mtisfa.com.sfa.model.Assignment;
import mtisfa.com.sfa.model.Response.AssignmentListResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignmentActivity extends AppCompatActivity {
    ListView table;
    TextView noData;
    SwipeRefreshLayout swipe;
    AssignmentControl assignmentControl;
    List<Assignment> listData;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        assignmentControl.close();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        assignmentControl = new AssignmentControl(getApplicationContext());
        table = (ListView) findViewById(R.id.tableAssignment);
        noData=(TextView) findViewById(R.id.noData);
        swipe = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        table.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent gotoDetail = new Intent(getApplicationContext(),AssignmentWorkLogActivity.class);
                Assignment detail = listData.get(i);
                gotoDetail.putExtra("Customer",detail.customer);
                startActivity(gotoDetail);
            }
        });
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                getAssignment();

            }
        });
        getAssignment();
    }
    public void getAssignment() {

        MyCookieJar cookieJar = Utility.utility.getCookieFromPreference(this);
        API api = Utility.utility.getAPIWithCookie(cookieJar);
        String user = Utility.utility.getLoggedName(this);
        final Call<AssignmentListResponse> assignment = api.getAssignment("[[\"Assignment\",\"docstatus\",\"=\",1],[\"Assignment\",\"user\",\"=\",\""+user+"\"]]");
        assignment.enqueue(new Callback<AssignmentListResponse>() {
            @Override
            public void onResponse(Call<AssignmentListResponse> call, Response<AssignmentListResponse> response) {
                assignmentControl.makeEmpty();
                if (Utility.utility.catchResponse(getApplicationContext(), response)) {
                    AssignmentListResponse result = response.body();
                    if (result.data.size()==0){
                        if (noData.getVisibility()== View.INVISIBLE){
                            noData.setVisibility(View.VISIBLE);
                            table.setVisibility(View.INVISIBLE);
                        }
                    }else{
                        noData.setVisibility(View.INVISIBLE);
                        table.setVisibility(View.VISIBLE);
                        listData=result.data;
                        assignmentControl.addFromList(listData);

                    }
                }
                refreshTable();

                swipe.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<AssignmentListResponse> call, Throwable throwable) {
                Utility.utility.showConnectivityUnstable(getApplicationContext());
                loadLocal();
                swipe.setRefreshing(false);
                refreshTable();
            }
        });
    }
    public void loadLocal(){
        listData = assignmentControl.getAll();
    }
    AssignmentAdapter adapter;
    public void refreshTable(){
        adapter= new AssignmentAdapter(getApplicationContext(), listData);
        table.setAdapter(adapter);
    }
}
