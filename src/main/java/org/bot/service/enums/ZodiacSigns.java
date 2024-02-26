package org.bot.service.enums;

import java.util.Arrays;

import static org.bot.util.StringUtil.capitalized;

public enum ZodiacSigns {

    ARIES ("Овен","Овна", "&#9800;"),
    TAURUS ("Телец","Тельца", "&#9801;"),
    GEMINI ("Близнецы","Близнецов","&#9802;"),
    CANCER ("Рак","Рака","&#9803;"),
    LEO ("Лев","Льва","&#9804;"),
    VIRGO ("Дева","Девы","&#9805;"),
    LIBRA ("Весы","Весов","&#9806;"),
    SCORPIO ("Скорпион","Скорпиона","&#9807;"),
    SAGITTARIUS ("Стрелец","Стрельца","&#9808;"),
    CAPRICORN ("Козерог","Козерога","&#9809;"),
    AQUARIUS ("Водолей","Водолея","&#9810;"),
    PISCES ("Рыбы","Рыбы","&#9811;");

    private final String title;
    private final String secondTitle;
    private final String emojiCode;

    ZodiacSigns(String title, String secondTitle, String code) {
        this.title = title;
        this.secondTitle = secondTitle;
        this.emojiCode = code;
    }

    public static boolean isZodiacSign(String zodiacSign) {
        return Arrays.stream(ZodiacSigns.values())
                .anyMatch(sign -> sign.getTitle().equals(capitalized(zodiacSign)));
    }

    public static ZodiacSigns getZodiacSignByStr(String zodiacSign) {
        return Arrays.stream(ZodiacSigns.values())
                .filter(sign -> sign.getTitle().equals(capitalized(zodiacSign)))
                .findFirst()
                .orElse(null);
    }

    public static String getZodiacSignInEnglish(String zodiacSign) {
        return Arrays.stream(ZodiacSigns.values())
                .filter(sign -> sign.getTitle().equals(capitalized(zodiacSign)))
                .findFirst()
                .map(zodiacSigns -> zodiacSigns.toString().toLowerCase()).orElse(null);
    }

    public String getTitle() {
        return title;
    }

    public String getEmojiCode() {
        return emojiCode;
    }

    public String getSecondTitle() {
        return secondTitle;
    }
}
