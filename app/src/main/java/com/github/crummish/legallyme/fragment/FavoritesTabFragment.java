package com.github.crummish.legallyme.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.github.crummish.legallyme.activity.R;

public class FavoritesTabFragment extends BaseTitledFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.favorites_tab_title), getString(R.string.favorites_tab_subtitle));

        Fragment favoritesGrid = new FavoritesGridFragment();
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.replace(R.id.content_container, favoritesGrid)
                .commit();
    }

    public static class FavoritesGridFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_favorites_tab, container, false);

            GridView favoritesGrid = rootView.findViewById(R.id.favorites_gridview);
            String[] documentNames = new String[] { "VA: name change", "VA: birth certificate", "VA: driver's license" };
            ArrayAdapter<String> documentNameAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, documentNames);
            favoritesGrid.setAdapter(documentNameAdapter);

            return rootView;
        }
    }
}
