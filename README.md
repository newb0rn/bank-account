# Bank Account Kata

This application of a bank account allows a client to make a deposit on his account and to check the history of its operations.

## Technical environment

- Backend : Spring Boot Java Application (jdk 11.0.2)
- Frontend : Angular (16)
- Database : embedded H2 database

## Run the project :

### Clone it
 - git clone git@github.com:newb0rn/bank-account.git

### Launch it

With any IDE :

 - in a terminal : mvn clean install
 - run the Springboot BankAccountApplication.java
 - in a terminal, go into the ihm folder :
   - npm install
   - ng serve
 - on a browser go to http://localhost:4200 

## For future improvements

- Add logs 
- Make the UI responsive 
- Add other functionality such as :
  - make a withdrawal
  - add clients management