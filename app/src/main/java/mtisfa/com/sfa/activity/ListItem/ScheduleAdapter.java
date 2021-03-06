package mtisfa.com.sfa.activity.ListItem;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import mtisfa.com.sfa.R;
import mtisfa.com.sfa.model.Assignment;

/**
 * Created by Windows 8.1 on 13/10/2017.
 */

public class ScheduleAdapter extends ArrayAdapter<Assignment> {
    private Context context;
    private List<Assignment> list;
    int layout;
    View.OnClickListener listener ;
    View.OnClickListener listener_tap ;
    public ScheduleAdapter(Context context, int layout, List<Assignment> list, View.OnClickListener listener,View.OnClickListener listener_tap) {
        super(context, layout, list);
        this.context = context;
        this.list = list;
        this.layout = layout;
        this.listener=listener;
        this.listener_tap=listener_tap;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, parent, false);
        }
        Assignment data = list.get(position);
//        TextView ID = (TextView)view.findViewById(R.id.id_supir);
        TextView customer = (TextView) view.findViewById(R.id.customer);
        customer.setText(data.customer);
        TextView status = (TextView) view.findViewById(R.id.status);
        status.setText(data.status);
        TextView cp = (TextView) view.findViewById(R.id.cp);
        cp.setText(data.contact_display);
        LinearLayout tp = (LinearLayout) view.findViewById(R.id.holder);
        tp.setTag(R.id.holder,position);
        tp.setFocusable(true);
        tp.setOnClickListener(listener_tap);
        Button call = (Button) view.findViewById(R.id.call);
        call.setTag(R.id.call, position);
        call.setOnClickListener(listener);

        return view;
    }
}