package payments.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import core.ui.ByDataTestId;
import org.openqa.selenium.By;

import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.$;

public class PaymentPage {

    private final SelenideElement priceMajor = $(ByDataTestId.id("price_major"));
    private final SelenideElement cardInput = $(ByDataTestId.id("cardNumber"));
    private final SelenideElement cardExpiryDate = $(By.name("cardExpiryDate"));
    private final SelenideElement cardCvv = $(By.id("cvv2"));
    private final SelenideElement cardHolder = $(By.name("cardHolder"));
    private final SelenideElement submit = $(ByDataTestId.id("submit"));
    private final SelenideElement gPayBtn = $("#gpay-button-online-api-id");

    @Step
    public void shouldHaveMajorPrice(String price) {
        priceMajor.shouldHave(Condition.partialText(price));
    }

    @Step
    public String getCurrency() {
        return priceMajor.getText().replaceAll("[\\d.,\\s]+","");
    }

    @Step
    public String getPrice() {
        return priceMajor.getText().replaceAll("[^\\d.,\\s]","").trim();
    }

    @Step
    public PaymentPage typeCardNumber(String cardNumber) {
        cardInput.click();
        cardInput.setValue(cardNumber);
        return this;
    }

    @Step
    public PaymentPage typeCardExpiryDate(String expiryDate) {
        cardExpiryDate.click();
        cardExpiryDate.setValue(expiryDate);
        return this;
    }

    @Step
    public PaymentPage typeCvv(String cvv) {
        cardCvv.click();
        cardCvv.setValue(cvv);
        return this;
    }

    @Step
    public PaymentPage typeCardHolder(String cardHolder) {
        this.cardHolder.click();
        this.cardHolder.setValue(cardHolder);
        return this;
    }

    @Step
    public void clickSubmit() {
        submit.click();
    }

    @Step
    public void gPayBtnPresent() {
        gPayBtn.shouldBe(Condition.visible);
    }

    @Step
    public void gPayBtnAbsent() {
        gPayBtn.shouldNotBe(Condition.visible);
    }

}
