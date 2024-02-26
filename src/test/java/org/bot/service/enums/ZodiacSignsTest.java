package org.bot.service.enums;

import org.junit.jupiter.api.Test;

import static org.bot.service.enums.ZodiacSigns.*;
import static org.junit.jupiter.api.Assertions.*;

class ZodiacSignsTest {

    @Test
    void isZodiacSignTest() {
        assertAll(
                () -> assertTrue(isZodiacSign("Дева")),
                () -> assertTrue(isZodiacSign("дева")),
                () -> assertTrue(isZodiacSign("ДЕВА")),
                () -> assertTrue(isZodiacSign("Лев")),
                () -> assertTrue(isZodiacSign("Телец")),
                () -> assertFalse(isZodiacSign(""))
        );
    }

    @Test
    void getZodiacSignByStrTest() {
        assertAll(
                () -> assertEquals(LEO, getZodiacSignByStr("Лев")),
                () -> assertEquals(LEO, getZodiacSignByStr("лев")),
                () -> assertEquals(LEO, getZodiacSignByStr("ЛЕВ")),
                () -> assertEquals(VIRGO, getZodiacSignByStr("Дева")),
                () -> assertEquals(TAURUS, getZodiacSignByStr("Телец")),
                () -> assertNull(getZodiacSignByStr("")),
                () -> assertNull(getZodiacSignByStr(null))
        );
    }

    @Test
    void getZodiacSignInEnglishTest() {
        assertAll(
                () -> assertEquals("scorpio", getZodiacSignInEnglish("Скорпион")),
                () -> assertEquals("scorpio", getZodiacSignInEnglish("скорпион")),
                () -> assertEquals("scorpio", getZodiacSignInEnglish("СКОРПИОН")),
                () -> assertEquals("gemini", getZodiacSignInEnglish("Близнецы")),
                () -> assertEquals("libra", getZodiacSignInEnglish("Весы")),
                () -> assertNull(getZodiacSignInEnglish("")),
                () -> assertNull(getZodiacSignInEnglish(null))
        );
    }
}