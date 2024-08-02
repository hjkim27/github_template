package com.github.hjkim27.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hjkim27
 * @since 24.08.02
 */
public class FormatUtil {

    /**
     * <pre>
     *     List to String
     * </pre>
     *
     * @param list
     * @param delimiter
     * @return
     */
    public static String listToString(List<?> list, String delimiter) {
        if (list != null) {
            return list.stream().map(String::valueOf).collect(Collectors.joining(delimiter));
        } else {
            return null;
        }
    }

    /**
     * <pre>
     *     Objects to List
     * </pre>
     *
     * @param objs
     * @return
     */
    public static List<?> objectsToList(Object... objs) {
        return Arrays.stream(objs).toList();
    }

}
