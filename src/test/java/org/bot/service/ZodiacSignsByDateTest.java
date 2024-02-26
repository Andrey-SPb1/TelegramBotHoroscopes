package org.bot.service;

import org.junit.jupiter.api.*;

import static java.time.LocalDate.*;
import static org.bot.service.ZodiacSignsByDate.*;
import static org.bot.service.enums.ZodiacSigns.*;
import static org.junit.jupiter.api.Assertions.*;

public class ZodiacSignsByDateTest {

    @Nested
    @DisplayName("Test isDateFormat functionality")
    @Tag("is_date_format")
    class IsDateTest {
        @Test
        void isDateTest() {
            assertAll(
                    () -> assertTrue(isDateFormat("01.01.2000")),
                    () -> assertTrue(isDateFormat("01/01/2000")),
                    () -> assertTrue(isDateFormat("31.12.2024")),
                    () -> assertTrue(isDateFormat("12.31.2000")),
                    () -> assertTrue(isDateFormat("2000.31.12")),
                    () -> assertTrue(isDateFormat("31 декабря 2000")),
                    () -> assertTrue(isDateFormat("31 декабря")),
                    () -> assertTrue(isDateFormat("декабрь 31го")),
                    () -> assertTrue(isDateFormat("31.12")),
                    () -> assertTrue(isDateFormat("12.31"))
            );
        }

        @Test
        void failIfDateIsNotCorrect() {
            assertAll(
                    () -> assertFalse(isDateFormat("")),
                    () -> assertFalse(isDateFormat(null)),
                    () -> assertFalse(isDateFormat("3112"))
            );
        }
    }

    @Nested
    @DisplayName("Test getZodiacSignByDate functionality")
    @Tag("get_sign_by_date")
    class GetZodiacSignByDateTest {
        @Test
        void getZodiacSignByDateTest() {
            assertAll(
                    () -> assertEquals(LEO, getZodiacSignByDate(of(2000, 7, 25))),
                    () -> assertEquals(CAPRICORN, getZodiacSignByDate(of(2000, 12, 22))),
                    () -> assertEquals(CANCER, getZodiacSignByDate(of(2000, 7, 22))),
                    () -> assertEquals(PISCES, getZodiacSignByDate(of(2000, 3, 5))),
                    () -> assertEquals(TAURUS, getZodiacSignByDate(of(1990, 4, 28)))
            );
        }
    }

    @Nested
    @DisplayName("Test dateConvertFromString functionality")
    @Tag("date_convert")
    class DateConvertFromStringTest {
        @Test
        void dateConvertFromStringTest() {
            assertAll(
                    () -> assertEquals(of(2000, 1, 15), dateConvertFromString("15.01.2000")),
                    () -> assertEquals(of(2000, 1, 15), dateConvertFromString("2000.15.01")),
                    () -> assertEquals(of(2000, 1, 15), dateConvertFromString("01.15")),
                    () -> assertEquals(of(2000, 1, 1), dateConvertFromString("1.1")),
                    () -> assertEquals(of(2000, 1, 15), dateConvertFromString("1.15")),
                    () -> assertEquals(of(2000, 1, 15), dateConvertFromString("15.01")),
                    () -> assertEquals(of(2000, 1, 15), dateConvertFromString("15 01")),
                    () -> assertEquals(of(2000, 1, 15), dateConvertFromString("15/01")),
                    () -> assertEquals(of(2000, 1, 15), dateConvertFromString("15 января")),
                    () -> assertEquals(of(2000, 1, 15), dateConvertFromString("январь 15го")),
                    () -> assertEquals(of(2000, 1, 15), dateConvertFromString("15 января 2000")),
                    () -> assertEquals(of(2000, 1, 15), dateConvertFromString("январь 15го 2000"))
            );
        }

        @Test
        void nullIfDateIsNotCorrect() {
            assertAll(
                    () -> assertNull(dateConvertFromString("2000.13.15")),
                    () -> assertNull(dateConvertFromString("2000.12.32")),
                    () -> assertNull(dateConvertFromString("13.15")),
                    () -> assertNull(dateConvertFromString("12.32")),
                    () -> assertNull(dateConvertFromString("12.0")),
                    () -> assertNull(dateConvertFromString("30 февраля"))
            );
        }
    }
}
