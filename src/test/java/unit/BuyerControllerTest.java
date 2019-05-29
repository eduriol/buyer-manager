package unit;

import buyer.entities.Buyer;
import buyer.BuyerController;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BuyerControllerTest {

    private BuyerController buyerController;

    @Before
    public void setUp() throws IOException {
        buyerController = new BuyerController();
    }

    @Test
    public void testGetAllBuyersFirstBuyer() throws IOException {
        assertThat(buyerController.getAllBuyers().get(0), is(equalTo(Buyer.fromFile("src/main/resources/buyer1.json"))));
    }

    @Test
    public void testGetBuyerById() throws IOException {
        assertThat(buyerController.getBuyer(1L), is(equalTo(Buyer.fromFile("src/main/resources/buyer1.json"))));
    }

    @Test
    public void testGetBuyerByInexistentId() {
        assertThat(buyerController.getBuyer(0L), is(nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemExistingId() throws IOException {
        buyerController.addBuyer(Buyer.fromFile("src/main/resources/buyer1.json"));
    }

    @Test
    public void testAddBuyerDistinctId() throws IOException {
        buyerController.addBuyer(Buyer.fromFile("src/test/resources/buyer3.json"));
        assertThat(buyerController.getBuyer(3L), is(equalTo(Buyer.fromFile("src/test/resources/buyer3.json"))));
    }

}
