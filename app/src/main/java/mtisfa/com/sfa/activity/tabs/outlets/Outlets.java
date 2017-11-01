package mtisfa.com.sfa.activity.tabs.outlets;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import mtisfa.com.sfa.R;
import mtisfa.com.sfa.activity.AssignmentWorkLogActivity;
import mtisfa.com.sfa.activity.ListItem.OutletAdapter;
import mtisfa.com.sfa.activity.ListItem.ScheduleAdapter;
import mtisfa.com.sfa.model.Outlet;


public class Outlets extends Fragment {
    View v;
    ListView table;
    SwipeRefreshLayout swipe;
    EditText searchBox;
    OutletAdapter adapter;
    List<Outlet> listData;
    int page=1;
    int content_per_page=20;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_outlets, container, false);
        table = (ListView) v.findViewById(R.id.outletsListView);
        swipe = (SwipeRefreshLayout)v.findViewById(R.id.swipeRefreshLayout);
        searchBox=(EditText) v.findViewById(R.id.seachBox);
        searchBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    search();
                    return false;
                }
                return false;
            }
        });
        page=1;
        return v;
    }
    public void search(){

    }
    public void refreshTable() {
        adapter = new OutletAdapter(getActivity().getApplicationContext(),R.layout.outlets_list, listData,new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CALL_PHONE},
                            PackageManager.PERMISSION_GRANTED);

                }else{
                    Outlet data = listData.get((Integer) v.getTag(R.id.call));
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + data.default_contact_person_phone));
                    startActivity(callIntent);

                }
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        table.setAdapter(adapter);
    }
}
