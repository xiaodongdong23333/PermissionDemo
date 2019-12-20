package com.example.permissiondemo;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SMSUtil {

    private ContentResolver contentResolver;
    List<HashMap<String, String>> data=new ArrayList<HashMap<String, String>>();

    public SMSUtil(ContentResolver contentResolver){
        this.contentResolver = contentResolver;
    }

    public List doFunc() {
        Cursor c = contentResolver.query(Uri.parse("content://sms"), new String[]{"address"}, null, null, null);

        while (c.moveToNext()) {
            HashMap<String, String> map = new HashMap<String, String>();

            Log.e("address", c.getString(c.getColumnIndex("address")));

            map.put("address", c.getString(c.getColumnIndex("address")));
            map.put("body", "bodys");//c.getString(c.getColumnIndex("body"))

            data.add(map);
        }
        return data;
    }
}
