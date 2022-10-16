package kr.project.shortlink.core.util;

import lombok.experimental.UtilityClass;

import java.util.Arrays;

@UtilityClass
public class Base62Util {

    private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    public static String encode(long value) {
        return encode(value, 3);
    }

    public static String encode(long value, int minSize) {

        final StringBuilder sb = new StringBuilder();

        while(minSize-- > 0 || value > 0) {
            sb.append(BASE62[(int) (value % (long) BASE62.length)]);
            value /= BASE62.length;
        }

        return sb.reverse().toString();
    }

    public static long decode(String value) {
        long result = 0;
        StringBuilder sb = new StringBuilder(value);

        while(sb.length() > 0) {
            result += ((long) Arrays.binarySearch(BASE62, sb.charAt(0))) * ((long) Math.pow(BASE62.length, sb.length() - 1));
            sb.deleteCharAt(0);
        }


        return result;
    }

}