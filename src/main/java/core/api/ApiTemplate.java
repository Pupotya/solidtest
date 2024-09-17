package core.api;

import io.qameta.allure.Step;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

public class ApiTemplate {

    public static final Logger LOGGER = LoggerFactory.getLogger(ApiTemplate.class);

    private final Config config;

    public ApiRequest<?> apiRequest() {
        return apiRequest;
    }

    public ApiResponse apiResponse() {
        return apiResponse;
    }

    ApiRequest<?> apiRequest;
    ApiResponse apiResponse;

    public ApiTemplate() {
        this(new ApiRequest<>());
    }

    public ApiTemplate(ApiRequest<Object> apiRequest) {
        this.apiRequest = apiRequest;
        config = new Config();
    }

    public ApiTemplate url(String url) {
        this.config.url = url;
        return this;
    }

    public ApiTemplate post() {
        return post(config.url);
    }

    private ApiTemplate get(String url) {
        return request(Method.GET, url);
    }

    private ApiTemplate post(String url) {
        return request(Method.POST, url);
    }

    @Step("{method} {url}")
    private ApiTemplate request(Method method, String url) {
        this.config.method = method;
        if (config.url == null) {
            config.url = url;
        }

        LOGGER.info("{} request: {}", config.method, config.url);

        Response response = given().spec(apiRequest.getRequestSpecification())
                .request(method, url);
        apiResponse = new ApiResponse(response);
        LOGGER.info("{} response: {} {}", config.method, config.url, response.statusLine());
        return this;
    }

    public static class Config {
        public String url;
        public Method method;
    }

}
