package org.bot.util;

import java.util.stream.Stream;

public class StringUtil {
    public static String capitalized(String word) {
        return word != null && word.length() > 1 ? Stream.of(word)
                .map(String::toLowerCase)
                .map(it -> it.substring(0, 1).toUpperCase() + it.substring(1))
                .findFirst()
                .get() : null;
    }
}
