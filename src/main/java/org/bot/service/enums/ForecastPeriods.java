package org.bot.service.enums;

import java.util.Arrays;

import static org.bot.util.StringUtil.capitalized;

public enum ForecastPeriods {
    YESTERDAY ("Вчера"),
    TODAY ("Сегодня"),
    TOMORROW ("Завтра"),
    WEEK ("Неделю"),
    MONTH ("Месяц"),
    YEAR ("Год");

    private final String title;

    ForecastPeriods(String title) {
        this.title = title;
    }

    public static boolean isPeriod(String period) {
        return Arrays.stream(ForecastPeriods.values())
                .anyMatch(sign -> sign.getTitle().equals(capitalized(period)));
    }

    public static String getForecastPeriodsInEnglish(String period) {
        return Arrays.stream(ForecastPeriods.values())
                .filter(sign -> sign.getTitle().equals(capitalized(period)))
                .findFirst()
                .map(forecastPeriod -> forecastPeriod.toString().toLowerCase()).orElse(null);
    }

    public String getTitle() {
        return title;
    }
}
