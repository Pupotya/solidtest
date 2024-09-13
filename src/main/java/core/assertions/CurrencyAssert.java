package core.assertions;

import org.assertj.core.api.AbstractAssert;

import java.util.Currency;
import java.util.Locale;

public class CurrencyAssert extends AbstractAssert<CurrencyAssert, String> {

    public CurrencyAssert(String actual) {
        super(actual, CurrencyAssert.class);
    }

    public static CurrencyAssert assertThat(String actual) {
        return new CurrencyAssert(actual);
    }

    public CurrencyAssert isEqualToCurrencyCode(String currencyCode, Locale locale) {
        isNotNull();

        Currency currency = Currency.getInstance(currencyCode);

        String expectedSymbol = currency.getSymbol(locale);

        if (!actual.equals(expectedSymbol)) {
            failWithMessage("Expected currency symbol to be <%s> but was <%s>", expectedSymbol, actual);
        }

        return this;
    }
}
