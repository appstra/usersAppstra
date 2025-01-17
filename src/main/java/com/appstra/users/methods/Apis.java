package com.appstra.users.methods;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 *                  String token = this.jwtUtil.create(userObjet);
 *                 String responseGet = GetApi("GET", Apis.EMPLOYEE, token, null);
 *                 System.out.println("GET Response: " + responseGet);
 *
 *                 // Ejemplo de uso con un PUT
 *                 String jsonBody = "{\"name\":\"John Doe\",\"position\":\"Developer\"}";
 *                 String responsePut = GetApi("PUT", Apis.EMPLOYEE, token, jsonBody);
 *                 System.out.println("PUT Response: " + responsePut);
 */
@Configuration
public class Apis {

    @Value("${url-api}")
    private String employeeUrl;

    public static String EMPLOYEE;

    @PostConstruct
    private void init() {
        EMPLOYEE = employeeUrl;
    }

    public static String GetApi(String method, String url, String token, String jsonBody) throws IOException {

        URL obj = new URL(url);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod(method.toUpperCase());

        con.setRequestProperty("Authorization", "Bearer " + token);
        con.setRequestProperty("Content-Type", "application/json");

        // Si el método es PUT o POST, agregar el cuerpo
        if (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT")) {
            con.setDoOutput(true);
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
        }

        int responseCode = con.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            return response.toString();
        }
    }


}
