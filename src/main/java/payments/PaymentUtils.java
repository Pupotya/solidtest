package payments;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

public class PaymentUtils {

    private PaymentUtils() {

    }

    public static BigDecimal convertStringToBigDecimal(String price, Locale locale) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
        DecimalFormat format = new DecimalFormat();
        format.setDecimalFormatSymbols(symbols);
        format.setParseBigDecimal(true);
        try {
            return (BigDecimal) format.parse(price);
        } catch (ParseException e) {
            throw new RuntimeException("Can't parse string number: " + price + e);
        }
    }

}
