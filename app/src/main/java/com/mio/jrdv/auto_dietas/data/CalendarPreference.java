package com.mio.jrdv.auto_dietas.data;

import android.content.ContentResolver;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.provider.CalendarContract;
import android.util.Log;

import com.mio.jrdv.auto_dietas.R;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by joseramondelgado on 17/03/15.
 */
public class CalendarPreference extends PreferenceActivity {
    private static final String CALENDAR_ID = "calendarId";
    private static final String[] PROJECTION = new String[] { CalendarContract.Calendars._ID,
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, CalendarContract.Calendars.CALENDAR_COLOR };
    private Set<String> initialActiveCalendars;

    CheckBoxPreference mCheckBoxPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.calendaraccounts);
        SharedPreferences prefs = getPreferenceManager().getSharedPreferences();
        initialActiveCalendars = prefs.getStringSet("PREF_ACTIVE_CALENDARS", null);
        populatePreferenceScreen(initialActiveCalendars);


    }

    private void populatePreferenceScreen(Set<String> activeCalendars) {
        Cursor cursor = createLoadedCursor();
        if (cursor == null) {
            return;
        }
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            CheckBoxPreference checkboxPref = new CheckBoxPreference(this);
            checkboxPref.setTitle(cursor.getString(1));
            checkboxPref.setIcon(createDrawable(cursor.getInt(2)));
            int calendarId = cursor.getInt(0);
            checkboxPref.getExtras().putInt(CALENDAR_ID, calendarId);
            checkboxPref.setChecked(activeCalendars == null
                    || activeCalendars.contains(String.valueOf(calendarId)));
            getPreferenceScreen().addPreference(checkboxPref);
        }
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        return true;
    }


    private Cursor createLoadedCursor() {
        Uri.Builder builder = CalendarContract.Calendars.CONTENT_URI.buildUpon();
        ContentResolver contentResolver = getContentResolver();
        return contentResolver.query(builder.build(), PROJECTION, null, null, null);
    }

    @Override
    public void onPause() {
        super.onPause();
        HashSet<String> selectedCalendars = getSelectedCalenders();
        if (!selectedCalendars.equals(initialActiveCalendars)) {
            persistSelectedCalendars(selectedCalendars);
            Log.v("Selected Calendars", selectedCalendars.toString());
            //NewWidget.updateAllWidgets(this);
        }
    }

    private void persistSelectedCalendars(HashSet<String> prefValues) {
        SharedPreferences prefs = getPreferenceManager().getSharedPreferences();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putStringSet("PREF_ACTIVE_CALENDARS", prefValues);
        editor.commit();
    }

    private HashSet<String> getSelectedCalenders() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        int prefCount = preferenceScreen.getPreferenceCount();
        HashSet<String> prefValues = new HashSet<String>();
        for (int i = 0; i < prefCount; i++) {
            Preference pref = preferenceScreen.getPreference(i);
            if (pref instanceof CheckBoxPreference) {
                CheckBoxPreference checkPref = (CheckBoxPreference) pref;
                if (checkPref.isChecked()) {
                    prefValues.add(String.valueOf(checkPref.getExtras().getInt(CALENDAR_ID)));
                }
            }
        }
        return prefValues;
    }

    private Drawable createDrawable(int color) {
       // Drawable drawable = getResources().getDrawable(R.drawable.prefs_calendar_entry);
        Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher);
        drawable.setColorFilter(new LightingColorFilter(0x0, color));
        return drawable;
    }


}