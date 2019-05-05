package com.github.crummish.legallyme.fragment;

import android.content.Context;
import android.icu.text.AlphabeticIndex;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.crummish.legallyme.activity.R;
import com.github.crummish.legallyme.document.RecordField;
import com.github.crummish.legallyme.document.RecordState;
import com.github.crummish.legallyme.document.RecordType;
import com.github.crummish.legallyme.sql.RecordChangeInstructions;

import org.jsoup.Connection;

import java.util.ArrayList;
import java.util.List;

public class ChecklistAdapter extends BaseAdapter {
    private RecordState selectedState;
    private Boolean courtOrderComplete;
    private ArrayList<RecordType> selectedRecords;
    private ArrayList<RecordField> selectedFields;
    Context context;
    LayoutInflater inflater;

    private static class ViewHolder {
        TextView step;
    }

    public ChecklistAdapter(RecordState state, Boolean courtOrder, ArrayList<RecordType> records, ArrayList<RecordField> fields, Context context) {
        this.context = context;
        this.selectedState = state;
        this.courtOrderComplete = courtOrder;
        this.selectedRecords = records;
        this.selectedFields = fields;
        inflater = (LayoutInflater.from(context));
    }

    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.checklist_view, null);
        final CheckedTextView checkedText = (CheckedTextView) convertView.findViewById(R.id.step_text);
        //TODO: set checkedText based on user selected input
        

        checkedText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkedText.isChecked()) {
                    checkedText.setCheckMarkDrawable(0);
                    checkedText.setChecked(false);
                } else {
                    checkedText.setCheckMarkDrawable(R.drawable.ic_check_box_black_24dp);
                    checkedText.setChecked(true);
                }
            }
        });
        return convertView;
    }
}
