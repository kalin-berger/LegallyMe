package com.github.crummish.translegal.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.github.crummish.translegal.activity.R;
import com.github.crummish.translegal.document.DocumentType;
import com.github.crummish.translegal.document.State;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

public class DocumentsTabFragment extends BaseTitledFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // First time opening, initialize to selection screen
        SelectScreenFragment selectScreen = new SelectScreenFragment();
        Bundle b = new Bundle();
        // Example demonstration of selection screen
        // TODO: Replace example selection screen with flexible implementation
        TreeMap<State, TreeSet<DocumentType>> stateDocumentsMap = new TreeMap<>();
        TreeSet<DocumentType> virginiaDocs = new TreeSet<>();
        virginiaDocs.add(DocumentType.NAME_CHANGE);
        virginiaDocs.add(DocumentType.BIRTH_CERTIFICATE);
        virginiaDocs.add(DocumentType.DRIVERS_LICENSE);
        stateDocumentsMap.put(State.VIRGINIA, virginiaDocs);
        b.putSerializable(SelectScreenFragment.EXTRA_STATE_DOCUMENTS_MAP, (Serializable) stateDocumentsMap);
        selectScreen.setArguments(b);

        setTitle(getString(R.string.documents_tab_selection_title), getString(R.string.documents_tab_selection_subtitle));

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                ft.replace(R.id.content_container, selectScreen)
                .commit();
    }

    public static class SelectScreenFragment extends Fragment {

        private final static String EXTRA_STATE_DOCUMENTS_MAP = "stateDocumentsMap";
        private TreeMap<State, TreeSet<DocumentType>> stateDocumentsMap;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if(savedInstanceState != null) {
                stateDocumentsMap = (TreeMap<State, TreeSet<DocumentType>>) savedInstanceState.getSerializable(EXTRA_STATE_DOCUMENTS_MAP);
            }
            else if(getArguments() != null) {
                stateDocumentsMap = (TreeMap<State, TreeSet<DocumentType>>) getArguments().getSerializable(EXTRA_STATE_DOCUMENTS_MAP);
            }
            else {
                throw new IllegalArgumentException("No state document information provided");
            }
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);

            View rootView = inflater.inflate(R.layout.fragment_documents_tab_selection, container, false);

            final Spinner stateSpinner = rootView.findViewById(R.id.select_state_spinner);

            ArrayAdapter<State> stateNameAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, new ArrayList<State>(stateDocumentsMap.keySet()));
            stateSpinner.setAdapter(stateNameAdapter);

            final Spinner documentSpinner = rootView.findViewById(R.id.select_document_spinner);

            stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    State selectedState = (State) stateSpinner.getSelectedItem();
                    ArrayList<DocumentType> availableDocuments = new ArrayList<>(stateDocumentsMap.get(selectedState));
                    ArrayAdapter<DocumentType> documentTypeAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, availableDocuments);
                    documentSpinner.setAdapter(documentTypeAdapter);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            final Button goButton = rootView.findViewById(R.id.go_button);
            goButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            return rootView;
        }
    }
}
