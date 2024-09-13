package core.configuration.l10n;

import org.aeonbits.owner.Config;


public interface LocalesConfig extends Config {

    @Sources("classpath:l10n_en_US.properties")
    interface EnUsConfig extends BaseLocalizationConfig {}

    @Sources("classpath:l10n_en_GB.properties")
    interface EnGBConfig extends BaseLocalizationConfig {}

    @Sources("classpath:l10n_fr_FR.properties")
    interface FrFRConfig extends BaseLocalizationConfig {}

    @Sources("classpath:l10n_uk_UA.properties")
    interface UkUAConfig extends BaseLocalizationConfig {}

}
