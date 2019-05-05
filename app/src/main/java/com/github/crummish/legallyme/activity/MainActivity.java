package com.github.crummish.legallyme.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.github.crummish.legallyme.fragment.MainFragmentPagerAdapter;
import com.github.crummish.legallyme.sql.RecordChangeFormViewModel;
import com.github.crummish.legallyme.sql.RecordChangeInstructionsViewModel;

public class MainActivity extends AppCompatActivity {

    private RecordChangeFormViewModel formViewModel;
    private RecordChangeInstructionsViewModel instructionsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        formViewModel = ViewModelProviders.of(this).get(RecordChangeFormViewModel.class);
        instructionsViewModel = ViewModelProviders.of(this).get(RecordChangeInstructionsViewModel.class);

        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO: Implement custom toolbar for title centering
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("legallyme");
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(),
                MainActivity.this));

        final int[] ICONS = new int[]{
                R.drawable.home_tab,
                R.drawable.documents_tab,
                R.drawable.favorites_tab,
                R.drawable.search_tab
        };

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(ICONS[0]);
        tabLayout.getTabAt(1).setIcon(ICONS[1]);
        tabLayout.getTabAt(2).setIcon(ICONS[2]);
        tabLayout.getTabAt(3).setIcon(ICONS[3]);
    }
}
