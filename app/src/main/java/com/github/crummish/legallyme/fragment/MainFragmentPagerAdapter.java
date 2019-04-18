package com.github.crummish.legallyme.fragment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[] { "Home", "Documents", "Favorites", "Search" };

    public MainFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new HomeTabFragment();
            case 1:
                return new DocumentsTabFragment();
            case 2:
                return new FavoritesTabFragment();
            case 3:
                return new SearchTabFragment();
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
