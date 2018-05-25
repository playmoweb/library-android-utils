package com.playmoweb.android.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Thibaud Giovannetti
 * @date 23/03/2018
 */

public class ListUtils {

    public interface Predicate<T> {
        boolean test(T obj);
    }

    public interface Function<T, S> {
        S execute(T obj);
    }

    public static <T> List<T> emptyIfNull(final List<T> iterable) {
        return iterable == null ? Collections.<T>emptyList() : iterable;
    }

    public static <T> boolean isEmptyOrNull(final Iterable<T> iterable) {
        return iterable == null || !iterable.iterator().hasNext();
    }

    public static <T> List<T> filter(final List<T> listToFilter, final Predicate<T> predicate) {
        if (!ListUtils.isEmptyOrNull(listToFilter)) {
            final List<T> collection = new ArrayList<>();
            for (final T item : listToFilter) {
                if (predicate.test(item)) {
                    collection.add(item);
                }
            }
            return collection;
        }
        return listToFilter;
    }

    public static <T> T getOne(final List<T> listToFilter, final Predicate<T> predicate) {
        if (!ListUtils.isEmptyOrNull(listToFilter)) {
            for (final T item : listToFilter) {
                if (predicate.test(item)) {
                    return item;
                }
            }
        }
        return null;
    }

    public static <T, S> List<S> map(final Iterable<T> iterable, final Function<T, S> func) {
        final List<S> output = new ArrayList<>();
        for (final T t : iterable) {
            output.add(func.execute(t));
        }
        return output;
    }

    public static <T, S> List<S> map(final T[] iterable, final Function<T, S> func) {
        final List<S> output = new ArrayList<>();
        for (final T t : iterable) {
            output.add(func.execute(t));
        }
        return output;
    }

    public static List<Integer> range(final int from, final int to) {
        final List<Integer> output = new ArrayList<>();
        if (from > to) {
            for (int i = from; i >= to; i--) {
                output.add(i);
            }
        } else {
            for (int i = from; i < to; i++) {
                output.add(i);
            }
        }
        return output;
    }

    public static <T> T[] toArray(Collection<? extends T> itr, Class<T> type) {
        return itr.toArray(newArray(type, itr.size()));
    }

    public static <T> T[] newArray(Class<T> type, int length) {
        return (T[]) Array.newInstance(type, length);
    }

    public static <T> List<T> fromArray(final T[] array) {
        return Arrays.asList(array);
    }
}
