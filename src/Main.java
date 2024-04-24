import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        int status = 1;
        URL myURL = new URL("https://es.iban.com/currency-codes");

        while (status == 1) {
            System.out.println("\n***************************************************************************************");
            System.out.println("These are the acronyms for some of the most used currencies in latinamerica:  \n" +
                    "ARS : Argentinian peso \n" +
                    "BOB : Bolivian bolivar \n" +
                    "BRL : Brazilian Real \n" +
                    "CLP : Chilean peso \n" +
                    "COP : Colombian peso \n" +
                    "USD : American Dolar \n" +
                    "EUR : Euro \n" +
                    "MXN : Mexican peso \n" +
                    "If the currency you are looking for is not listed, please remit to the following URL with " +
                            "a more complete list: " + myURL);
            System.out.println("***************************************************************************************");
            Scanner scan = new Scanner(System.in);
            System.out.println("Please write the 3 letter acronym for your coins: ");
            var inputCurrency = scan.nextLine().toUpperCase();
            System.out.println("Please write the 3 letter acronym of the coin you want to consult: ");
            var outputCurrency = scan.nextLine().toUpperCase();

            try {
                double rate = Requester.searchCurrency(inputCurrency, outputCurrency);
                if (rate > 0) {
                    System.out.println("The exchange rate is: " + rate);
                    System.out.println("Please introduce the amount of " + inputCurrency + " you want to exchange for " + outputCurrency + ":");
                    double amount = scan.nextDouble();
                    System.out.println(amount + " " + inputCurrency + " is equal to " + amount*rate + " " + outputCurrency);
                } else if (rate == 0){
                    System.out.println("I could not find the second currency, please try again.");
                } else {
                    System.out.println("I could not find the original currency");
                }

                System.out.println("Would you like to do a new conversion? 1 = yes, 2 = no");
                status = scan.nextInt();

            } catch (NumberFormatException e) {
                System.out.println("Currency not found " + e.getMessage());
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                System.out.println("Application finished.");
            }
        }

        System.out.println("***************************************************************************************");
        System.out.println("-----------------------THANKS FOR USING OUR CONVERSION SERVICES-------------------------");
        System.out.println("***************************************************************************************");
    }
}
