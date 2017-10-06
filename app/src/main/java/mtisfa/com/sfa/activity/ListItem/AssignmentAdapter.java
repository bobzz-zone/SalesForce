package mtisfa.com.sfa.activity.ListItem;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import mtisfa.com.sfa.R;
import mtisfa.com.sfa.model.Assignment;

/**
 * Created by Windows 8.1 on 24/09/2017.
 */

public class AssignmentAdapter extends BaseAdapter {
    private Context context;
    private List<Assignment> list;
    public static final int customerId=1;
    public static final int dateId=2;
    public static final int noteId=3;
    public static final int firstRow=4;
    public AssignmentAdapter(@NonNull Context context, List<Assignment> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //View view = convertView;
        if(view==null){
            LinearLayout.LayoutParams rightGravityParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            rightGravityParams.gravity = Gravity.RIGHT;
            LinearLayout lin = new LinearLayout(context);
            lin.setLayoutParams(new AbsListView.LayoutParams(
                    AbsListView.LayoutParams.MATCH_PARENT,
                    AbsListView.LayoutParams.WRAP_CONTENT));
            lin.setOrientation(LinearLayout.VERTICAL);
            LinearLayout firstRow = new LinearLayout(context);
            firstRow.setLayoutParams(new AbsListView.LayoutParams(
                    AbsListView.LayoutParams.MATCH_PARENT,
                    AbsListView.LayoutParams.WRAP_CONTENT));
            firstRow.setOrientation(LinearLayout.HORIZONTAL);
            firstRow.setId(AssignmentAdapter.firstRow);

            TextView customer = new TextView(context);
            customer.setText(list.get(i).customer);
            customer.setId(AssignmentAdapter.customerId);
            customer.setTextColor(Color.BLACK);
            firstRow.addView(customer);


            TextView date = new TextView(context);
            date.setText(android.text.format.DateFormat.format("dd-MM-yyyy",list.get(i).date));
            //date.setText(list.get(i).date.toString());
            date.setId( AssignmentAdapter.dateId);
            date.setTextColor(Color.BLACK);
            date.setGravity(Gravity.RIGHT);
            date.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
            firstRow.addView(date,rightGravityParams);

            lin.addView(firstRow);

            TextView note = new TextView(context);
            note.setText(list.get(i).note);
            note.setId(AssignmentAdapter.noteId);
            note.setTextColor(Color.BLACK);
            lin.addView(note);

            view =lin;
        }else {
            TextView customer = (TextView) view.findViewById(AssignmentAdapter.customerId);
            TextView date = (TextView) view.findViewById(AssignmentAdapter.dateId);
            TextView note = (TextView) view.findViewById(AssignmentAdapter.noteId);
            customer.setText(list.get(i).customer);
            date.setText(android.text.format.DateFormat.format("dd-MM-yyyy",list.get(i).date));
            note.setText(list.get(i).note);
        }


        return view;
    }
}
