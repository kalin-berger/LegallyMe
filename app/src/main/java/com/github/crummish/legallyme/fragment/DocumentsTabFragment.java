package com.github.crummish.legallyme.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.github.crummish.legallyme.activity.R;
import com.github.crummish.legallyme.document.RecordType;
import com.github.crummish.legallyme.document.RecordState;

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
        TreeMap<RecordState, TreeSet<RecordType>> stateDocumentsMap = new TreeMap<>();
        TreeSet<RecordType> virginiaDocs = new TreeSet<>();
        virginiaDocs.add(RecordType.BIRTH_CERTIFICATE);
        virginiaDocs.add(RecordType.DRIVERS_LICENSE);
        virginiaDocs.add(RecordType.PASSPORT);
        stateDocumentsMap.put(RecordState.VIRGINIA, virginiaDocs);
        b.putSerializable(SelectScreenFragment.EXTRA_STATE_DOCUMENTS_MAP, (Serializable) stateDocumentsMap);
        selectScreen.setArguments(b);

        setTitle(getString(R.string.documents_tab_selection_title), getString(R.string.documents_tab_selection_subtitle));

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                ft.replace(R.id.content_container, selectScreen)
                .commit();
    }

    public static class SelectScreenFragment extends Fragment {

        private final static String EXTRA_STATE_DOCUMENTS_MAP = "stateDocumentsMap";
        private TreeMap<RecordState, TreeSet<RecordType>> stateDocumentsMap;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if(savedInstanceState != null) {
                stateDocumentsMap = (TreeMap<RecordState, TreeSet<RecordType>>) savedInstanceState.getSerializable(EXTRA_STATE_DOCUMENTS_MAP);
            }
            else if(getArguments() != null) {
                stateDocumentsMap = (TreeMap<RecordState, TreeSet<RecordType>>) getArguments().getSerializable(EXTRA_STATE_DOCUMENTS_MAP);
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

            ArrayAdapter<RecordState> stateNameAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, new ArrayList<RecordState>(stateDocumentsMap.keySet()));
            stateSpinner.setAdapter(stateNameAdapter);

            final Spinner documentSpinner = rootView.findViewById(R.id.select_document_spinner);

            stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    RecordState selectedState = (RecordState) stateSpinner.getSelectedItem();
                    ArrayList<RecordType> availableDocuments = new ArrayList<>(stateDocumentsMap.get(selectedState));
                    ArrayAdapter<RecordType> documentTypeAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, availableDocuments);
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
