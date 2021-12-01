package com.khadimullin.net.service;

import com.khadimullin.net.helper.Json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class WeatherService {
    private final static String API_KEY = "47f8e32ff6b6e632ade1c688d83b2b94";
    private final static String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    private final static Json jsonHelper = new Json();

    public Map<String, String> get(String city) throws IOException {
        URL myUrl = new URL(BASE_URL + city + "&appid=" + API_KEY);
        HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();
        StringBuilder result = new StringBuilder();

        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {
            String input;

            while ((input = reader.readLine()) != null) {
                result.append(input);
            }
        } catch (Exception e) {
            return null;
        }

        connection.disconnect();
        return jsonHelper.parseJson(result.toString());
    }
}
