package core.api;

import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class ApiResponse {

    private final Response response;
    private final ApiResponseChecker apiResponseChecker;

    public ApiResponse(Response response) {
        this.response = response;
        this.apiResponseChecker= new ApiResponseChecker(this);
    }

    public Response getResponse() {
        return response;
    }

    public ApiResponseChecker check() {
        return apiResponseChecker;
    }

    public <T> T asClass(Class<T> cls) {
        return response.as(cls, ObjectMapperType.GSON);
}

}
