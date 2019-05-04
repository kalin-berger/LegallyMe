package com.github.crummish.legallyme.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.crummish.legallyme.activity.R;
import com.github.crummish.legallyme.document.RecordField;
import com.github.crummish.legallyme.document.RecordType;
import com.github.crummish.legallyme.document.RecordState;

import java.util.ArrayList;
import java.util.Arrays;

public class DocumentsTabFragment extends BaseTitledFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // First time opening, initialize to selection screen
        SelectScreenFragment selectScreen = new SelectScreenFragment();

        setTitle(getString(R.string.documents_tab_selection_title), getString(R.string.documents_tab_selection_subtitle));

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                ft.replace(R.id.content_container, selectScreen)
                .commit();
    }

    public static class SelectScreenFragment extends Fragment {

        RecordState selectedState;
        ArrayList<RecordType> selectedRecordTypes;
        ArrayList<RecordField> selectedRecordFields;
        boolean courtOrderCompleted;

        //TODO: Normalize extra names to be usable in ChecklistFragment
        public static final String  EXTRA_SELECTED_STATE = "extraSelectedState",
                                    EXTRA_SELECTED_RECORD_TYPES = "extraSelectedRecordTypes",
                                    EXTRA_SELECTED_RECORD_FIELDS = "extraSelectedRecordFields",
                                    EXTRA_COURT_ORDER_COMPLETED = "extraCourtOrderCompleted";

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //TODO: Recover selected items from savedInstanceState if exists
            selectedRecordTypes = new ArrayList<>();
            selectedRecordFields = new ArrayList<>();
            courtOrderCompleted = false;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);

            View rootView = inflater.inflate(R.layout.fragment_documents_tab_selection, container, false);

            final Spinner stateSpinner = rootView.findViewById(R.id.select_state_spinner);

            ArrayAdapter<RecordState> stateNameAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, Arrays.asList(RecordState.values()));
            stateSpinner.setAdapter(stateNameAdapter);

            stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selectedState = (RecordState) stateSpinner.getSelectedItem();
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
                        selectedRecordTypes.add(RecordType.BIRTH_CERTIFICATE);
                    } else {
                        selectedRecordTypes.remove(RecordType.BIRTH_CERTIFICATE);
                    }
                }
            });
            checkDrivers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View rootView) {
                    if(((CompoundButton) rootView).isChecked()) {
                        selectedRecordTypes.add(RecordType.DRIVERS_LICENSE);
                    } else {
                        selectedRecordTypes.remove(RecordType.DRIVERS_LICENSE);
                    }
                }
            });
            checkSocial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View rootView) {
                    if(((CompoundButton) rootView).isChecked()) {
                        selectedRecordTypes.add(RecordType.SOCIAL_SECURITY);
                    } else {
                        selectedRecordTypes.remove(RecordType.SOCIAL_SECURITY);
                    }
                }
            });
            checkPassport.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View rootView) {
                    if(((CompoundButton) rootView).isChecked()) {
                        selectedRecordTypes.add(RecordType.PASSPORT);
                    } else {
                        selectedRecordTypes.remove(RecordType.PASSPORT);
                    }
                }
            });
            checkName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View rootView) {
                    if(((CompoundButton) rootView).isChecked()) {
                        selectedRecordFields.add(RecordField.NAME);
                    } else {
                        selectedRecordFields.remove(RecordField.NAME);

                    }
                }
            });
            checkGender.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View rootView) {
                    if(((CompoundButton) rootView).isChecked()) {
                        selectedRecordFields.add(RecordField.GENDER_MARKER);
                    } else {
                        selectedRecordFields.remove(RecordField.GENDER_MARKER);

                    }
                }
            });

            final Button goButton = rootView.findViewById(R.id.go_button);
            goButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Carry over saved user choices and switch fragments
                    ChecklistFragment checklistFragment = new ChecklistFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(EXTRA_SELECTED_STATE, selectedState);
                    bundle.putSerializable(EXTRA_SELECTED_RECORD_TYPES, selectedRecordTypes);
                    bundle.putSerializable(EXTRA_SELECTED_RECORD_FIELDS, selectedRecordFields);
                    bundle.putBoolean(EXTRA_COURT_ORDER_COMPLETED, courtOrderCompleted);
                    checklistFragment.setArguments(bundle);

                    FragmentTransaction ft = getParentFragment().getChildFragmentManager().beginTransaction();
                    ((DocumentsTabFragment) getParentFragment()).setTitle("Your personalized checklist", "Navigate your transition");

                    ft.replace(R.id.content_container, checklistFragment)
                            .addToBackStack("testname")
                            .commit();
                }
            });

            return rootView;
        }
    }

    // Fragment to display checklist; currently populated with static dummy data for demo
    public static class ChecklistFragment extends Fragment {
        RecordState selectedState;
        ArrayList<RecordType> selectedRecordTypes;
        ArrayList<RecordField> selectedRecordFields;
        boolean courtOrderCompleted;

        public static final String  EXTRA_SELECTED_STATE = "extraSelectedState",
                EXTRA_SELECTED_RECORD_TYPES = "extraSelectedRecordTypes",
                EXTRA_SELECTED_RECORD_FIELDS = "extraSelectedRecordFields",
                EXTRA_COURT_ORDER_COMPLETED = "extraCourtOrderCompleted";

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);
            View rootView = inflater.inflate(R.layout.fragment_documents_tab_checklist, container, false);

            if(getArguments() != null) {
                selectedState = (RecordState) getArguments().getSerializable(EXTRA_SELECTED_STATE);
                selectedRecordTypes = (ArrayList<RecordType>) getArguments().getSerializable(EXTRA_SELECTED_RECORD_TYPES);
                selectedRecordFields = (ArrayList<RecordField>) getArguments().getSerializable(EXTRA_SELECTED_RECORD_FIELDS);
                courtOrderCompleted = getArguments().getBoolean(EXTRA_COURT_ORDER_COMPLETED);
            }
            else {
                selectedState = RecordState.VIRGINIA;
                selectedRecordTypes = new ArrayList<>();
                selectedRecordFields = new ArrayList<>();
                courtOrderCompleted = false;
            }

            final TextView info = (TextView) rootView.findViewById(R.id.checklist_info);
            info.setMovementMethod(LinkMovementMethod.getInstance());
            //TODO: Link data to blurbs in database
            String info_string = getString(R.string.checklistHeading);

            if(!courtOrderCompleted){
                String petition = getString(R.string.courtOrderPetitionInstructions);
                info_string += petition;
            }



            if(selectedRecordFields.contains(RecordField.GENDER_MARKER)) {
                if(selectedRecordTypes.contains(RecordType.BIRTH_CERTIFICATE)) {
                    String birth_gender = "Change your birth certificate:\n1. Submit " + getString(R.string.app_sex_change) + " and Order for Sex Change forms to your local circuit court\n2. Fill out Application for Birth Certificate\n\n";
                    info_string = info_string + birth_gender;
                }
                if(selectedRecordTypes.contains(RecordType.DRIVERS_LICENSE)) {
                    String drivers_gender = "Change your driver's license:\n1. Submit VA Driver's License and ID Card Application\n2. Submit VA Gender Designation Change Request signed by a medical professional\n3. Some counties may also require you to submit an Order for Sex Change from your local court\n\n";
                    info_string = info_string + drivers_gender;
                }
                if(selectedRecordTypes.contains(RecordType.PASSPORT)) {
                    //
                }
                if(selectedRecordTypes.contains(RecordType.SOCIAL_SECURITY)) {
                    //
                }
            }
            else {
                if(selectedRecordTypes.contains(RecordType.BIRTH_CERTIFICATE)) {
                    String birth_name = "Change your birth certificate:\n1. Fill out Application for Birth Certificate\n\n";
                    info_string = info_string + birth_name;
                }
                if(selectedRecordTypes.contains(RecordType.DRIVERS_LICENSE)) {
                    String drivers_name = "Change your driver's license:\n1. Submit VA Driver's License and ID Card Application\n\n";
                    info_string = info_string + drivers_name;
                }
                if(selectedRecordTypes.contains(RecordType.PASSPORT)) {
                    //
                }
                if(selectedRecordTypes.contains(RecordType.SOCIAL_SECURITY)) {
                    //
                }
            }

            //info.setText(info_string);

            //TODO: List relevant documents from database

            return rootView;
        }
    }
}
