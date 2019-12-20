package com.example.permissiondemo;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class RelatesTo {
    @SerializedName("m.in_reply_to")
    public Map<String, String> dict;
}
