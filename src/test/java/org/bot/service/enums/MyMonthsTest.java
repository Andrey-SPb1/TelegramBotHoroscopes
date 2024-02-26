package org.bot.service.enums;

import org.junit.jupiter.api.Test;

import static org.bot.service.enums.MyMonths.*;
import static org.junit.jupiter.api.Assertions.*;

class MyMonthsTest {

    @Test
    void isMonthTest() {
        assertAll(
                () -> assertTrue(isMonth("январь")),
                () -> assertTrue(isMonth("Февраль")),
                () -> assertTrue(isMonth("МАРТ")),
                () -> assertTrue(isMonth("МАРТА")),
                () -> assertTrue(isMonth("Апреля")),
                () -> assertTrue(isMonth("july")),
                () -> assertTrue(isMonth("November")),
                () -> assertFalse(isMonth("")),
                () -> assertFalse(isMonth(null))
        );
    }

    @Test
    void getNumberOfMonthTest() {
        assertAll(
                () -> assertEquals(1, getNumberOfMonth( "January")),
                () -> assertEquals(2, getNumberOfMonth( "февраль")),
                () -> assertEquals(3, getNumberOfMonth( "МАРТ")),
                () -> assertEquals(4, getNumberOfMonth( "Апрель")),
                () -> assertEquals(0, getNumberOfMonth( "")),
                () -> assertEquals(0, getNumberOfMonth( null))
        );
    }
}