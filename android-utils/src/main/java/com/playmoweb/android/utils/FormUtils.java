package com.playmoweb.android.utils;

import android.support.annotation.StringRes;
import android.widget.EditText;

import java.util.regex.Pattern;

/**
 * FormUtil
 * <p>
 * All check* methods throw a typed custom exception.
 *
 * @author Playmoweb
 */
public class FormUtils {
    private static final Pattern emailPattern = Pattern.compile(
            "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$"
    );

    public static boolean isNotEmpty(final String value) {
        return value != null && !value.isEmpty();
    }

    public static boolean isNotEmpty(final EditText editText, @StringRes final int stringResourceIfError) {
        editText.setError(null);
        final boolean isValid = FormUtils.isNotEmpty(editText);
        if (!isValid) {
            editText.setError(editText.getContext().getString(stringResourceIfError));
        }
        return isValid;
    }

    public static boolean isNotEmpty(final EditText editText) {
        return FormUtils.isNotEmpty(editText.getText().toString());
    }

    public static void checkIfNotEmpty(final String value) throws FormException {
        if (!FormUtils.isNotEmpty(value)) {
            throw new EmptyStringException();
        }
    }

    public static boolean isValidEmailAddress(final String email) {
        return FormUtils.emailPattern.matcher(email).matches();
    }

    public static boolean isValidEmailAddress(final EditText editText, @StringRes final int stringResourceIfError) {
        final boolean isValid = FormUtils.isValidEmailAddress(editText);
        if (!isValid) {
            editText.setError(editText.getContext().getString(stringResourceIfError));
        }
        return isValid;
    }

    public static boolean isValidEmailAddress(final EditText editText) {
        return FormUtils.isValidEmailAddress(editText.getText().toString());
    }

    public static void checkValidEmailAddress(final String value) throws FormException {
        if (!FormUtils.isValidEmailAddress(value)) {
            throw new InvalidEmailAddressException();
        }
    }

    public static boolean isSameValue(final EditText firstEditText, final EditText secondEditText) {
        return firstEditText.getText().toString().equals(secondEditText.getText().toString());
    }

    public static boolean isSameValue(final EditText firstEditText, final EditText secondEditText, @StringRes final int stringResourceIfError) {
        final boolean isValid = FormUtils.isSameValue(firstEditText, secondEditText);
        if (!isValid) {
            secondEditText.setError(secondEditText.getContext().getString(stringResourceIfError));
        }
        return isValid;
    }

    public static boolean isLongerThan(final String value, final int minLength) {
        return value.length() >= minLength;
    }

    public static boolean isLongerThan(final EditText editText, final int minLength) {
        return FormUtils.isLongerThan(editText.getText().toString(), minLength);
    }

    public static void checkIsLongerThan(final String value, final int minLength) throws FormException {
        if (!FormUtils.isLongerThan(value, minLength)) {
            throw new StringTooShortException();
        }
    }

    public static class FormException extends Exception {
    }

    public static class EmptyStringException extends FormException {
    }

    public static class InvalidEmailAddressException extends FormException {
    }

    public static class StringTooShortException extends FormException {
    }
}
