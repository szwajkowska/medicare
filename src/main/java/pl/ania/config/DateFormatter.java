package pl.ania.config;

import org.springframework.format.Formatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter implements Formatter<Date> {
    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(text);
    }

    @Override
    public String print(Date object, Locale locale) {
        return new SimpleDateFormat("YYYY-MM-dd HH:mm").format(object);
    }
}
