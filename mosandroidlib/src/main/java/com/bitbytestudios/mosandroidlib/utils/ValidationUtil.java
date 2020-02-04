package com.bitbytestudios.mosandroidlib.utils;

/**
 *
 */
public class ValidationUtil {

    public static final String TAG = ValidationUtil.class.getCanonicalName();

    /**
     * returns true if email is valid
     * else returns false if email is null, empty or invalid
     *
     * @param emailAddress
     * @return
     */
    public static boolean isValidEmail(final String emailAddress) {

        if (emailAddress == null || emailAddress.isEmpty()) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches();
        }
    }

    /**
     * checks if a string is empty, null string or null object
     *
     * @param value
     * @return
     */
    public static boolean isEmptyOrNull(String value) {

        if (value == null)
            return true;

        if (value.isEmpty())
            return true;

        if (value.equals("null"))
            return true;

        if (value.equals("#"))
            return true;

        return false;
    }

    /**
     * return true if password is null or length is less than 5
     *
     * @param string
     * @return
     */
    public static boolean isPasswordShort(final String string) {
        if (string == null || string.length() < 5) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * return true if phone number is null or length is less than 6
     *
     * @param string
     * @return
     */
    public static boolean isPhoneNumberShort(final String string) {
        if (string == null || string.length() < 6) {
            return true;
        } else {
            return false;
        }
    }
}
