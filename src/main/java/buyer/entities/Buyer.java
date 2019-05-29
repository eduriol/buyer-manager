package buyer.entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.File;
import java.io.IOException;

@Data
public class Buyer {

    private Long id;
    private String name;
    private Purchase[] purchasedItems;
    private Store preferredStore;

    public static Buyer fromFile(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(path), Buyer.class);
    }
}
