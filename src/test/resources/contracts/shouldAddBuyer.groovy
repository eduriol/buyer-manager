package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'POST'
        url '/buyer'
        body("""
        {
          "id": "4",
          "name": "William of Baskerville",
          "purchasedItems": [
            {
              "purchaseNumber": "1",
              "itemTitle": "The Name of the Rose"
            },
            {
              "purchaseNumber": "2",
              "itemTitle": "Very Poisonous Ancient Book"
            }
          ],
          "preferredStore": {
            "name": "Library of an Ancient Monastery"
          }
        }
        """)
        headers {
            header('Content-Type', 'application/json')
        }
    }

    response {
        status 200
    }
}
