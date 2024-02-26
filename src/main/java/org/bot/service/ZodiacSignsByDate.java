package org.bot.service;

import org.bot.service.enums.MyMonths;
import org.bot.service.enums.ZodiacSigns;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.bot.service.enums.ZodiacSigns.*;

public class ZodiacSignsByDate {

    private static final Pattern dateOfNum =
            Pattern.compile("(\\d{1,2})\\D(\\d{1,2})"); // dd.MM.?
    private static final Pattern dateOfNumYearAtFirst =
            Pattern.compile("(\\d{2,4})\\D(\\d{1,2})\\D(\\d{1,2})"); // yyyy.MM.dd
    private static final Pattern monthAtEnd =
            Pattern.compile("(\\d{1,2})\\D([А-Яа-яA-Za-z]{3,})"); // ?.dd.MM(word).?
    private static final Pattern monthAtFirst =
            Pattern.compile("([А-Яа-яA-Za-z]{3,})\\D(\\d{1,2})"); // ?.MM(word).dd.?

    private static final Logger log = LoggerFactory.getLogger(ZodiacSignsByDate.class);

    public static boolean isDateFormat(String message) {
        return message != null && (dateOfNum.matcher(message).find() || dateOfNumYearAtFirst.matcher(message).find() ||
                monthAtEnd.matcher(message).find() || monthAtFirst.matcher(message).find());
    }

    public static ZodiacSigns getZodiacSignByDate(LocalDate date) {
        if(date == null) {
            return null;
        }
//        Водолей / aquarius (21 января – 20 февраля)
        if(isWithinRange(date, 1, 20, 2, 21)) return AQUARIUS;
//        Рыбы / pisces (21 февраля – 20 марта)
        else if (isWithinRange(date, 2, 20, 3, 21)) return PISCES;
//        Овен / aries (21 марта – 20 апреля)
        else if (isWithinRange(date, 3, 20, 4, 21)) return ARIES;
//        Телец / taurus (21 апреля – 20 мая)
        else if (isWithinRange(date, 4, 20, 5, 21)) return TAURUS;
//        Близнецы / gemini (21 мая – 21 июня)
        else if (isWithinRange(date, 5, 20, 6, 22)) return GEMINI;
//        Рак / cancer (22 июня – 22 июля)
        else if (isWithinRange(date, 6, 21, 7, 23)) return CANCER;
//        Лев / leo (23 июля – 23 августа)
        else if (isWithinRange(date, 7, 22, 8, 24)) return LEO;
//        Дева / virgo (24 августа – 23 сентября)
        else if (isWithinRange(date, 8, 23, 9, 24)) return VIRGO;
//        Весы / libra (24 сентября – 23 октября)
        else if (isWithinRange(date, 9, 23, 10, 24)) return LIBRA;
//        Скорпион / scorpio (24 октября – 22 ноября)
        else if (isWithinRange(date, 10, 23, 11, 23)) return SCORPIO;
//        Стрелец / sagittarius (23 ноября – 21 декабря)
        else if (isWithinRange(date, 11, 22, 12, 22)) return SAGITTARIUS;
//        Козерог / capricorn (22 декабря – 20 января)
        else return CAPRICORN;
    }

    private static boolean isWithinRange(LocalDate date, int monthAfter, int dayAfter, int monthBefore, int dayBefore) {
        return date.isAfter(LocalDate.of(date.getYear(), monthAfter,dayAfter)) &&
                date.isBefore(LocalDate.of(date.getYear(), monthBefore, dayBefore));
    }

    public static LocalDate dateConvertFromString(String message) {
        LocalDate localDate;
        int numOfMonth = 0;
        int numOfDay = 0;

        Matcher dateOfNumMatcher = dateOfNum.matcher(message);
        if(dateOfNumMatcher.find()) {
            int group1 = Integer.parseInt(dateOfNumMatcher.group(1));
            int group2 = Integer.parseInt(dateOfNumMatcher.group(2));
            if(group1 != 0 && group2 != 0) {
                if(group1 <= 31 && group2 <= 12) {
                    numOfDay = group1;
                    numOfMonth = group2;
                } else if(group2 <= 31 && group1 <= 12) {
                    numOfDay = group2;
                    numOfMonth = group1;
                }
            } else {
                Matcher dateOfNumYearAFMatcher = dateOfNumYearAtFirst.matcher(message);
                if (dateOfNumYearAFMatcher.find()) {
                    int groupY2 = Integer.parseInt(dateOfNumYearAFMatcher.group(2));
                    int groupY3 = Integer.parseInt(dateOfNumYearAFMatcher.group(3));
                    if (groupY3 <= 31 && groupY2 <= 12) {
                        numOfDay = groupY3;
                        numOfMonth = groupY2;
                    } else if (groupY2 <= 31 && groupY3 <= 12) {
                        numOfDay = groupY2;
                        numOfMonth = groupY3;
                    }
                }
            }
        } else {
            Matcher monthAtEndMatcher = monthAtEnd.matcher(message);
            Matcher monthAtFirstMatcher = monthAtFirst.matcher(message);
            if (monthAtEndMatcher.find()) {
                int group1 = Integer.parseInt(monthAtEndMatcher.group(1));
                String group2 = monthAtEndMatcher.group(2);
                if(group1 <= 31 && MyMonths.isMonth(group2)) {
                    numOfDay = group1;
                    numOfMonth = MyMonths.getNumberOfMonth(group2);
                }
            } else if (monthAtFirstMatcher.find()) {
                String group1 = monthAtFirstMatcher.group(1);
                int group2 = Integer.parseInt(monthAtFirstMatcher.group(2));
                if (group2 <= 31 && MyMonths.isMonth(group1)) {
                    numOfDay = group2;
                    numOfMonth = MyMonths.getNumberOfMonth(group1);
                }
            }
        }

        try {
            localDate = LocalDate.of(2000, numOfMonth, numOfDay);
        } catch (DateTimeException e) {
            localDate = null;
            log.error("Date error: {}", e.toString());
            e.printStackTrace();
        }
        return localDate;
    }
}
