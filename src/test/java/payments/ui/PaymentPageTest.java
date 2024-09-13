package payments.ui;

import com.codeborne.selenide.Selenide;
import core.allure.ScreenshotListener;
import core.assertions.CurrencyAssert;
import core.browser.BrowserHelper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import payments.SuccessCard;
import payments.api.endpoints.InitPageEndpoint;
import payments.api.endpoints.StatusEndpoint;
import payments.api.models.request.InitRequestPayload;
import payments.api.models.request.StatusRequestPayload;
import payments.api.models.response.InitResponseBody;
import payments.api.models.response.StatusResponseBody;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import static core.configuration.l10n.LocalizationService.getConfigForLocale;
import static org.assertj.core.api.Assertions.assertThat;
import static payments.PaymentUtils.convertStringToBigDecimal;

@Listeners({ ScreenshotListener.class})
public class PaymentPageTest {

    @DataProvider(name = "Locales", parallel = true)
    public Object[][] locales() {
        return new Object[][]{
                {Locale.FRANCE},
                {Locale.UK},
                {Locale.US},
                {new Locale("uk","UA")},
        };
    }

    @Test(description = "UI success payment. Check success message, price and amount equals to /status response",
            dataProvider = "Locales")
    public void successOrderStatus(Locale locale) {
        int amount = 1051;
        InitRequestPayload initPayload = InitRequestPayload.getBody(locale, amount);
        InitResponseBody response = new InitPageEndpoint().post(initPayload);

        BrowserHelper.open(response.getUrl(), locale);

        PaymentPage paymentPage = new PaymentPage();
        BigDecimal bigDecimalAmount = convertStringToBigDecimal(String.valueOf(amount), locale)
                .divide(BigDecimal.valueOf(100));

        NumberFormat numberFormat = NumberFormat.getInstance(locale);
        String localeAmount = numberFormat.format(bigDecimalAmount);

        paymentPage.shouldHaveMajorPrice(localeAmount);

        String currency = paymentPage.getCurrency();
        String displayPrice = paymentPage.getPrice();

        BigDecimal bigDecimalPrice = convertStringToBigDecimal(displayPrice, locale);

        paymentPage.typeCardNumber(SuccessCard.NUMBER)
                .typeCardExpiryDate(SuccessCard.EXPIRY_DATE)
                .typeCvv(SuccessCard.CVV)
                .typeCardHolder(SuccessCard.CARD_HOLDER)
                .clickSubmit();

        SuccessPaymentPage successPaymentPage = new SuccessPaymentPage();
        successPaymentPage.titleHasSuccessMessage(getConfigForLocale(locale)
                        .successPayMessage())
                .shouldHaveMajorPrice(localeAmount);

        StatusRequestPayload statusPayload = new StatusRequestPayload(initPayload.order.orderId);
        StatusResponseBody statusResponse = new StatusEndpoint().post(statusPayload);
        String transactionStatus = statusResponse.transactions.entrySet().iterator().next().getValue().status;

        assertThat(transactionStatus).isEqualTo("success");
        assertThat(bigDecimalPrice.multiply(BigDecimal.valueOf(100)).intValue()).isEqualTo(statusResponse.order.amount);
        CurrencyAssert.assertThat(currency).isEqualToCurrencyCode(statusResponse.order.currency, locale);
    }

    @Test(description = "No GPay Button when amount 0")
    public void noGPayBtnAbsentAmountZero() {
        int amount = 0;
        Locale locale = Locale.US;
        InitRequestPayload initPayload = InitRequestPayload.getBody(locale, amount);
        InitResponseBody response = new InitPageEndpoint().post(initPayload);
        BrowserHelper.open(response.getUrl(), locale);
        PaymentPage paymentPage = new PaymentPage();
        paymentPage.gPayBtnAbsent();
    }

    @Test(description = "GPay Button present on amount 1")
    public void gPayBtnPresentPositiveAmount() {
        int amount = 1;
        Locale locale = Locale.US;
        InitRequestPayload initPayload = InitRequestPayload.getBody(locale, amount);
        InitResponseBody response = new InitPageEndpoint().post(initPayload);
        BrowserHelper.open(response.getUrl(), locale);
        PaymentPage paymentPage = new PaymentPage();
        paymentPage.gPayBtnPresent();
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Selenide.closeWebDriver();
    }

}
