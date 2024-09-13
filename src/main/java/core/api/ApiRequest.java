package core.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static core.configuration.ConfigInitialization.getSiteConfig;

public class ApiRequest<T> {

    private static final Filter allureFilter = new AllureRestAssured();
    private final RequestSpecification requestSpecification;

    public ApiRequest() {
        requestSpecification = defaultRequestSpecification();
    }

    public RequestSpecification defaultRequestSpecification() {
        return new RequestSpecBuilder().setRelaxedHTTPSValidation()
                .build()
                .filter(allureFilter)
                .filter(new SessionFilter());
    }

    public ApiRequest<T> body(String body) {
        requestSpecification.body(body);
        return this;
    }


    public ApiRequest<T> header(String name, String value) {
        requestSpecification.header(name, value);
        return this;
    }

    public ApiRequest<T> signature(String value) {
        requestSpecification.header("signature", value);
        return this;
    }

    public ApiRequest<T> merchant() {
        requestSpecification.header("merchant", getSiteConfig().publicKey());
        return this;
    }


    public ApiRequest<T> contentType(ContentType contentType) {
        requestSpecification.contentType(contentType);
        return this;
    }


    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }
}
