import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        int status = 1;
        URL myURL = new URL("https://es.iban.com/currency-codes");

        while (status == 1) {
            PrintAndLog("\n***************************************************************************************\n" +
                    "These are the acronyms for some of the most used currencies in latinamerica:  \n" +
                    "ARS : Argentinian peso \n" +
                    "BOB : Bolivian bolivar \n" +
                    "BRL : Brazilian Real \n" +
                    "CLP : Chilean peso \n" +
                    "COP : Colombian peso \n" +
                    "USD : American Dolar \n" +
                    "EUR : Euro \n" +
                    "MXN : Mexican peso \n" +
                    "If the currency you are looking for is not listed, please remit to the following URL with " +
                            "a more complete list: " + myURL + "\n" +
                    "***************************************************************************************");

            Scanner scan = new Scanner(System.in);
            PrintAndLog("Please write the 3 letter acronym for your coins: ");
            var inputCurrency = scan.nextLine().toUpperCase();
            WriteToLog(inputCurrency);

            PrintAndLog("Please write the 3 letter acronym of the coin you want to consult: ");
            var outputCurrency = scan.nextLine().toUpperCase();
            WriteToLog(outputCurrency);

            try {
                double rate = Requester.searchCurrency(inputCurrency, outputCurrency);
                if (rate > 0) {
                    PrintAndLog("The exchange rate is: " + rate);
                    PrintAndLog("Please introduce the amount of " + inputCurrency + " you want to exchange for " + outputCurrency + ":");
                    double amount = scan.nextDouble();
                    WriteToLog(amount + " " + inputCurrency);
                    PrintAndLog(amount + " " + inputCurrency + " is equal to " + amount*rate + " " + outputCurrency);
                } else if (rate == 0){
                    PrintAndLog("I could not find the second currency, please try again.");
                } else {
                    PrintAndLog("I could not find the original currency");
                }

                PrintAndLog("Would you like to do a new conversion? 1 = yes, 2 = no");
                status = scan.nextInt();
                WriteToLog(Double.toString(status));

            } catch (NumberFormatException e) {
                PrintAndLog("Currency not found " + e.getMessage());
            } catch (RuntimeException e) {
                PrintAndLog(e.getMessage());
                PrintAndLog("Application finished.");
            }
        }

        PrintAndLog("***************************************************************************************");
        PrintAndLog("-----------------------THANKS FOR USING OUR CONVERSION SERVICES-------------------------");
        PrintAndLog("***************************************************************************************");
    }

    private static void PrintAndLog(String message) {
        System.out.println(message);
        WriteToLog(message);
    }

    private static void WriteToLog(String message) {
        LocalDate today = LocalDate.now();
        String formatedDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String fileName = "logger_" + formatedDate + ".txt";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            LocalDateTime currentDateTime = LocalDateTime.now();
            String format = currentDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

            writer.append(format + " : ");
            writer.append(message + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception writing logs " + e.getMessage());
        }
    }
}



