# Challenge coin exchanger
This is a java challenge proposed to the back-end formation students in the Oracle Next Education (ONE) program.
This consist in the creation of a java program able to consume an API from [ExchangeRateAPi](https://www.exchangerate-api.com/) in order to get a json object that we could use to convert different type of currencies provided by the user.

## :hammer: Functionalities:
`Functionality 1`: The console/terminal should provide a menu with relevant information to the user. \
`Functionality 2`: The program should be able to process at least 6 different currencies. \
`Functionality 3`: The program would be running in a loop which the user could be able to get out to finish the program. \
`Functionality 4`: The program would have to be able to handle different types of error and exceptions.
`Functionality 5`: The program will create a logfile after execution with a timestamp history.

## :computer: Technologies
Java \
IntellJ V21 \
API (Application Programming Interface) \
Postman API platform

## :bulb: How to use?
Once the program start execution a brief menu with some of the most popular currencies in latin america will be displayed with its corresponding 3 letter acronym, the program can work with around 170 currencies, but it would display just a few because of space optimization.
Afterwards, the program will ask for the 3 letter acronym of the currency to convert and subsequently, the 3 letter acronym of the currency to consult. 
Then, if there are no errors while looking for the currencies, the program would display the exchange rate between both currencies and will ask for the amount to convert.
Finally, the program will display the currency to convert in its original amount and the new amount converted to the new currency. The program will ask if another conversion would be done, if positive, the program will restart, otherwise, the programm will thank the user and close operation.


