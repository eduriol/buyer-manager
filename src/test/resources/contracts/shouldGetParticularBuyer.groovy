package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url '/buyer/1'
    }

    response {
        status 200
        body("""
        {
            "id": 1,
            "name": "John Doe",
            "purchasedItems": [
                {
                    "purchaseNumber": "1",
                    "itemTitle": "PC 486 66MHz 8MB"
                },
                {
                    "purchaseNumber": "2",
                    "itemTitle": "Nintendo NES 8 bits"
                }
            ],
            "preferredStore": {
                "name": "ACME"
            }
        }
        """)
    }
}
