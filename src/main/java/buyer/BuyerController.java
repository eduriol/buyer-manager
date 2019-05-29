package buyer;

import buyer.entities.Buyer;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BuyerController {

    public static final List<Buyer> buyerRepository = new ArrayList<>();

    public BuyerController() throws IOException {
        //set up some example buyers
        for (int i = 1; i <= 2; i++) {
            buyerRepository.add(Buyer.fromFile("src/main/resources/buyer" + i + ".json"));
        }
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping("/buyers")
    public List<Buyer> getAllBuyers() {
        return buyerRepository;
    }

    @PostMapping("/buyer")
    public void addBuyer(@RequestBody Buyer newBuyer) {
        for (Buyer buyer : buyerRepository) {
            if (buyer.getId().equals(newBuyer.getId())) {
                throw new IllegalArgumentException("Buyer id already in use.");
            }
        }
        buyerRepository.add(newBuyer);
    }

    @GetMapping("/buyer/{id}")
    public Buyer getBuyer(@PathVariable Long id) {
        for (Buyer buyer: buyerRepository) {
            if (buyer.getId().equals(id)) {
                return buyer;
            }
        }
        return null;
    }

}
