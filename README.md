# Software Developer Coding Challenge

##Solution
The app uses spring boot and a h2 database.
There are 4 endpoints:
    users: to manage user
    cars: to manage cars
    bids: to manage the bids
    auctions: to manage the auctions

To run the app: simply import the project in Intellij and click run on AuctionApplication.

Few rules to place a bid:
-A bid can be placed on a item only if an auction has been opened.
-A bid requires a user, so you need to create a user first
-A bid requires a item, so you need to create a item first

The postman collection:
https://www.getpostman.com/collections/895124904802cab103bb




This is a coding challenge for software developer applicants applying through http://work.traderev.com/

## Goal:

#### You have been tasked with building a simple online car auction system which will allow users to bid on cars for sale and with the following funcitionalies: 

  - [ ] Fork this repo. Keep it public until we have been able to review it.
  - [ ] A simple auction bidding system
  - [ ] Record a user's bid on a car
  - [ ] Get the current winning bid for a car
  - [ ] Get a car's bidding history 

 ### Bonus:

  - [ ] Unit Tests on the above functions
  - [ ] Develop a UI on web or mobile or CLI to showcase the above functionality

## Evaluation:

 - [ ] Solution compiles. Provide a README to run/build the project and explain anything that you leave aside
 - [ ] No crashes, bugs, compiler warnings
 - [ ] App operates as intended
 - [ ] Conforms to SOLID principles
 - [ ] Code is easily understood and communicative
 - [ ] Commit history is consistent, easy to follow and understand
