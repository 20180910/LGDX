package com.sk.lgdx.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.sk.lgdx.Constant;

import java.io.File;

import static com.sk.lgdx.tools.download.CrashHandler.TAG;

/**
 * Created by Administrator on 2017/12/23.
 */

public class DownloadBro extends BroadcastReceiver {
    private final String[][] MIME_MapTable = {
//{后缀名，MIME类型}
            {".3gp", "video/3gpp"},

            {".apk", "application/vnd.android.package-archive"},

            {".asf", "video/x-ms-asf"},

            {".avi", "video/x-msvideo"},

            {".bin", "application/octet-stream"},

            {".bmp", "image/bmp"},

            {".c", "text/plain"},

            {".class", "application/octet-stream"},

            {".conf", "text/plain"},

            {".cpp", "text/plain"},

            {".doc", "application/msword"},

            {".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},

            {".xls", "application/vnd.ms-excel"},

            {".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},

            {".exe", "application/octet-stream"},

            {".gif", "image/gif"},

            {".gtar", "application/x-gtar"},

            {".gz", "application/x-gzip"},

            {".h", "text/plain"},

            {".htm", "text/html"},

            {".html", "text/html"},

            {".jar", "application/java-archive"},

            {".java", "text/plain"},

            {".jpeg", "image/jpeg"},

            {".jpg", "image/jpeg"},

            {".js", "application/x-javascript"},

            {".log", "text/plain"},

            {".m3u", "audio/x-mpegurl"},

            {".m4a", "audio/mp4a-latm"},

            {".m4b", "audio/mp4a-latm"},

            {".m4p", "audio/mp4a-latm"},

            {".m4u", "video/vnd.mpegurl"},

            {".m4v", "video/x-m4v"},

            {".mov", "video/quicktime"},

            {".mp2", "audio/x-mpeg"},

            {".mp3", "audio/x-mpeg"},

            {".mp4", "video/mp4"},

            {".mpc", "application/vnd.mpohun.certificate"},

            {".mpe", "video/mpeg"},

            {".mpeg", "video/mpeg"},

            {".mpg", "video/mpeg"},

            {".mpg4", "video/mp4"},

            {".mpga", "audio/mpeg"},

            {".msg", "application/vnd.ms-outlook"},

            {".ogg", "audio/ogg"},

            {".pdf", "application/pdf"},

            {".png", "image/png"},

            {".pps", "application/vnd.ms-powerpoint"},

            {".ppt", "application/vnd.ms-powerpoint"},

            {".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"},

            {".prop", "text/plain"},

            {".rc", "text/plain"},

            {".rmvb", "audio/x-pn-realaudio"},

            {".rtf", "application/rtf"},

            {".sh", "text/plain"},

            {".tar", "application/x-tar"},

            {".tgz", "application/x-compressed"},

            {".txt", "text/plain"},

            {".wav", "audio/x-wav"},

            {".wma", "audio/x-ms-wma"},

            {".wmv", "audio/x-ms-wmv"},

            {".wps", "application/vnd.ms-works"},

            {".xml", "text/plain"},

            {".z", "application/x-compress"},

            {".zip", "application/x-zip-compressed"},

            {"", "*/*"}

    };

    @Override
    public void onReceive(Context context, Intent intent) {

        String path = intent.getStringExtra(Constant.IParam.path);
        String action = intent.getAction(); //动作
        String type="*/*";
        if (action.equals("download")) {
            for (int i = 0; i < MIME_MapTable.length; i++) {
                String str = MIME_MapTable[i][0].toLowerCase();
                if(path.toLowerCase().indexOf(str)>=0){
                    type=MIME_MapTable[i][1];
                    break;
                }
            }
            Log.i(TAG+"===","==="+type);
            Intent pathIntent = new Intent();
            pathIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            pathIntent.setAction(android.content.Intent.ACTION_VIEW);
            pathIntent.setDataAndType(Uri.fromFile(new File(path)), type);
            context.startActivity(pathIntent);

            /*
        new File(FileUtils.getDownloadDir()+"/"+info.getFileName());*/
        }
    }

}
