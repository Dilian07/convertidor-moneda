package com.transformadormonedas.Api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.transformadormonedas.Modelos.Moneda;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeAPI {
    private final String apiKey;
    private final HttpClient cliente;

    public ExchangeAPI(String apiKey) {
        this.apiKey = apiKey;
        this.cliente = HttpClient.newHttpClient();
    }

    public double getConversionRate(Moneda fromMoneda, Moneda toMoneda) throws Exception {
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + fromMoneda;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonObject conversionRates = jsonResponse.getAsJsonObject("conversion_rates");
        return conversionRates.get(toMoneda.name()).getAsDouble();
    }

    public double convertir(Moneda fromMoneda, Moneda toMoneda, double monto) throws Exception {
        double rate = getConversionRate(fromMoneda, toMoneda);
        return monto * rate;
    }
}