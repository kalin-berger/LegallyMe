package com.github.crummish.translegal.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.crummish.translegal.activity.R;

public class BaseTitledFragment extends Fragment {

    String titleText, subtitleText;
    TextView title, subtitle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
            titleText = savedInstanceState.getString("title");
            subtitleText = savedInstanceState.getString("subtitle");
        }
        else if(getArguments() != null) {
            titleText = savedInstanceState.getString("title");
            subtitleText = savedInstanceState.getString("subtitle");
        }
        else {
            throw new IllegalArgumentException("No text was provided for title");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_base_titled, container, false);

        title = rootView.findViewById(R.id.textview_title);
        title.setText(titleText);

        subtitle = rootView.findViewById(R.id.textview_subtitle);
        subtitle.setText(subtitleText);

        return rootView;
    }
}
