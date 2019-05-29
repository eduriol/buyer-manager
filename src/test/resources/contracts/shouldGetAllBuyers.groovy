package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url '/buyers'
    }

    response {
        status 200
        body("""
        [
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
            },
            {
                "id": 2,
                "name": "Jane Doe",
                "purchasedItems": [
                    {
                        "purchaseNumber": "1",
                        "itemTitle": "Hammer"
                    },
                    {
                        "purchaseNumber": "2",
                        "itemTitle": "Chainsaw"
                    }
                ],
                "preferredStore": {
                    "name": "eBay"
                }
            }
        ]
        """)
    }
}
