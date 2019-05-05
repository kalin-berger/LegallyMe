package com.github.crummish.legallyme.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ListView;
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
import com.github.crummish.legallyme.sql.RecordChangeFormViewModel;
import com.github.crummish.legallyme.sql.RecordChangeInstructions;
import com.github.crummish.legallyme.sql.RecordChangeInstructionsViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DocumentsTabFragment extends BaseTitledFragment {

    private RecordChangeFormViewModel formViewModel;
    private RecordChangeInstructionsViewModel instructionsViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        formViewModel = ViewModelProviders.of(getActivity()).get(RecordChangeFormViewModel.class);
        instructionsViewModel = ViewModelProviders.of(getActivity()).get(RecordChangeInstructionsViewModel.class);

        // First time opening, initialize to selection screen
        SelectScreenFragment selectScreen = new SelectScreenFragment();

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                ft.replace(R.id.content_container, selectScreen)
                .commit();
    }

    public static class SelectScreenFragment extends Fragment {

        private RecordState selectedState;
        private ArrayList<RecordType> selectedRecordTypes;
        private ArrayList<RecordField> selectedRecordFields;
        private boolean courtOrderCompleted, courtOrderItemSelected = false;

        private Spinner stateSpinner;

        private CheckBox checkBirth,
                        checkDrivers,
                        checkSocial,
                        checkPassport,
                        checkName,
                        checkGender;


        private RadioGroup courtOrderCompletedOptions;

        private Button goButton;

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

            stateSpinner = rootView.findViewById(R.id.select_state_spinner);
            stateSpinnerInit();

            courtOrderCompletedOptions = rootView.findViewById(R.id.court_order_completed_options);
            courtOrderItemInit();

            checkBirth = rootView.findViewById(R.id.checkbox_birth_cert);
            checkDrivers = rootView.findViewById(R.id.checkbox_drivers);
            checkSocial = rootView.findViewById(R.id.checkbox_social);
            checkPassport = rootView.findViewById(R.id.checkbox_passport);
            checkName = rootView.findViewById(R.id.checkbox_name);
            checkGender = rootView.findViewById(R.id.checkbox_gender);
            checkBoxInit();

            goButton = rootView.findViewById(R.id.go_button);
            goButtonInit();

            return rootView;
        }

        public void stateSpinnerInit() {
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
        }

        public void courtOrderItemInit() {
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
        }

        public void checkBoxInit() {
            checkBirth.setOnClickListener(new CheckBoxListener<>(selectedRecordTypes, RecordType.BIRTH_CERTIFICATE));
            checkDrivers.setOnClickListener(new CheckBoxListener<>(selectedRecordTypes, RecordType.DRIVERS_LICENSE));
            checkSocial.setOnClickListener(new CheckBoxListener<>(selectedRecordTypes, RecordType.SOCIAL_SECURITY));
            checkPassport.setOnClickListener(new CheckBoxListener<>(selectedRecordTypes, RecordType.PASSPORT));
            checkName.setOnClickListener(new CheckBoxListener<>(selectedRecordFields, RecordField.NAME));
            checkGender.setOnClickListener(new CheckBoxListener<>(selectedRecordFields, RecordField.GENDER_MARKER));
        }

        public void goButtonInit() {
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

                    switchToChecklist();
                }
            });
        }

        public void switchToChecklist() {
            ChecklistFragment checklistFragment = new ChecklistFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(ExtrasKeys.EXTRA_SELECTED_STATE, selectedState);
            bundle.putSerializable(ExtrasKeys.EXTRA_SELECTED_RECORD_TYPES, selectedRecordTypes);
            bundle.putSerializable(ExtrasKeys.EXTRA_SELECTED_RECORD_FIELDS, selectedRecordFields);
            bundle.putBoolean(ExtrasKeys.EXTRA_COURT_ORDER_COMPLETED, courtOrderCompleted);
            checklistFragment.setArguments(bundle);

            FragmentTransaction ft = getParentFragment().getChildFragmentManager().beginTransaction();

            ft.replace(R.id.content_container, checklistFragment)
                    .addToBackStack(null)
                    .commit();
        }

            private static class CheckBoxListener<T extends Enum> implements View.OnClickListener {
                private ArrayList<T> tracker;
                private T field;

                public CheckBoxListener(ArrayList<T> tracker, T field) {
                    this.field = field;
                    this.tracker = tracker;
                }

                @Override
                public void onClick(View rootView) {
                    if(((CompoundButton) rootView).isChecked()) {
                        tracker.add(field);
                    } else {
                        tracker.remove(field);
                    }
                }
            }
    }

    // Fragment to display checklist; currently populated with static dummy data for demo
    public static class ChecklistFragment extends Fragment {
        private RecordChangeFormViewModel formViewModel;
        private RecordChangeInstructionsViewModel instructionsViewModel;

        RecordState selectedState;
        ArrayList<RecordType> selectedRecordTypes;
        ArrayList<RecordField> selectedRecordFields;
        boolean courtOrderCompleted;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            formViewModel = ViewModelProviders.of(getActivity()).get(RecordChangeFormViewModel.class);
            instructionsViewModel = ViewModelProviders.of(getActivity()).get(RecordChangeInstructionsViewModel.class);
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);
            View rootView = inflater.inflate(R.layout.fragment_documents_tab_checklist, container, false);

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

            LinearLayout rootViewTest = new LinearLayout(getContext());
            ScrollView scrollView = new ScrollView(getContext());
            scrollView.addView(generateLayout());


            return rootViewTest;
        }

        private LinearLayout generateLayout() {
            LinearLayout layout = new LinearLayout(getContext());
            layout.setOrientation(LinearLayout.VERTICAL);
            String info_string = getString(R.string.checklistHeading);
            for(RecordType type : selectedRecordTypes) {
                for(RecordField field : selectedRecordFields) {

                    final TextView header = new TextView(getContext());
                    header.setText("Changing " + field.toString() + " on " + type.toString() + ":");
                    layout.addView(header);

                    instructionsViewModel.findInstructions(selectedState, type, field);
                    List<RecordChangeInstructions> instructions = instructionsViewModel.getFindInstructionsResults().getValue();
                    for(RecordChangeInstructions i :instructions) {
                        View checklistItem = LayoutInflater.from(getContext()).inflate(R.layout.view_checklist_item, null);
                        CheckBox checkBox = checklistItem.findViewById(R.id.checkbox);
                        TextView text = checklistItem.findViewById(R.id.text);
                        text.setText(i.getInstructions());

                        layout.addView(checklistItem);
                    }
                }
            }

            return layout;
        }
    }
}
