package com.sevenXnetworks.treasure.utils;

public class ArrayUtils {

    public static final String SEPARATOR = ",";

    public static String toString(Object[] array) {
        return toString(array, StringUtils.EMPTY, SEPARATOR);
    }

    public static String toString(Object[] array, String stringIfNull, String separator) {
        if (null == array) {
            return stringIfNull;
        }

        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (; i < array.length; i++) {
            builder.append(array[i]).append(separator);
        }
        if (i > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }

}
