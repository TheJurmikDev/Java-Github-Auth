package dev.thejurmik.utils.requests;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GithubRequest {
    public static HttpResponse SendAndGetRequest(String fileUrl, String token) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(fileUrl))
                .header("Authorization", "Bearer " + token)
                .header("Cache-Control", "no-cache")
                .header("Pragma", "no-cache")
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}