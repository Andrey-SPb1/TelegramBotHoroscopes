package org.bot.service.enums;

import java.util.Arrays;
import java.util.Optional;

public enum MyMonths {

    JANUARY ("январь", "января"),
    FEBRUARY ("февраль", "февраля"),
    MARCH ("март","марта"),
    APRIL ("апрель", "апреля"),
    MAY ("май", "мая"),
    JUNE ("июнь", "июня"),
    JULY ("июль", "июля"),
    AUGUST ("август", "августа"),
    SEPTEMBER ("сентябрь", "сентября"),
    OCTOBER ("октябрь", "октября"),
    NOVEMBER ("ноябрь", "ноября"),
    DECEMBER ("декабрь", "декабря");
    private final String firstTitle;
    private final String secondTitle;

    MyMonths(String firstTitle, String secondTitle) {
        this.firstTitle = firstTitle;
        this.secondTitle = secondTitle;
    }

    public static boolean isMonth(String str) {
        boolean byFirstTitle = Arrays.stream(MyMonths.values())
                .anyMatch(month -> month.getFirstTitle().equalsIgnoreCase(str));

        boolean bySecondTitle = Arrays.stream(MyMonths.values())
                .anyMatch(month -> month.getSecondTitle().equalsIgnoreCase(str));

        boolean byName = Arrays.stream(MyMonths.values())
                .anyMatch(month -> month.toString().equalsIgnoreCase(str));

        return byFirstTitle || bySecondTitle || byName;
    }

    public static int getNumberOfMonth(String str) {
        Optional<Integer> first = Arrays.stream(MyMonths.values())
                .filter(month -> month.getFirstTitle().equalsIgnoreCase(str))
                .map(month -> month.ordinal() + 1)
                .findFirst();

        Optional<Integer> second = Arrays.stream(MyMonths.values())
                .filter(month -> month.getSecondTitle().equalsIgnoreCase(str))
                .map(month -> month.ordinal() + 1)
                .findFirst();

        Optional<Integer> third = Arrays.stream(MyMonths.values())
                .filter(month -> month.toString().equalsIgnoreCase(str))
                .map(month -> month.ordinal() + 1)
                .findFirst();

        return first.orElseGet(() -> second.orElseGet(() -> third.orElse(0)));
    }

    public String getFirstTitle() {
        return firstTitle;
    }

    public String getSecondTitle() {
        return secondTitle;
    }
}
