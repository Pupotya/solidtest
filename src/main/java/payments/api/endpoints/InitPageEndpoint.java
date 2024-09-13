package payments.api.endpoints;

import com.google.gson.Gson;
import core.api.AbstractEndpoint;
import io.qameta.allure.Step;
import payments.SignatureGenerator;
import payments.api.models.request.InitRequestPayload;
import payments.api.models.response.InitResponseBody;
import io.restassured.http.ContentType;

import static core.configuration.ConfigInitialization.getSiteConfig;

public class InitPageEndpoint extends AbstractEndpoint {

    public static final String ENDPOINT = "api/v1/init";

    public InitPageEndpoint() {
        api().url(getSiteConfig().paymentPageUrl() + ENDPOINT)
                .apiRequest()
                .contentType(ContentType.JSON)
                .merchant();
    }


    public InitResponseBody post(InitRequestPayload payload) {
        String jsonBody = new Gson().toJson(payload);
        String signature = SignatureGenerator.generate(getSiteConfig().publicKey(), jsonBody , getSiteConfig().secretKey());

        api().apiRequest()
                .signature(signature)
                .body(jsonBody);

        return api().post()
                .apiResponse()
                .check()
                .isError()
                .asClass(InitResponseBody.class);
    }

}
