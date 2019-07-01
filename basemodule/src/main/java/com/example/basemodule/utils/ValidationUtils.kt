package com.example.basemodule.utils

import android.util.Patterns
import java.io.File

class ValidationUtils {
    companion object {
        fun isValidOTP(otp: String): Boolean {
            return otp.length > 2
        }

        fun isValidEmail(email: String?): Boolean {
            return email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun isValidName(name: String): Boolean {
            return name.length > 2
        }

        fun isValidPassword(password: String): Boolean {
            return password.length > 5
        }

        fun isValidPhoneNumber(phone: String?): Boolean {
            return phone != null && Patterns.PHONE.matcher(phone).matches()
        }

        fun isValidAddress(address: String): Boolean {
            return address.length > 2
        }

        fun isValidCountry(country: String): Boolean {
            return country.length > 2
        }

        fun isValidState(state: String): Boolean {
            return state.length > 1
        }

        fun isValidCity(city: String): Boolean {
            return city.length > 1
        }


        fun isValidZipCode(zip: String): Boolean {
            try {
                Integer.parseInt(zip)
            } catch (e: Exception) {
                return false
            }

            return zip.length == 6
        }

        fun isValidFileSize(file: File): Boolean {
            val length: Long
            try {
                length = file.length() / 1024
                println("File size in kb : $length")
            } catch (e: Exception) {
                return false
            }

            return length <= 2000
        }

        fun isValidNumberString(numberString: String): Boolean {
            try {
                Integer.parseInt(numberString)
            } catch (e: Exception) {
                return false
            }

            return true
        }

        fun isValidFloatString(numberString: String): Boolean {
            try {
                java.lang.Float.parseFloat(numberString)
            } catch (e: Exception) {
                return false
            }

            return true
        }

    }
}