package core.configuration.l10n;

import core.configuration.l10n.LocalesConfig.*;
import org.aeonbits.owner.ConfigFactory;

import java.util.Locale;

public class LocalizationService {

    public static BaseLocalizationConfig getConfigForLocale(Locale locale) {
        return switch (locale.toString()) {
            case "en_GB" -> ConfigFactory.create(EnGBConfig.class);
            case "en_US" -> ConfigFactory.create(EnUsConfig.class);
            case "uk_UA" -> ConfigFactory.create(UkUAConfig.class);
            case "fr_FR" -> ConfigFactory.create(FrFRConfig.class);
            default -> throw new IllegalArgumentException("Can't find locale configuration: " + locale);
        };
    }

}
