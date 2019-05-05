package com.github.crummish.legallyme.fragment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    final int PAGE_COUNT = 3;
    public final int    HOME_TAB = 0,
                        DOCUMENTS_TAB = 1,
                        FAVORITES_TAB = 2,
                        SEARCH_TAB = 3;

    private HomeTabFragment homeTab;
    private DocumentsTabFragment documentsTab;
    private SearchTabFragment searchTab;
    private FavoritesTabFragment favoritesTab;

    private String tabTitles[] = new String[] { "Home", "Docs", "Favorites", "Search" };

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
            case HOME_TAB:
                homeTab = new HomeTabFragment();
                return homeTab;
            case DOCUMENTS_TAB:
                documentsTab = new DocumentsTabFragment();
                return documentsTab;
            case FAVORITES_TAB:
                favoritesTab = new FavoritesTabFragment();
                return favoritesTab;
            case SEARCH_TAB:
                searchTab = new SearchTabFragment();
                return searchTab;
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    public HomeTabFragment getHomeTab() {
        return homeTab;
    }

    public DocumentsTabFragment getDocumentsTab() {
        return documentsTab;
    }

    public SearchTabFragment getSearchTab() {
        return searchTab;
    }

    public FavoritesTabFragment getFavoritesTab() {
        return favoritesTab;
    }
}
