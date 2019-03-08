package buyer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
class BuyerController {

    private final List<Buyer> repository;

    public BuyerController() throws IOException {
        this.repository = new ArrayList<>();
        //set up some example buyers
        this.repository.add(Buyer.fromFile("src/main/resources/buyer1.json"));
        this.repository.add(Buyer.fromFile("src/main/resources/buyer2.json"));
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @GetMapping("/buyers")
    List<Buyer> getAllBuyers() {
        return repository;
    }

    @PostMapping("/buyer")
    void addBuyer(@RequestBody Buyer newBuyer) {
        repository.add(newBuyer);
    }

    @GetMapping("/buyer/{id}")
    Buyer getBuyer(@PathVariable Long id) {
        for (Buyer buyer: repository) {
            if (buyer.getId().equals(id)) {
                return buyer;
            }
        }
        return null;
    }

    @PutMapping("/buyer/{id}")
    void replaceBuyer(@RequestBody Buyer newBuyer, @PathVariable Long id) {
        for (Buyer buyer : repository) {
            if (buyer.getId().equals(id)) {
                repository.remove(buyer);
                repository.add(newBuyer);
            }
        }
    }

    @DeleteMapping("/buyer/{id}")
    void deleteBuyer(@PathVariable Long id){
            for (Buyer buyer : repository) {
                if (buyer.getId().equals(id)) {
                    repository.remove(buyer);
                }
            }
        }

}
