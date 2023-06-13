package common.Message;

import java.io.Serial;
import java.io.Serializable;

public class Response implements Serializable {
    @Serial
    private final static long serialVersionUID = 88256;
    String description;
    public Response(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
