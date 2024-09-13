package core.configuration;

import org.aeonbits.owner.Config;


public interface SiteConfig extends Config {

    @Key("payment.page.url")
    @DefaultValue("https://payment-page.solidgate.com/")
    String paymentPageUrl();

    @Key("pay.url")
    @DefaultValue("https://pay.solidgate.com/")
    String payUrl();

    @Key("public.key")
    String publicKey();

    @Key("secret.key")
    String secretKey();

}
