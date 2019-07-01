package com.example.basemodule.utils;


import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.SOURCE)
@IntDef({ErrorType.NORMAL, ErrorType.WEBSERVICE_RESPONSE_ERROR,ErrorType.CLIENT_SIDE_ERROR})
public @interface ErrorType {
    public static final int NORMAL = 1;
    public static final int WEBSERVICE_RESPONSE_ERROR = 2;
    public static final int CLIENT_SIDE_ERROR=3;
}
