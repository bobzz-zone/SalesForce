package mtisfa.com.sfa.activity.ListItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import mtisfa.com.sfa.R;
import mtisfa.com.sfa.Utility.GPSServices;
import mtisfa.com.sfa.Utility.Haversine;
import mtisfa.com.sfa.model.Outlet;

/**
 * Created by Windows 8.1 on 23/10/2017.
 */

public class OutletAdapter extends ArrayAdapter<Outlet> {
    private Context context;
    private List<Outlet> list;
    int layout;
    View.OnClickListener listener;
    View.OnClickListener listener_tap;

    public OutletAdapter(Context context, int layout, List<Outlet> list, View.OnClickListener listener, View.OnClickListener listener_tap) {
        super(context, layout, list);
        this.context = context;
        this.list = list;
        this.layout = layout;
        this.listener = listener;
        this.listener_tap = listener_tap;
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
        Outlet data = list.get(position);
        //        TextView ID = (TextView)view.findViewById(R.id.id_supir);
        TextView outlet_name = (TextView) view.findViewById(R.id.outlet_name);
        outlet_name.setText(data.name);
        TextView outlet_address = (TextView) view.findViewById(R.id.outlet_address);
        outlet_address.setText(data.default_address_location);
        TextView distance = (TextView) view.findViewById(R.id.distance);
        distance.setText(""+(Double) Haversine.distance(GPSServices.lat,GPSServices.longi,Double.parseDouble(data.latitude),Double.parseDouble(data.longitude)));
        LinearLayout tp = (LinearLayout) view.findViewById(R.id.holder);
        tp.setTag(R.id.holder, position);
        tp.setOnClickListener(listener_tap);
        Button call = (Button) view.findViewById(R.id.call);
        call.setTag(R.id.call, position);
        call.setOnClickListener(listener);

        return view;
    }
}
