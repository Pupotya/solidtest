package payments.api;

import core.exceptions.ApiRequestFailedException;
import org.testng.annotations.Test;
import payments.api.endpoints.InitPageEndpoint;
import payments.api.models.request.InitRequestPayload;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PaymentTest {

    public static final Locale locale = Locale.US;

    @Test(description = "Payment form /init fails due to amount -1")
    public void paymentFormInitFailsNegativeAmount() {
        int amount = -1;
        InitRequestPayload initPayload = InitRequestPayload.getBody(locale, amount);
        assertThatThrownBy(() -> new InitPageEndpoint().post(initPayload))
                .isInstanceOf(ApiRequestFailedException.class)
                .hasMessageContaining("2.01")
                .hasMessageContaining("amount=must be greater than or equal to 0");
    }

}
