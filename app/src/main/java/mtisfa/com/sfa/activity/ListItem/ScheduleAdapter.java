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
import android.widget.ImageView;
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

    public ScheduleAdapter(Context context, int layout, List<Assignment> list) {
        super(context, layout, list);
        this.context = context;
        this.list = list;
        this.layout = layout;
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
        ImageView call = (ImageView) view.findViewById(R.id.call);
        call.setTag(R.id.call, position);
        call.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Assignment data = list.get((Integer) v.getTag(R.id.call));
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + data.phone));
                Activity parent = (Activity) v.getContext();
                if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    Toast.makeText(v.getContext(),"Permission to call is denied",Toast.LENGTH_SHORT);
                    return false;
                }
                parent.startActivity(callIntent);
                return false;
            }
        });

        return view;
    }
}