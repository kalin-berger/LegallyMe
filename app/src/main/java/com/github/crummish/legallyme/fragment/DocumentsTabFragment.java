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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.crummish.legallyme.activity.R;
import com.github.crummish.legallyme.document.RecordField;
import com.github.crummish.legallyme.document.RecordType;
import com.github.crummish.legallyme.document.RecordState;
import com.github.crummish.legallyme.shared.ExtrasKeys;

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
        boolean courtOrderCompleted, courtOrderItemSelected = false;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Bundle args = null;
            if(getArguments() != null) {
                args = getArguments();
            }
            else if(savedInstanceState != null) {
                args = savedInstanceState;
            }
            if(args != null) {
                selectedState = (RecordState) args.getSerializable(ExtrasKeys.EXTRA_SELECTED_STATE);
                selectedRecordTypes = (ArrayList<RecordType>) args.getSerializable(ExtrasKeys.EXTRA_SELECTED_RECORD_TYPES);
                selectedRecordFields = (ArrayList<RecordField>) args.getSerializable(ExtrasKeys.EXTRA_SELECTED_RECORD_FIELDS);
                courtOrderCompleted = args.getBoolean(ExtrasKeys.EXTRA_COURT_ORDER_COMPLETED);
            }
            else {
                selectedRecordTypes = new ArrayList<>();
                selectedRecordFields = new ArrayList<>();
            }
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
            final RadioGroup courtOrderCompletedOptions = rootView.findViewById(R.id.court_order_completed_options);
            courtOrderCompletedOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    courtOrderItemSelected = true;
                    if(i == R.id.radio_yes){
                        courtOrderCompleted = true;
                    }
                    if(i == R.id.radio_no || i == R.id.radio_idk) {
                        courtOrderCompleted = false;
                    }
                }
            });

            final CheckBox  checkBirth = rootView.findViewById(R.id.checkbox_birth_cert),
                            checkDrivers = rootView.findViewById(R.id.checkbox_drivers),
                            checkSocial = rootView.findViewById(R.id.checkbox_social),
                            checkPassport = rootView.findViewById(R.id.checkbox_passport),
                            checkName = rootView.findViewById(R.id.checkbox_name),
                            checkGender = rootView.findViewById(R.id.checkbox_gender);

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
                    if(selectedState == null ||
                        selectedRecordTypes.isEmpty() ||
                        selectedRecordFields.isEmpty() ||
                        !courtOrderItemSelected) {
                        final Animation animShake = AnimationUtils.loadAnimation(getContext(), R.anim.shake);
                        goButton.startAnimation(animShake);
                        Toast.makeText(getContext(), "Please answer all fields", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    ChecklistFragment checklistFragment = new ChecklistFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(ExtrasKeys.EXTRA_SELECTED_STATE, selectedState);
                    bundle.putSerializable(ExtrasKeys.EXTRA_SELECTED_RECORD_TYPES, selectedRecordTypes);
                    bundle.putSerializable(ExtrasKeys.EXTRA_SELECTED_RECORD_FIELDS, selectedRecordFields);
                    bundle.putBoolean(ExtrasKeys.EXTRA_COURT_ORDER_COMPLETED, courtOrderCompleted);
                    checklistFragment.setArguments(bundle);

                    FragmentTransaction ft = getParentFragment().getChildFragmentManager().beginTransaction();
                    ((DocumentsTabFragment) getParentFragment()).setTitle("Your personalized checklist", "Navigate your transition");

                    ft.replace(R.id.content_container, checklistFragment)
                            .addToBackStack(null)
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

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);
            //View rootView = inflater.inflate(R.layout.fragment_documents_tab_checklist, container, false);

            Bundle args = null;
            if(getArguments() != null) {
                args = getArguments();
            }
            else if(savedInstanceState != null) {
                args = savedInstanceState;
            }
            if(args != null) {
                selectedState = (RecordState) args.getSerializable(ExtrasKeys.EXTRA_SELECTED_STATE);
                selectedRecordTypes = (ArrayList<RecordType>) args.getSerializable(ExtrasKeys.EXTRA_SELECTED_RECORD_TYPES);
                selectedRecordFields = (ArrayList<RecordField>) args.getSerializable(ExtrasKeys.EXTRA_SELECTED_RECORD_FIELDS);
                courtOrderCompleted = args.getBoolean(ExtrasKeys.EXTRA_COURT_ORDER_COMPLETED);
            }
            else {
                selectedRecordTypes = new ArrayList<>();
                selectedRecordFields = new ArrayList<>();
            }

            //TODO: Link data to blurbs in database
            ScrollView rootView = new ScrollView(getContext());
            LinearLayout layout = new LinearLayout(getContext());
            for(RecordType recordType : selectedRecordTypes) {
                for(RecordField recordField : selectedRecordFields) {
                    TextView instruction;

                }
            }
            String info_string = getString(R.string.checklistHeading);


            return rootView;
        }
    }
}
