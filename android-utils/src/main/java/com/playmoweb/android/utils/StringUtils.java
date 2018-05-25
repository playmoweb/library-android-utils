package com.playmoweb.android.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spanned;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StringUtil
 *
 * @author Playmoweb
 */
public class StringUtils {

    /**
     * Returns true if the string is null or 0-length.
     *
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isEmpty(@Nullable CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static String decodeUnicode(@NonNull final String encoded) {
        final Pattern p = Pattern.compile("%u([0-9a-f]{4})", Pattern.CASE_INSENSITIVE);

        final Matcher m = p.matcher(encoded);
        final StringBuffer decoded = new StringBuffer(encoded.length());

        while (m.find()) {
            m.appendReplacement(decoded, Character.toString((char) Integer.parseInt(m.group(1), 16)));
        }

        m.appendTail(decoded);
        return decoded.toString();
    }

    public static String capitalizeFirstLetter(@Nullable final String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }

    public static String implode(final String separator, final Collection<String> data) {
        return implode(separator, ListUtils.toArray(data, String.class));
    }

    public static String implode(final String separator, final String... data) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            if (!isEmpty(data[i]) && !data[i].matches(" *")) {//empty string are ""; " "; "  "; and so on
                if (!isEmpty(sb.toString())) {
                    sb.append(separator);
                }
                sb.append(data[i]);
            }
        }
        return sb.toString();
    }

    public static Spanned getHtml(@NonNull final String text) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(text);
        }
    }
}
