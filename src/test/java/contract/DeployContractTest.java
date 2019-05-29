package contract;


import io.restassured.module.mockmvc.RestAssuredMockMvc;
import buyer.BuyerController;
import org.junit.Before;

import java.io.IOException;

public class DeployContractTest {

    @Before
    public void setup() throws IOException {
        RestAssuredMockMvc.standaloneSetup(new BuyerController());
    }

}
