package core.api;

import core.exceptions.ApiRequestFailedException;
import payments.api.models.response.ErrorResponseBody;

public class ApiResponseChecker {

    public static final String ERROR_TEMPLATE = "Request Failed!\n Code: %1s, Message: %2s";
    private final ApiResponse apiResponse;

    public ApiResponseChecker(ApiResponse apiResponse) {
        this.apiResponse = apiResponse;
    }

    public ApiResponse isError() {
        ErrorResponseBody error = apiResponse.asClass(ErrorResponseBody.class);
        if (error.error == null) {
            return apiResponse;
        } else {
            throw new ApiRequestFailedException(String.format(ERROR_TEMPLATE,error.error.code, error.error.message));
        }
    }
}
