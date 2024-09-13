package payments.api.models.response;

import java.util.HashMap;

public class ErrorResponseBody {

    public Error error;

    public static class Error {
        public String code;
        public HashMap<String,Object> message;

    }
    
}
