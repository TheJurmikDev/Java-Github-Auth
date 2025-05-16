package dev.thejurmik.utils.requests;

import dev.thejurmik.utils.system.SecureExit;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

public class GithubRequest {
    public static HttpResponse<String> SendAndGetRequest(char[] fileUrl, char[] token) throws IOException, InterruptedException {
        if (fileUrl == null || token == null) {
            SecureExit.exit();
        }

        HttpClient client = HttpClient.newHttpClient();
        char[] urlStr = new String(fileUrl).trim().toCharArray();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(new String(fileUrl)))
                .header("Authorization", "Bearer " + new String(token))
                .header("Cache-Control", "no-cache")
                .header("Pragma", "no-cache")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Arrays.fill(token, '\0');
        Arrays.fill(urlStr, '\0');
        Arrays.fill(fileUrl, '\0');
        token = null;
        urlStr = null;
        fileUrl = null;
        request = null;
        client = null;
        return response;
    }
}