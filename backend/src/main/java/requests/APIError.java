package requests;

import java.util.List;

public class APIError {
    public final String message;
    public final List<String> errors;

    public APIError(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }
}
