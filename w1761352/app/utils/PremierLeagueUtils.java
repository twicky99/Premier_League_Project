package utils;

import utils.validator.date.DateValidator;
import utils.validator.date.DateValidatorUsingDateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Utility class for Premier League application.
 */

public final class PremierLeagueUtils {

    /**
    * check valid date
    * @param date
    * @return boolean
    */
    public static boolean checkValidDate(String date) {
        DateValidator validator = new DateValidatorUsingDateFormat("d/M/yyyy");
        return validator.isValid(date);
    }

    /**
    * change format of date, if day and month have only one character, it will be filled with zero
    * @param date String of date
    * @return String
    */
    public static String formatDate(String date) {
        String[] split = date.split("/");
        split[0] = (split[0].length() == 1) ? "0" + split[0] : split[0];
        split[1] = (split[1].length() == 1) ? "0" + split[1] : split[1];
        return split[0] + "/" + split[1] + "/" + split[2];
    }

    /**
    * generate random number with bound
    * @param bound number of bound
    * @return int
    */
    public static int generateRandomNumber(int bound) {
        Random rand = new Random();
        return rand.nextInt(bound);
    }

    /**
    * generate random year
    * @return String
    */
    public static String generateNow() {
        return createRandomDate(2019, 2020);
    }

    /**
    * genererate random number between 2 numbers, start and end
    * @param start
    * @param end
    * @return int
    */
    public static int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    /**
    * generate random date between 2 years
    * @param startYear 
    * @param endYear 
    * @return String
    */
    public static String createRandomDate(int startYear, int endYear) {
        int day = createRandomIntBetween(1, 28);
        int month = createRandomIntBetween(1, 12);
        int year = createRandomIntBetween(startYear, endYear);
        return LocalDate.of(year, month, day)
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

}
