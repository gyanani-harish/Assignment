package com.example.basemodule.utils

public class CardUtils {
    companion object{
        /**
         * rules (@link https://creditcardjs.com/credit-card-type-detection)
         *
         * @param accNo
         * @return
         */
        @CreditCardType
        @JvmStatic
        fun getCreditCardType(accNo: String): String {

            val visaPattern = "^4.*$"
            val masterCardPattern = "^5[0-5].*$"
            val americalExpressPattern = "^3[47].*$"
            val discoverPattern = "^6[0245].*$"
            return if (accNo.matches(visaPattern.toRegex()))
                CreditCardType.VISA
            else if (accNo.matches(masterCardPattern.toRegex()))
                CreditCardType.MASTER_CARD
            else if (accNo.matches(americalExpressPattern.toRegex()))
                CreditCardType.AMERICAN_EXPRESS
            else
                CreditCardType.DISCOVER
        }
    }
}