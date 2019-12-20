package com.example.permissiondemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String TAG = "MainActivity";
    public List<ContactsBean> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startServer = findViewById(R.id.Permission);
        startServer.setOnClickListener(this);
    }

    @Override
        public void onClick(View view) {

        //第一部分，获取通讯录相关信息
        /*if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
        }
        ContactUtil contactUtil = new ContactUtil(getContentResolver());
        Message m = contactUtil.doFunction();*/

        //第二部分，获取短信相关信息
        /*if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    MainActivity.this, new String[]{Manifest.permission.READ_SMS}, 1);
        }
        SMSUtil smsUtil = new SMSUtil(getContentResolver());
        List a = smsUtil.doFunc();*/

        //第三部分，获取相册所有图片路径
        /*if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        List<String> list = getGalleryPhotos(getContentResolver());
        for(String s : list){
            System.out.println(s);
        }*/

    }

    //获取所有图片存入list集合返回,MediaStore.Images.Media.DATA中的Images
    public static ArrayList<String> getGalleryPhotos(ContentResolver resolver) {
        ArrayList<String> galleryList = new ArrayList<String>();

        try {
            //获取所在相册和相册id
            final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
            //按照id排序
            final String orderBy = MediaStore.Images.Media._ID;

            //相当于sql语句默认升序排序orderBy，如果降序则最后一位参数是是orderBy+" desc "
            Cursor imagecursor =
                    resolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
                            null, orderBy);

            //从数据库中取出图存入list集合中
            if (imagecursor != null && imagecursor.getCount() > 0) {
                while (imagecursor.moveToNext()) {
                    int dataColumnIndex = imagecursor.getColumnIndex(MediaStore.Images.Media.DATA);
//                    Log.d("tgw7", "getGalleryPhotos: " + dataColumnIndex);
                    String path = "file://" + imagecursor.getString(dataColumnIndex);
                    Log.d("tgw5", "getGalleryPhotos: " + path);
                    galleryList.add(path);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 进行反转集合
        Collections.reverse(galleryList);
        return galleryList;
    }
}
