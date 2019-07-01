package com.example.basemodule.utils;


import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({CreditCardType.VISA, CreditCardType.MASTER_CARD, CreditCardType.AMERICAN_EXPRESS, CreditCardType.DISCOVER})
public @interface CreditCardType {
    public static final String VISA = "visa";
    public static final String MASTER_CARD = "mastercard";
    public static final String AMERICAN_EXPRESS = "amex";
    public static final String DISCOVER = "discover";

}
