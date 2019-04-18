package com.github.crummish.legallyme.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.crummish.legallyme.activity.R;

public class SearchTabFragment extends BaseTitledFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.search_tab_title), getString(R.string.search_tab_subtitle));
    }
}
