package mtisfa.com.sfa.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

import mtisfa.com.sfa.R;
import mtisfa.com.sfa.activity.tabs.news.News;
import mtisfa.com.sfa.activity.tabs.orders.Orders;
import mtisfa.com.sfa.activity.tabs.outlets.Outlets;
import mtisfa.com.sfa.activity.tabs.profile.Profile;
import mtisfa.com.sfa.activity.tabs.schedules.Schedules;

public class MainMenuActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener {
    ViewPager viewPager;
    TabHost tabHost;
    String[] tabs = {"Schedule","Outlets","Orders","News","Profile"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_menu);

        initViewPager();
        initTabHost();
    }
    public void goToAssignment(View v) {
        startActivity(new Intent(getApplicationContext(),AssignmentActivity.class));
    }

    private void initViewPager() {
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        List<Fragment> listFragments =  new ArrayList<Fragment>();
        listFragments.add(new Schedules());
        listFragments.add(new Outlets());
        listFragments.add(new Orders());
        listFragments.add(new News());
        listFragments.add(new Profile());

        MainMenuPagerAdapter viewJobOrderAdapter = new MainMenuPagerAdapter(getSupportFragmentManager(), listFragments);
        viewPager.setAdapter(viewJobOrderAdapter);
        viewPager.setOnPageChangeListener(this);
    }

    public class FakeContent implements TabHost.TabContentFactory{
        Context context;
        public FakeContent(Context mcontext){
            context=mcontext;
        }
        @Override
        public View createTabContent(String s) {
            View fakeView = new View(context);
            fakeView.setMinimumHeight(0);
            fakeView.setMinimumHeight(0);
            return fakeView;
        }
    }

    private void initTabHost() {
        tabHost=(TabHost)findViewById(R.id.tabhost);
        tabHost.setup();

        for(int i=0;i<tabs.length;i++){
            TabHost.TabSpec tabSpec;
            tabSpec = tabHost.newTabSpec(tabs[i]);
            tabSpec.setIndicator(tabs[i]);
            tabSpec.setContent(new FakeContent(getApplicationContext()));
            tabHost.addTab(tabSpec);
        }

        tabHost.setOnTabChangedListener(this);
    }

    //TAB HOST DELEGATE
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tabHost.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabChanged(String s) {
        int selectedItem = tabHost.getCurrentTab();
        viewPager.setCurrentItem(selectedItem);
    }
}
