import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Requester {
    public static double searchCurrency (String inputCurrency, String outputCurrency) {

        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/bd95dc05c27599bbff89c090/latest/" + inputCurrency);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            double rate = 0;
            if (response.statusCode() == 200) {
                JsonObject jsonObject = new Gson().fromJson(response.body(), JsonObject.class);
                JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");
                rate = conversionRates.get(outputCurrency).getAsDouble();
                return rate;
            } else {
                return -1;
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException("I could not find the second currency");
        }
    }
}
