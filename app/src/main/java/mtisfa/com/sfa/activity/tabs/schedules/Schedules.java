package mtisfa.com.sfa.activity.tabs.schedules;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mtisfa.com.sfa.R;
import mtisfa.com.sfa.Utility.MyCookieJar;
import mtisfa.com.sfa.Utility.Utility;
import mtisfa.com.sfa.activity.AssignmentWorkLogActivity;
import mtisfa.com.sfa.activity.ListItem.ScheduleAdapter;
import mtisfa.com.sfa.control.AssignmentControl;
import mtisfa.com.sfa.model.API;
import mtisfa.com.sfa.model.Assignment;
import mtisfa.com.sfa.model.Response.AssignmentListResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by davidwibisono on 08/10/17.
 */

public class Schedules extends Fragment {
    View v;
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    ListView table;
    SwipeRefreshLayout swipe;
    AssignmentControl assignmentControl;
    List<Assignment> listData;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_schedule, container, false);
        assignmentControl = new AssignmentControl(getActivity().getApplicationContext());
        table = (ListView) v.findViewById(R.id.schedulesListView);
        swipe = (SwipeRefreshLayout)v.findViewById(R.id.swipeRefreshLayout);

        Button today = (Button) v.findViewById(R.id.today);
        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = new Date();
                getAssignment();
            }
        });
        Button tommorow = (Button) v.findViewById(R.id.tommorow);
        tommorow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = new Date();
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(Calendar.DATE, 1);
                date = c.getTime();
                getAssignment();
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
        return v;
    }
    public void getAssignment() {

        MyCookieJar cookieJar = Utility.utility.getCookieFromPreference(getActivity());
        API api = Utility.utility.getAPIWithCookie(cookieJar);
        String user = Utility.utility.getLoggedName(getActivity());
        final Call<AssignmentListResponse> assignment = api.getAssignment("[[\"Assignment\",\"docstatus\",\"=\",1],[\"Assignment\",\"date\",\"=\",\""+sdf.format(date)+"\"],[\"Assignment\",\"user\",\"=\",\""+user+"\"]]");
        assignment.enqueue(new Callback<AssignmentListResponse>() {
            @Override
            public void onResponse(Call<AssignmentListResponse> call, Response<AssignmentListResponse> response) {
                assignmentControl.makeEmpty();
                if (Utility.utility.catchResponse(getActivity().getApplicationContext(), response)) {
                    AssignmentListResponse result = response.body();
                    //Toast.makeText(getContext(),""+response.body(),Toast.LENGTH_LONG);
                    listData=result.data;
                    assignmentControl.addFromList(listData);
                }
                refreshTable();

                swipe.setRefreshing(false);

            }

            @Override
            public void onFailure(Call<AssignmentListResponse> call, Throwable throwable) {
                Utility.utility.showConnectivityUnstable(getActivity().getApplicationContext());
                loadLocal();
                swipe.setRefreshing(false);
                refreshTable();
            }
        });
    }
    public void loadLocal(){
        listData = assignmentControl.getAll();
    }
    ScheduleAdapter adapter;
    public void refreshTable() {
        adapter = new ScheduleAdapter(getActivity().getApplicationContext(),R.layout.schedule_list, listData,new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CALL_PHONE},
                            PackageManager.PERMISSION_GRANTED);

                }else{
                    Assignment data = listData.get((Integer) v.getTag(R.id.call));
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + data.phone));
                    startActivity(callIntent);

                }
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoDetail = new Intent(getActivity().getApplicationContext(),AssignmentWorkLogActivity.class);
                Assignment detail = listData.get((Integer) v.getTag(R.id.holder));
                gotoDetail.putExtra("Customer",detail.customer);
                startActivity(gotoDetail);
            }
        });
        table.setAdapter(adapter);
    }
}