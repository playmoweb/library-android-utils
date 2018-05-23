package com.playmoweb.android.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * StringUtilsTest
 *
 * @author Playmoweb
 */
public class StringUtilsTest {

    @Test
    public void testImplodeOne() {
        String result = StringUtils.implode(", ", "test1");
        assertEquals("test1", result);
    }

    @Test
    public void testImplodeTwo() {
        String result = StringUtils.implode(", ", "test1", "test2");
        assertEquals("test1, test2", result);
    }

    @Test
    public void testImplodeList() {
        List<String> strings = new ArrayList<>(2);
        strings.add("test1");
        strings.add("test2");
        String result = StringUtils.implode(", ", strings);
        assertEquals("test1, test2", result);
    }

    @Test
    public void testImplodeWithNull() {
        String result = StringUtils.implode(", ", "test1", null, "test2");
        assertEquals("test1, test2", result);
    }
}
