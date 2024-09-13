package payments.api.models.request;

import com.google.gson.annotations.SerializedName;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static core.configuration.ConfigInitialization.getSiteConfig;
import static core.configuration.l10n.LocalizationService.getConfigForLocale;


public class InitRequestPayload {

    public InitRequestPayload(Order order, PageCustomization pageCustomization) {
        this.order = order;
        this.pageCustomization = pageCustomization;
    }

    @SerializedName("order")
    public Order order;

    @SerializedName("page_customization")
    public PageCustomization pageCustomization;

    public static class Order {

        @SerializedName("order_id")
        public String orderId;

        @SerializedName("amount")
        public Integer amount;

        @SerializedName("currency")
        public String currency;
        @SerializedName("order_description")

        public String orderDescription;
        @SerializedName("order_items")

        public String orderItems;
        @SerializedName("order_date")

        public String orderDate;
        @SerializedName("order_number")

        public Integer orderNumber;
        @SerializedName("type")

        public String type;
        @SerializedName("settle_interval")

        public Integer settleInterval;
        @SerializedName("retry_attempt")

        public Integer retryAttempt;
        @SerializedName("force3ds")

        public Boolean force3ds;

        @SerializedName("google_pay_allowed_auth_methods")
        public List<String> googlePayAllowedAuthMethods;
        @SerializedName("customer_date_of_birth")

        public String customerDateOfBirth;
        @SerializedName("customer_email")

        public String customerEmail;
        @SerializedName("customer_first_name")

        public String customerFirstName;
        @SerializedName("customer_last_name")

        public String customerLastName;
        @SerializedName("customer_phone")

        public String customerPhone;
        @SerializedName("traffic_source")

        public String trafficSource;
        @SerializedName("transaction_source")

        public String transactionSource;
        @SerializedName("purchase_country")

        public String purchaseCountry;
        @SerializedName("geo_country")

        public String geoCountry;
        @SerializedName("geo_city")

        public String geoCity;
        @SerializedName("language")

        public String language;
        @SerializedName("website")

        public String website;
        @SerializedName("order_metadata")

        public OrderMetadata orderMetadata;
        @SerializedName("success_url")

        public String successUrl;
        @SerializedName("fail_url")

        public String failUrl;


        public static class OrderMetadata {

            @SerializedName("coupon_code")

            public String couponCode;
            @SerializedName("partner_id")

            public String partnerId;

        }
    }

    public static class PageCustomization {

        @SerializedName("public_name")
        public String publicName;

        @SerializedName("order_title")
        public String orderTitle;

        @SerializedName("order_description")
        public String orderDescription;

        @SerializedName("payment_methods")
        public List<String> paymentMethods;
        @SerializedName("button_font_color")

        public String buttonFontColor;
        @SerializedName("button_color")

        public String buttonColor;
        @SerializedName("font_name")

        public String fontName;
        @SerializedName("is_cardholder_visible")

        public Boolean isCardholderVisible;
        @SerializedName("terms_url")

        public String termsUrl;
        @SerializedName("back_url")

        public String backUrl;

    }

    public static InitRequestPayload getBody(Locale locale, int amount) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Order order = new Order();
        PageCustomization pageCustomization = new PageCustomization();
        order.orderId = String.valueOf(UUID.randomUUID());
        order.amount = amount;
        order.currency = Currency.getInstance(locale).getCurrencyCode();
        order.orderDescription = "Premium package";
        order.orderItems = "item 1 x 10, item 2 x 30";
        order.orderDate = ZonedDateTime.now().format(format);
        order.orderNumber = 9;
        order.type = "auth";
        order.settleInterval = 0;
        order.retryAttempt = 3;
        order.force3ds = false;
        order.googlePayAllowedAuthMethods = List.of("PAN_ONLY");
        order.customerDateOfBirth = "1988-11-21";
        order.customerEmail = "example@example.com";
        order.customerFirstName = "Nikola";
        order.customerLastName = "Tesla";
        order.customerPhone = "+10111111111";
        order.trafficSource = "facebook";
        order.transactionSource = "main_menu";
        order.purchaseCountry = locale.getISO3Country();
        order.geoCountry = locale.getISO3Country();
        order.geoCity = getConfigForLocale(locale).capitalCity();
        order.language = locale.getLanguage();
        order.website = "https://solidgate.com";
        order.orderMetadata = new Order.OrderMetadata();
        order.orderMetadata.couponCode = "NY2018";
        order.orderMetadata.partnerId = "123989";
        order.successUrl = getSiteConfig().paymentPageUrl() + "/success";
        order.successUrl = getSiteConfig().paymentPageUrl() + "/fail";

        pageCustomization.publicName = "Public Name";
        pageCustomization.orderTitle = "Order Title";
        pageCustomization.orderDescription = "Premium package";
        pageCustomization.paymentMethods = List.of("paypal");
        pageCustomization.buttonFontColor = "#FFFFFF";
        pageCustomization.buttonColor = "#00816A";
        pageCustomization.fontName = "Open Sans";
        pageCustomization.isCardholderVisible = true;
        pageCustomization.termsUrl = "https://solidgate.com/terms";
        pageCustomization.backUrl = "https://solidgate.com";

        return new InitRequestPayload(order, pageCustomization);

    }


}