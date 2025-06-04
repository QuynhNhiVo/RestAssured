package jsonplaceholder.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DemoPojo {
    private String title;
    private String body;
    private int userId;
}
