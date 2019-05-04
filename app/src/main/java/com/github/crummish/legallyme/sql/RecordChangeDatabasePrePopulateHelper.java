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
                    "Submit a name change petition to your local circuit court.\n" +
                                "\u2022 Complete and file the Application for Name Change and the Order for Name Change forms with your local circuit court.\n" +
                                "\u2022 The Application for Name Change must be notarized. This can usually be done for free at your local circuit court.\n" +
                                "\u2022 You can file these applications in person or by mail.\n" +
                                "\u2022 You will need to bring valid identification in the forms of state ID card (i.e. driver’s license) and birth certificate, as well as proof of residency. If filing by mail, provide copies of these.\n" +
                                "\u2022 Filing this application does require a fee that is dependent on county; if cost is a concern, you may also apply for a fee waiver. Do not forget to include a check for the fee if filing by mail.\n" +
                                "\u2022 It should be noted that the court may schedule a hearing for your petition, but rarely (if ever) will they actually call you to court.\n"),
            new RecordChangeInstructions(RecordState.VIRGINIA, RecordType.GENERAL, RecordField.NAME, 2,
                    "Receive signed Order for Name Change in the mail.\n" +
                                "\u2022 You can now use this signed order to change other forms of identification!\n" +
                                "\u2022 It is recommended that you request at least 10 copies of the signed order for future ID changes and personal records. There is a fee of $2.50 per copy.\n"),

            new RecordChangeInstructions(RecordState.VIRGINIA, RecordType.BIRTH_CERTIFICATE, RecordField.GENDER_MARKER, 1,
                    "Submit a gender marker change petition to your local court.\n" +
                                "\u2022 Complete and file the Petition for Change of Sex and the Order for Change of Sex forms with your local circuit court.\n" +
                                "\u2022 The Petition for Change of Sex must be notarized. This can usually be done for free at your local circuit court.\n" +
                                "\u2022 You can file these applications in person or by mail.\n" +
                                "\u2022 You must file these forms with a letter from a license medical provider confirming that your “sex has been changed by medical procedure” (Va. Code § 32.1-269(E)), which is up to the interpretation of you and your provider.\n" +
                                "\u2022 Filing this application does require a fee of $98; if cost is concern, you may also apply for a fee waiver. Do not forget to include a check for the fee if filing by mail.\n"),
            new RecordChangeInstructions(RecordState.VIRGINIA, RecordType.BIRTH_CERTIFICATE, RecordField.GENDER_MARKER, 2,
                    "Receive signed Order for Change of Sex in the mail.\n" +
                               "\u2022 You can now use this signed order to update your birth certificate!\n"),
            new RecordChangeInstructions(RecordState.VIRGINIA, RecordType.BIRTH_CERTIFICATE, RecordField.GENDER_MARKER, 3,
                    "Apply for an updated Virginia birth certificate.\n" +
                               "\u2022 Complete the Application for Certification of a Vital Record.\n" +
                               "\u2022 Submit this application along with a signed Order for Name Change, a letter requesting an amendment to your birth certificate, and a check for a processing fee of $12 to:\n" +
                               "\u25BA Virginia Department of Health, Division of Vital Records, Attn: Special Services Department, PO Box 1000, Richmond, VA 23218-1000\n"),
            new RecordChangeInstructions(RecordState.VIRGINIA, RecordType.BIRTH_CERTIFICATE, RecordField.NAME, 1,
                    "Apply for an updated Virginia birth certificate.\n" +
                               "\u2022 Complete the Application for Certification of a Vital Record.\n" +
                               "\u2022 Submit this application along with a signed Order for Name Change, a letter requesting an amendment to your birth certificate, and a check for a processing fee of $12 to:\n" +
                               "\u25BA Virginia Department of Health, Division of Vital Records, Attn: Special Services Department, PO Box 1000, Richmond, VA 23218-1000\n"),

            new RecordChangeInstructions(RecordState.VIRGINIA, RecordType.DRIVERS_LICENSE, RecordField.GENDER_MARKER, 1,
                    "Apply for a Gender Change Approval Letter.\n" +
                               "\u2022 Complete the DMV’s Gender Designation Change Request, and have a licensed medical provider sign it.\n" +
                               "\u2022 Mail the completed form to:\n" +
                               "\u25BA Virginia Department of Motor Vehicles, PO Box 27412, Richmond, VA 23269-0001\n"),
            new RecordChangeInstructions(RecordState.VIRGINIA, RecordType.DRIVERS_LICENSE, RecordField.GENDER_MARKER, 2,
                        "Receive you Gender Change Approval Letter in the mail.\n" +
                                "\u2022 Allow at least 5 business days for processing.\n"),
            new RecordChangeInstructions(RecordState.VIRGINIA, RecordType.DRIVERS_LICENSE, RecordField.GENDER_MARKER, 3,
                        "Apply for an updated Virginia driver’s license.\n" +
                                "\u2022 Complete the Driver’s License and Identification Card Application.\n" +
                                "\u2022 Submit this application either in person or by mail with a $5 fee.\n" +
                                "\u2022 Submit this application along with your Gender Change Approval Letter.\n" +
                                "\u2022 You will need to bring your current valid driver’s license or ID card, as well as the payment fee of $32 for a new license in cash, check, or card.\n"),
            new RecordChangeInstructions(RecordState.VIRGINIA, RecordType.DRIVERS_LICENSE, RecordField.NAME, 1,
                        "Apply for an updated Virginia driver’s license.\n" +
                                "\u2022 Complete the Driver’s License and Identification Card Application.\n" +
                                "\u2022 Submit this application either in person or by mail with a $5 fee.\n" +
                                "\u2022 You will need to bring your current valid driver’s license or ID card, as well as the payment fee of $32 for a new license in cash, check, or card.\n")
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
