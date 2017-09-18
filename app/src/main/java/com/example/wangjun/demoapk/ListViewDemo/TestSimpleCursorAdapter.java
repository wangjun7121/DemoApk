package com.example.wangjun.demoapk.ListViewDemo;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjun on 2017/9/15.
 */

public class TestSimpleCursorAdapter extends Activity {
    private static String sLogcatTAG = "TestSimpleCursorAdapter";

    private ListView contactsListView;
    ArrayAdapter<String> contactsAdapter;
    List<String> contactsList = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);


        contactsListView = new ListView(this);
        contactsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactsList);
        contactsListView.setAdapter(contactsAdapter);

        Cursor cursor = null;
        int iTemp = 0;
        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null, null);
            while (cursor.moveToNext()) {
                String displayName = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactsList.add(displayName + "\n" + number);
                Log.d(sLogcatTAG,contactsList.get(iTemp));
                iTemp++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        setContentView(contactsListView);
    }


}