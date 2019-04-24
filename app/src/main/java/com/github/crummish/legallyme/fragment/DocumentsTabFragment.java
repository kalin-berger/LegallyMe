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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

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
        virginiaDocs.add(RecordType.SOCIAL_SECURITY);
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
                //throw new IllegalArgumentException("No state document information provided");
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

            //final Spinner documentSpinner = rootView.findViewById(R.id.select_document_spinner);

            stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    RecordState selectedState = (RecordState) stateSpinner.getSelectedItem();
                    ArrayList<RecordType> availableDocuments = new ArrayList<>(stateDocumentsMap.get(selectedState));
                    //ArrayAdapter<RecordType> documentTypeAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, availableDocuments);
                    //documentSpinner.setAdapter(documentTypeAdapter);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            // Radio button response
            final RadioGroup petitionRadio = rootView.findViewById(R.id.select_petition_bool);
            petitionRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if(i == R.id.radio_yes){
                        //
                    }
                    if(i == R.id.radio_no){
                        //
                    }
                    if(i == R.id.radio_idk){
                        //
                    }
                }
            });

            final CheckBox checkBirth = (CheckBox) rootView.findViewById(R.id.checkbox_birth_cert);
            final CheckBox checkDrivers = (CheckBox) rootView.findViewById(R.id.checkbox_drivers);
            final CheckBox checkSocial = (CheckBox) rootView.findViewById(R.id.checkbox_social);
            final CheckBox checkPassport = (CheckBox) rootView.findViewById(R.id.checkbox_passport);
            final CheckBox checkName = (CheckBox) rootView.findViewById(R.id.checkbox_name);
            final CheckBox checkGender = (CheckBox) rootView.findViewById(R.id.checkbox_gender);

            checkBirth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View rootView) {
                    if(((CompoundButton) rootView).isChecked()) {
                        // true
                    } else {
                        // false
                    }
                }
            });
            checkDrivers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View rootView) {
                    if(((CompoundButton) rootView).isChecked()) {
                        // true
                    } else {
                        // false
                    }
                }
            });
            checkSocial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View rootView) {
                    if(((CompoundButton) rootView).isChecked()) {
                        // true
                    } else {
                        // false
                    }
                }
            });
            checkPassport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View rootView) {
                    if(((CompoundButton) rootView).isChecked()) {
                        // true
                    } else {
                        // false
                    }
                }
            });
            checkName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View rootView) {
                    if(((CompoundButton) rootView).isChecked()) {
                        // true
                    } else {
                        // false
                    }
                }
            });
            checkGender.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View rootView) {
                    if(((CompoundButton) rootView).isChecked()) {
                        // true
                    } else {
                        // false
                    }
                }
            });

            final Button goButton = rootView.findViewById(R.id.go_button);
            goButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Carry over saved user choices and switch fragments
                    swapFragment();
                }
            });

            return rootView;
        }

        private void swapFragment() {
            ChecklistFragment checklistFragment = new ChecklistFragment();

            FragmentTransaction frag = getChildFragmentManager().beginTransaction();
            frag.replace(R.id.content_container, checklistFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    // Fragment to display checklist; currently populated with static dummy data for demo
    public static class ChecklistFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);

            Boolean petition_bool;
            Boolean birth_bool;
            Boolean drivers_bool;
            Boolean social_bool;
            Boolean passport_bool;
            Boolean name_bool;
            Boolean gender_bool;

            View rootView = inflater.inflate(R.layout.fragment_documents_tab_checklist, container, false);
            final TextView info = (TextView) rootView.findViewById(R.id.checklist_info);
            String info_string = "This is your personalized checklist! You still need to:\n\n";

            if(petition_bool = true){
                String petition = "You first must submit a name change petition to your local circuit court.\nFill out and submit forms: Application for Name Change, Order for Name Change\n\n";
                info_string = info_string + petition;
            }

            if(gender_bool = true) {
                if(birth_bool = true) {
                    String birth_gender = "Change your birth certificate:\n1. Submit Application for Sex Change and Order for Sex Change forms to your local circuit court\n2. Fill out Application for Birth Certificate\n\n";
                    info_string = info_string + birth_gender;
                }
                if(drivers_bool = true) {
                    String drivers_gender = "Change your driver's license:\n1. Submit VA Driver's License and ID Card Application\n2. Submit VA Gender Designation Change Request signed by a medical professional\n3. Some counties may also require you to submit an Order for Sex Change from your local court\n\n";
                    info_string = info_string + drivers_gender;
                }
                if(social_bool = true) {
                    //
                }
                if(passport_bool = true) {
                    //
                }
            } else if(gender_bool = false) {
                if(birth_bool = true) {
                    String birth_name = "Change your birth certificate:\n1. Fill out Application for Birth Certificate\n\n";
                    info_string = info_string + birth_name;
                }
                if(drivers_bool = true) {
                    String drivers_name = "Change your driver's license:\n1. Submit VA Driver's License and ID Card Application\n\n";
                    info_string = info_string + drivers_name;
                }
                if(social_bool = true) {
                    //
                }
                if(passport_bool = true) {
                    //
                }
            }

            info.setText(info_string);

            return rootView;
        }
    }
}
