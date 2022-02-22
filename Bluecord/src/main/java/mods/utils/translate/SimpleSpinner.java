package mods.utils.translate;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import mods.constants.Constants;
import mods.constants.PreferenceKeys;
import mods.preference.Prefs;
import mods.utils.StringUtils;
import mods.view.Colors;

public class SimpleSpinner {
    private static final String TAG = "SimpleSpinner";
    private String selectedKey;
    private final Spinner spinner;

    private SimpleSpinner(Spinner spinner, String str) {
        this.spinner = spinner;
        this.selectedKey = str;
    }

    public static SimpleSpinner createTranslateSpinner(Context context, LinkedHashMap<String, String> linkedHashMap, String str) {
        Spinner spinner = new Spinner(context);
        spinner.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        spinner.setTop(20);
        spinner.setBottom(20);
        spinner.setPadding(10, 10, 10, 10);
        spinner.setPopupBackgroundDrawable(new ColorDrawable(Colors.getThemeBackground()));
        ArrayList arrayList = new ArrayList(linkedHashMap.keySet());
        ArrayAdapter arrayAdapter = new ArrayAdapter(context, Constants.SPINNER_LAYOUT, arrayList);
        arrayAdapter.setDropDownViewResource(Constants.SPINNER_DROP_DOWN_LAYOUT);
        spinner.setAdapter((SpinnerAdapter) arrayAdapter);
        SimpleSpinner simpleSpinner = new SimpleSpinner(spinner, str);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = (String) parent.getItemAtPosition(position);
                simpleSpinner.setSelectedKey(str);
                Log.e(SimpleSpinner.TAG, "newValue = " + str);
                Prefs.setString(PreferenceKeys.DEFAULT_TRANSLATE_FROM_KEY, str);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        Log.e(TAG, "selection index = " + arrayList.indexOf(str));
        spinner.setSelection((StringUtils.isEmpty(str) || !arrayList.contains(str)) ? 0 : arrayList.indexOf(str));
        return simpleSpinner;
    }

    public String getSelectedKey() {
        return this.selectedKey;
    }

    public Spinner getSpinner() {
        return this.spinner;
    }

    public void setSelectedKey(String str) {
        this.selectedKey = str;
    }

    @NonNull
    public String toString() {
        return "SimpleSpinner{spinner=" + this.spinner + ", selectedKey='" + this.selectedKey + "'}";
    }
}
