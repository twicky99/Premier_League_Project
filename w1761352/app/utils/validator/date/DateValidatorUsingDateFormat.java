package utils.validator.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;

/**
* A class that handle date validation
*/
public class DateValidatorUsingDateFormat implements DateValidator {
    private String dateFormat;

    public DateValidatorUsingDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
    * check whether date valid or not
    * @param dateStr date input
    * @return boolean
    */
    @Override
    public boolean isValid(String dateStr) {
        try {
            if (dateStr.matches("([0-9]{1,2})/([0-9]{1,2})/([0-9]{4})")) {
                LocalDate.parse(dateStr,
                        DateTimeFormatter
                                .ofPattern(this.dateFormat));
                                
            } else {
                return false;
            }
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
