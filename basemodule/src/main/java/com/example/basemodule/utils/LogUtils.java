package com.example.basemodule.utils;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogUtils {
    public static void log(String path, String tag, String s, @ErrorType int error) {
        Log.d(tag, s);
        if (path == null) {
            new NullPointerException("sExternalStorageDirectory is null")
                    .printStackTrace();
            return;
        }
        String fileName = "logs";
        String fileExtension = ".txt";
        if (error == ErrorType.WEBSERVICE_RESPONSE_ERROR) {
            fileName = "web_api_error_log";
        } else if (error == ErrorType.CLIENT_SIDE_ERROR) {
            fileName = "client_log";
        }
        File f = new File(path, fileName + fileExtension);
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            BufferedWriter bfw = new BufferedWriter(new FileWriter(f, true));
            bfw.append(tag + "->" + s);
            bfw.newLine();
            bfw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void log(Exception e) {
        //TODO implement
    }

    public static void log(String s) {
        //TODO implement
    }
}
