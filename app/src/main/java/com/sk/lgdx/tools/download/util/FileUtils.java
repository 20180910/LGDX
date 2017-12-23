package com.sk.lgdx.tools.download.util;

import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.File;

/**
 * Created by aspsine on 15-4-19.
 */
public class FileUtils {
    private static final String DOWNLOAD_DIR = "lgdxdownload";

    public static final File getDownloadDir() {
        /*if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return new File(context.getExternalCacheDir(), DOWNLOAD_DIR);
        }
        return new File(context.getCacheDir(), DOWNLOAD_DIR);*/
        File file = new File(Environment.getExternalStorageDirectory(), DOWNLOAD_DIR);
        if(!file.exists()){
            file.mkdirs();
        }
        return file;
    }

    public static final String getPrefix(@NonNull String fileName) {
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    public static final String getSuffix(@NonNull String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}