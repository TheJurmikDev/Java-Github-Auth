package dev.thejurmik;

import dev.thejurmik.utils.getter.AuthInfo;
import dev.thejurmik.utils.hwid.HardwareID;
import dev.thejurmik.utils.requests.GithubRequest;
import dev.thejurmik.utils.system.SecureExit;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        char[] authAddress = null;
        char[] authKey = null;
        char[] hardwareId = null;
        HttpResponse<String> response;

        try {
            authAddress = AuthInfo.AuthAddress();
            authKey = AuthInfo.AuthKey();
            hardwareId = HardwareID.getHardwareID();

            response = GithubRequest.SendAndGetRequest(authAddress, authKey);

            if (response.statusCode() == 200) {
                if (response.body().toCharArray().length >= hardwareId.length && new String(response.body()).contains(new String(hardwareId))) {

                    // YOUR PROGRAM STARTS HERE
                    System.out.println("Successfully authenticated");


                } else {
                    SecureExit.exit();
                }
            } else {
                SecureExit.exit();
            }
        } catch (IOException | InterruptedException e) {
            SecureExit.exit();
        } finally {
            if (authAddress != null) Arrays.fill(authAddress, '\0');
            if (authKey != null) Arrays.fill(authKey, '\0');
            if (hardwareId != null) Arrays.fill(hardwareId, '\0');
        }
    }
}