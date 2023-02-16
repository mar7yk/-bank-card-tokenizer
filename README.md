# Bank card tokenization with a multi-threaded server

The goal of the project is to implement the tokenization of a given bank card number. The system also implements access control logic. Users can register tokens and retrieve a card number from a token belonging to the user.

The project consists of two applications:

1. bank_card_tokenizer_server - multi-threaded server application
- Users data is stores in the users.xml
- Tokens data is stores in the tokens.xml
- The login query **(login \<username\> \<password\>)** returns:
    - a username if the user is registered
    - "nothing" if there is no such user or the password is incorrect
- The registration query **(register \<username\> \<password\>)** registers:
    - a user and returns a username if no user with such a name is registered 
    - “nothing” if a user with such a name is registered
- The register-token query **(register-token \<card-number\> \<username\>)** registers:
    - a token and returns it if there is such a user
    - otherwise returns “nothing”
- The retrieve card number query **(register-token \<token\> \<username\>)** returns:
    - a card number if such a token exists and the user has access to it
    - otherwise returns “nothing”

2. bank_card_tokenizer_server - JavaFX application