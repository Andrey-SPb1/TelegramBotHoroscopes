package org.bot.service.enums;

import org.junit.jupiter.api.Test;

import static org.bot.service.enums.ForecastPeriods.*;
import static org.junit.jupiter.api.Assertions.*;

class ForecastPeriodsTest {

    @Test
    void isPeriodTest() {
        assertAll(
                () -> assertTrue(isPeriod("Вчера")),
                () -> assertTrue(isPeriod("вчера")),
                () -> assertTrue(isPeriod("ВЧЕРА")),
                () -> assertTrue(isPeriod("Год")),
                () -> assertFalse(isPeriod("")),
                () -> assertFalse(isPeriod(null))
        );
    }

    @Test
    void getForecastPeriodsInEnglishTest() {
        assertAll(
                () -> assertEquals("year", getForecastPeriodsInEnglish("Год")),
                () -> assertEquals("year", getForecastPeriodsInEnglish("год")),
                () -> assertEquals("year", getForecastPeriodsInEnglish("ГОД")),
                () -> assertEquals("tomorrow", getForecastPeriodsInEnglish("Завтра")),
                () -> assertEquals("today", getForecastPeriodsInEnglish("Сегодня")),
                () -> assertEquals("month", getForecastPeriodsInEnglish("Месяц")),
                () -> assertNull(getForecastPeriodsInEnglish("")),
                () -> assertNull(getForecastPeriodsInEnglish(null))
        );
    }
}