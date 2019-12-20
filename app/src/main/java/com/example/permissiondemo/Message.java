package com.example.permissiondemo;

import com.google.gson.annotations.SerializedName;

public class Message {

    public static final String MSGTYPE_TEXT = "m.text";
    public static final String MSGTYPE_EMOTE = "m.emote";
    public static final String MSGTYPE_NOTICE = "m.notice";
    public static final String MSGTYPE_IMAGE = "m.image";
    public static final String MSGTYPE_AUDIO = "m.audio";
    public static final String MSGTYPE_VIDEO = "m.video";
    public static final String MSGTYPE_LOCATION = "m.location";
    public static final String MSGTYPE_FILE = "m.file";
    public static final String FORMAT_MATRIX_HTML = "org.matrix.custom.html";

    // Define here a file scheme used to detect the local urls by using "startsWith()".
    // Consider only one '/' because of some Android devices which not use double '/'.
    public static final String FILE_SCHEME = "file:/";

    // Add, in local, a fake message type in order to StickerMessage can inherit Message class
    // Because sticker isn't a message type but a event type without msgtype field
    public static final String MSGTYPE_STICKER_LOCAL = "org.matrix.android.sdk.sticker";

    public String msgtype;
    public String body;

    public String format;
    public String formatted_body;
    public int is_encry = 0;

    @SerializedName("m.relates_to")
    public RelatesTo relatesTo;
}
