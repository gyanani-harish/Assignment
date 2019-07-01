package com.example.basemodule.utils

import android.content.Context
import android.graphics.Point
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.IdRes
import com.example.basemodule.base.HeightWidthData


public class ViewUtils {
    companion object {
        fun getButton(contentView: View, @IdRes id: Int): Button {
            val view = getView(contentView, id)
            if (view is Button)
                return view
            else if (view is TextView)
                throw IllegalArgumentException("this view is a textview insteadof Button")
            else
                throw IllegalArgumentException("this view is not a button")
        }


        fun getEditText(contentView: View, @IdRes id: Int): EditText {
            val view = getView(contentView, id)
            if (view is EditText)
                return view
            else if (view is TextView)
                throw IllegalArgumentException("this view is a textview insteadof EditText")
            else
                throw IllegalArgumentException("this view is not a edittext")
        }

        fun getTextView(contentView: View, @IdRes id: Int): TextView {
            if (contentView == null)
                throw NullPointerException("contentView is null")

            val view = getView(contentView, id)
            return view as? TextView ?: throw IllegalArgumentException("this view is not a textview")
        }

        fun getView(contentView: View, @IdRes id: Int): View {
            return contentView.findViewById(id)
                ?: throw IllegalArgumentException("wrong id. contentView does not contain this view")
        }
        @JvmStatic
        fun getViewHeightWidth(view: View): HeightWidthData {
            val wm = view.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay

            val deviceWidth: Int

            val size = Point()
            display.getSize(size)
            deviceWidth = size.x


            val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(deviceWidth, View.MeasureSpec.AT_MOST)
            val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            view.measure(widthMeasureSpec, heightMeasureSpec)
            val p = HeightWidthData(size.y.toDouble(), size.x.toDouble());
            return p
        }
    }
}