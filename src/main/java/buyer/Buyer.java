package buyer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.File;
import java.io.IOException;

@Data
class Buyer {

    private Long id;
    private String name;
    private Order[] purchasedItems;
    private Store preferredStore;

    public static Buyer fromFile(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(path), Buyer.class);
    }
}
