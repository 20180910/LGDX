package com.sk.lgdx.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.sk.lgdx.Constant;
import com.sk.lgdx.tools.IntentUtils;

/**
 * Created by Administrator on 2017/12/23.
 */

public class DownloadBro extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction(); //动作

        if (action.equals("download")) {
            String path = intent.getStringExtra(Constant.IParam.path);

            IntentUtils.openFileIntent(context,path);

        }
    }

}
