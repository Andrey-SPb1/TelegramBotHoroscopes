package org.bot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class TextOfAnswersUtil {

    private static final Logger log = LoggerFactory.getLogger(TextOfAnswersUtil.class);
    private static final File FILE = Path.of("src","main","resources", "defaultAnswers.txt").toFile();

    public static String getAnswer(String request) {

        String answer = "";

        try(BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while((line = br.readLine()) != null) {
                int index = line.indexOf("-");
                String command = line.substring(0, index);
                if (command.equals(request)){
                    answer = line.substring(index + 1);
                    break;
                }
            }

        } catch (IOException e) {
            log.error("Error getting answer from file: {}", e.toString());
            throw new RuntimeException(e);
        }

        return answer;
    }

}
