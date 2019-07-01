package com.example.basemodule.utils

import android.content.Context
import android.graphics.Typeface

class FontUtils {
    companion object{
        fun getTypefaceRobotoBold(context: Context): Typeface {
            return Typeface.createFromAsset(context.assets, "fonts/roboto_bold.TTF")
        }

        fun getTypefaceRobotoRegular(context: Context): Typeface {
            return Typeface.createFromAsset(context.assets, "fonts/roboto_regular.TTF")
        }

        fun getTypefaceRobotoLight(context: Context): Typeface {
            return Typeface.createFromAsset(context.assets, "fonts/roboto_light.TTF")
        }
    }
}