package com.github.crummish.legallyme.sql;

import android.support.annotation.NonNull;
import android.util.Log;

import com.github.crummish.legallyme.document.RecordField;
import com.github.crummish.legallyme.document.RecordState;
import com.github.crummish.legallyme.document.RecordType;

import java.net.MalformedURLException;
import java.net.URL;

public class RecordChangeDatabasePrePopulateHelper {

    public static RecordChangeForm[] getForms() {
        return new RecordChangeForm[] {
            createForm(RecordState.VIRGINIA, RecordType.GENERAL, RecordField.GENDER_MARKER, "Application for Sex Change","http://www.courts.state.va.us/forms/circuit/cc1411.pdf"),
            createForm(RecordState.VIRGINIA, RecordType.GENERAL, RecordField.NAME, "Application for Name Change","http://www.courts.state.va.us/forms/circuit/cc1411.pdf"),
        };
    }

    public static RecordChangeInstructions[] getInstructions() {
        return new RecordChangeInstructions[] {

            new RecordChangeInstructions(RecordState.VIRGINIA, RecordType.GENERAL, RecordField.NAME, 1,
                    "You must first submit a name change petition to your local circuit court." +
                                "\nFill out and submit an Application for Name Change."),

            new RecordChangeInstructions(RecordState.VIRGINIA, RecordType.BIRTH_CERTIFICATE, RecordField.GENDER_MARKER, 1,
                    "Submit an Application for Sex Change and an Order for Sex Change to your local circuit court."),
            new RecordChangeInstructions(RecordState.VIRGINIA, RecordType.BIRTH_CERTIFICATE, RecordField.GENDER_MARKER, 2,
                    "Fill out an Application for Birth Certificate."),

            new RecordChangeInstructions(RecordState.VIRGINIA, RecordType.DRIVERS_LICENSE, RecordField.GENDER_MARKER, 1,
                    "Submit a VA Driver's License and ID Card Application."),
            new RecordChangeInstructions(RecordState.VIRGINIA, RecordType.DRIVERS_LICENSE, RecordField.GENDER_MARKER, 2,
                    "Submit a VA Gender Designation Change Request, signed by a medical professional."),
            new RecordChangeInstructions(RecordState.VIRGINIA, RecordType.DRIVERS_LICENSE, RecordField.GENDER_MARKER, 3,
                    "Submit a VA Gender Designation Change Request, signed by a medical professional."),
            new RecordChangeInstructions(RecordState.VIRGINIA, RecordType.DRIVERS_LICENSE, RecordField.GENDER_MARKER, 4,
                    "Some counties may also require you to submit an Order for Sex Change from your local court.")

        };
    }

    /**
     * Creates a Form record given a string representing a URL. The resulting Form has a null URL if the provided one is malformed.
     * @param state
     * @param type
     * @param field
     * @param title
     * @param url
     * @return
     */
    public static RecordChangeForm createForm(@NonNull RecordState state, @NonNull RecordType type, @NonNull RecordField field, String title, String url) {
        URL parsedUrl;
        try {
            parsedUrl = new URL(url);
        } catch (MalformedURLException e) {
            Log.e("DbPrepopulateHelper", "Entered URL was invalid: " + url);
            parsedUrl = null;
        }

        return new RecordChangeForm(state, type, field, title, parsedUrl);
    }
}
