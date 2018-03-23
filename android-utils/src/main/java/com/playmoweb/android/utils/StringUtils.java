package com.playmoweb.android.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StringUtil
 *
 * @author Playmoweb
 */
public class StringUtils {

    public static String decodeUnicode(final String encoded) {
        final Pattern p = Pattern.compile("%u([0-9a-f]{4})", Pattern.CASE_INSENSITIVE);

        final Matcher m = p.matcher(encoded);
        final StringBuffer decoded = new StringBuffer(encoded.length());

        while (m.find()) {
            m.appendReplacement(decoded, Character.toString((char) Integer.parseInt(m.group(1), 16)));
        }

        m.appendTail(decoded);
        return decoded.toString();
    }

    public static String capitalizeFirstLetter(final String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }

    public static String implode(final String separator, final String... data) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length - 1; i++) {
            //data.length - 1 => to not add separator at the end
            if (!data[i].matches(" *")) {//empty string are ""; " "; "  "; and so on
                sb.append(data[i]);
                sb.append(separator);
            }
        }
        sb.append(data[data.length - 1].trim());
        return sb.toString();
    }
}
