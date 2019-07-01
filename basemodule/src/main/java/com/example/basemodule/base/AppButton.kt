package com.example.basemodule.base

import androidx.appcompat.widget.AppCompatButton


import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

public class AppButton : AppCompatButton {


    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}