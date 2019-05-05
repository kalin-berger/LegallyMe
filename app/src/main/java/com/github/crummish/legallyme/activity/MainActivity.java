package com.github.crummish.legallyme.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.github.crummish.legallyme.document.RecordField;
import com.github.crummish.legallyme.document.RecordState;
import com.github.crummish.legallyme.document.RecordType;
import com.github.crummish.legallyme.fragment.FavoritesTabFragment;
import com.github.crummish.legallyme.fragment.MainFragmentPagerAdapter;
import com.github.crummish.legallyme.sql.RecordChangeForm;
import com.github.crummish.legallyme.sql.RecordChangeFormViewModel;
import com.github.crummish.legallyme.sql.RecordChangeInstructionsViewModel;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MainFragmentPagerAdapter adapter;

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

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), MainActivity.this);
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(adapter.HOME_TAB).setIcon(R.drawable.home_tab);
        tabLayout.getTabAt(adapter.DOCUMENTS_TAB).setIcon(R.drawable.documents_tab);
        tabLayout.getTabAt(adapter.FAVORITES_TAB).setIcon(R.drawable.favorites_tab);
        tabLayout.getTabAt(adapter.SEARCH_TAB).setIcon(R.drawable.search_tab);
    }
    public void saveNewLinkInFavorites(String url) {
        adapter.getFavoritesTab();
    }
}
