## Solution
The app uses spring boot and a h2 database.
There are 4 endpoints:
 - users: to manage user
 - cars: to manage cars
 - bids: to manage the bids
 - auctions: to manage the auctions

To run the app: simply import the project in Intellij and click run on AuctionApplication.

Few rules to place a bid:
- A bid can be placed on a item only if an auction has been opened.
- An Auction requires a user, so you need to create a user first
- An Auction requires a item, so you need to create a item first

The postman collection:
https://www.getpostman.com/collections/895124904802cab103bb

The swagger ui:
http://localhost:8080/swagger-ui.html


