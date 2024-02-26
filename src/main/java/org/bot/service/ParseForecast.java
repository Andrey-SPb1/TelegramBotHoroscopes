package org.bot.service;

import org.bot.service.enums.ZodiacSigns;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.bot.service.enums.ForecastPeriods.*;
import static org.bot.service.enums.ZodiacSigns.getZodiacSignInEnglish;
import static org.bot.util.StringUtil.*;
import static org.bot.util.TextOfAnswersUtil.*;

public class ParseForecast {

    private static final Logger log = LoggerFactory.getLogger(ParseForecast.class);

    public static String getForecast(String zodiacSignStr, String forecastPeriod) {
        StringBuilder forecast = new StringBuilder();
        String zodiacSignInEnglish = getZodiacSignInEnglish(zodiacSignStr);
        String forecastPeriodsInEnglish = getForecastPeriodsInEnglish(forecastPeriod);
        ZodiacSigns zodiacSign = ZodiacSigns.getZodiacSignByStr(zodiacSignStr);
        String url = gerCorrectUrl(zodiacSignInEnglish, forecastPeriodsInEnglish);
        String answer = getAnswer(String.format("forecastTitleFor%s", capitalized(forecastPeriodsInEnglish)));

            Elements elements = parser(url);
            if(elements != null) {
                forecast.append(String.format("<b>%s</b> для <b>%s</b> %s:\n",
                                answer, zodiacSign.getSecondTitle(), zodiacSign.getEmojiCode()))
                        .append("&#10024;".repeat(3));
                for (Element element : elements) {
                    forecast.append("\n   <i>").append(element.text()).append("</i>\n");
                }
            } else {
                forecast = new StringBuilder(getAnswer("error"));
            }
        return forecast.toString();
    }

    private static Elements parser(String url)  {
        try {
            Document doc = Jsoup.connect(url).get();
            return doc.select("p");
        } catch (IOException e) {
            log.error("Error connecting to the site: {}", e.toString());
            e.printStackTrace();
            return null;
        }
    }

    private static String gerCorrectUrl(String zodiacSignInEnglish, String forecastPeriodsInEnglish) {
        return String.format("https://horo.mail.ru/prediction/%s/%s/", zodiacSignInEnglish, forecastPeriodsInEnglish);
    }
}
