package com.playmoweb.android.utils;

import android.content.Context;

/**
 * @author Thibaud Giovannetti
 * @date 20/04/2018
 */
public class DateUtils {
    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * DateUtils.SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * DateUtils.MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * DateUtils.HOUR_MILLIS;

    public static class TimeDiff {
        public final long diffMillis;
        public final int minutes;
        public final int hours;
        public final int days;

        public TimeDiff(final long timeMillis, final int minutes, final int hours, final int days) {
            diffMillis = timeMillis;
            this.minutes = minutes;
            this.hours = hours;
            this.days = days;
        }
    }

    public static String getTimeAgo(final Context context, final long from, final long toTime) {
        final TimeDiff timeDiff = DateUtils.getTimeDiff(from, toTime);
        if (timeDiff.minutes == 0) {
            return context.getString(R.string.library_android_utils_date_utils_just_now);
        } else if (timeDiff.minutes < 2) {
            return context.getString(R.string.library_android_utils_date_utils_a_minute_ago);
        } else if (timeDiff.minutes < 50) {
            return context.getString(R.string.library_android_utils_date_utils_n_minutes_ago, timeDiff.minutes);
        } else if (timeDiff.minutes < 90) {
            return context.getString(R.string.library_android_utils_date_utils_an_hour_ago);
        } else if (timeDiff.hours < 24) {
            return context.getString(R.string.library_android_utils_date_utils_n_hours_ago, timeDiff.hours);
        } else if (timeDiff.hours < 48) {
            return context.getString(R.string.library_android_utils_date_utils_yesterday);
        } else {
            return context.getString(R.string.library_android_utils_date_utils_n_days_ago, timeDiff.days);
        }
    }

    public static TimeDiff getTimeDiff(final long from, final long toTime) {
        final long to = toTime < 1000000000000L ? toTime * 1000 : toTime;
        final long diff = from - to;

        return new TimeDiff(
                diff,
                (int) (diff / DateUtils.MINUTE_MILLIS),
                (int) (diff / DateUtils.HOUR_MILLIS),
                (int) (diff / DateUtils.DAY_MILLIS)
        );
    }
}
