package payments.api.endpoints;

import com.google.gson.Gson;
import core.api.AbstractEndpoint;
import payments.SignatureGenerator;
import payments.api.models.request.StatusRequestPayload;
import payments.api.models.response.StatusResponseBody;
import io.restassured.http.ContentType;

import static core.configuration.ConfigInitialization.getSiteConfig;

public class StatusEndpoint extends AbstractEndpoint {

    public static final String ENDPOINT = "api/v1/status";

    public StatusEndpoint() {
        api().url(getSiteConfig().payUrl() + ENDPOINT);
        api().apiRequest()
                .contentType(ContentType.JSON);
//                .merchant(getSiteConfig().publicKey());
    }

    public StatusResponseBody post(StatusRequestPayload payload) {
        String jsonBody = new Gson().toJson(payload);
        String signature = SignatureGenerator.generate(getSiteConfig().publicKey(), jsonBody , getSiteConfig().secretKey());

        api().apiRequest()
                .header("merchant", getSiteConfig().publicKey())
                        .header("signature", signature)
                .body(jsonBody);


        return api().post()
                .apiResponse()
                .asClass(StatusResponseBody.class);
    }
}
