package mtisfa.com.sfa.activity.tabs.orders;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mtisfa.com.sfa.R;

/**
 * Created by davidwibisono on 08/10/17.
 */

public class Orders extends Fragment {
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_orders, container, false);
        return v;
    }
}
