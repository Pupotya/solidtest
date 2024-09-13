package payments.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import payments.PaymentUtils;
import core.ui.ByDataTestId;

import java.math.BigDecimal;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;

public class SuccessPaymentPage {

    private final SelenideElement title = $(ByDataTestId.id("status-title"));
    private final SelenideElement priceMajor = $(ByDataTestId.id("status-order-price-header"));

    public SuccessPaymentPage titleHasSuccessMessage(String successMessage) {
        title.shouldHave(Condition.text(successMessage));
        return this;
    }

    public void shouldHaveMajorPrice(String price) {
        priceMajor.shouldHave(Condition.partialText(price));
    }
}
