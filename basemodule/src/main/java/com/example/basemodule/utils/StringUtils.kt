package com.example.basemodule.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import com.example.basemodule.utils.LogUtils.log
import java.net.MalformedURLException
import java.net.URL

class StringUtils {
    companion object {
        fun equalsIgnoreCase(str1: String?, str2: String?): Boolean {
            if (str1 == null || str2 == null) {
                return false
            }
            return str1.equals(str2, true)
        }
        fun safeParseFloat(value: String): Float {
            return if (checkStringObject(value)) {
                try {
                    java.lang.Float.parseFloat(value)
                } catch (e: NumberFormatException) {
                    log(e)
                    Constants.DEFAULT_WRONG_ID_INT.toFloat()
                }

            } else {
                Constants.DEFAULT_WRONG_ID_INT.toFloat()
            }
        }
        fun checkStringObject(str: String): Boolean {
            if (TextUtils.isEmpty(str)) {
                log(IllegalArgumentException("String is null or empty"))
                return false
            }
            return true

        }
        fun checkValidURL(url: String): Boolean {
            try {
                if (checkStringObject(url)) {
                    val u = URL(url)
                } else {
                    return false
                }
            } catch (e: MalformedURLException) {
                log(e)
                return false
            } catch (e: Exception) {
                log(e)
                return false
            }

            return true
        }
        fun openLinkInBrowser(context: Context, url: String) {
            if (checkValidURL(url)) {
                try {
                    val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(myIntent)
                } catch (e: ActivityNotFoundException) {
                    log("No application can handle this request." + " Please install a webbrowser")

                }

            }
        }
        fun safeParseInt(value: String): Int {
            return if (checkStringObject(value)) {
                try {
                    Integer.parseInt(value)
                } catch (e: NumberFormatException) {
                    log(e)
                    Constants.DEFAULT_WRONG_ID_INT
                }

            } else {
                Constants.DEFAULT_WRONG_ID_INT
            }
        }
        fun safeParseLong(value: String): Long {
            return if (checkStringObject(value)) {
                try {
                    java.lang.Long.parseLong(value)
                } catch (e: NumberFormatException) {
                    log(e)
                    Constants.DEFAULT_WRONG_ID_INT.toLong()
                }

            } else {
                Constants.DEFAULT_WRONG_ID_INT.toLong()
            }
        }
        fun removeCommaFromLast(s: StringBuilder): StringBuilder {
            return if (s[s.length - 1] == ',') s.deleteCharAt(s.length - 1) else s
        }
        fun checkIdValidation(id: Long): Boolean {
            return if (id > 0) true else false
        }

    }
}
