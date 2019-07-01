package com.example.basemodule.utils

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.view.ViewConfiguration


class LoadingUtils {
    companion object {
        fun hideProgressBar(context: Context) {
            if (context == null || context !is Activity) {
                return
            }
            val layout = context.findViewById<View>(android.R.id.content).rootView as ViewGroup
            if (layout != null && layout.childCount > 0) {
                for (i in 0 until layout.childCount) {
                    val v = layout.getChildAt(i) ?: return
                    val o = v.tag
                    if (o != null && o is String) {
                        if (o.equals("#\$UniqueProgressBar", ignoreCase = true) && v is RelativeLayout) {
                            if (v.childCount > 0 && v.getChildAt(0) is ProgressBar) {
                                v.removeAllViews()
                                layout.removeView(v)
                            }
                            break
                        }
                    }
                }
            }

        }

        fun showProgressBar(context: Context, gravity: Int?) {
            if (context == null || context !is Activity) {
                return
            }
            // true if physical, false if virtual
            val permanentMenuKey =
                ViewConfiguration.get(context).hasPermanentMenuKey()
            val layout = context.findViewById<View>(android.R.id.content).rootView as ViewGroup
            val progressBar = ProgressBar(context)
            progressBar.isIndeterminate = true
            val params = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
            )
            val rl = RelativeLayout(context)
            rl.tag = "#\$UniqueProgressBar"
            val params2 = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                150
            )
            if (!permanentMenuKey) {
                params2.setMargins(0, 0, 0, 10 + Utils.getSoftButtonsBarHeight(context));
            }
            if (gravity == null) {
                rl.gravity = Gravity.CENTER
            } else {
                rl.gravity = gravity
            }

            rl.addView(progressBar, params2)

            layout.addView(rl, params)
        }
    }


}