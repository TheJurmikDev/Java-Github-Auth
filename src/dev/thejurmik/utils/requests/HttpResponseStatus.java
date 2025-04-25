package dev.thejurmik.utils.requests;

import java.net.http.HttpResponse;

public class HttpResponseStatus {
    public static int ResponseGetter(HttpResponse<String> response) {
        return response.statusCode();
    }
}