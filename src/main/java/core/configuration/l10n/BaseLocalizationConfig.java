package core.configuration.l10n;

import org.aeonbits.owner.Config;

public interface BaseLocalizationConfig extends Config {

    @Key("api.currency")
    String apiCurrency();

    @Key("display.currency")
    String displayCurrency();

    @Key("country.code")
    String countryCode();

    @Key("capital.city")
    String capitalCity();

    @Key("language.code")
    String languageCode();

    @Key("success.pay.message")
    String successPayMessage();

}
