package dev.thejurmik.parser;

import java.net.http.HttpResponse;
import java.util.Base64;

public class ParseGit {
    public static String parseGit(HttpResponse<String>  response) {
        String jsonResponse = response.body();
        String encodedString = jsonResponse.split("\"content\":\"")[1].split("\"")[0];

        String encodedString2 = encodedString.replace("\\n", "");
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString2);

        return new String(decodedBytes);
    }
}