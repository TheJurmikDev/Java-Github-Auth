package dev.thejurmik;

import dev.thejurmik.parser.ParseGit;
import dev.thejurmik.utils.getter.AuthInfo;
import dev.thejurmik.utils.hwid.HWID;
import dev.thejurmik.utils.requests.GithubRequest;
import dev.thejurmik.utils.requests.HttpResponseStatus;
import dev.thejurmik.utils.system.SecureExit;
import java.io.IOException;
import java.net.http.HttpResponse;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpResponse response = GithubRequest.SendAndGetRequest(AuthInfo.AuthAdress(), AuthInfo.AuthKey());
        int responseCode = HttpResponseStatus.ResponseGetter(response);
        if (responseCode == 200) {
            String hwid = HWID.getHWID();
            String decodedString = ParseGit.parseGit(response);
            if (decodedString.contains(hwid)) {

                // YOUR PROGRAM STARTS HERE
                System.out.println("Sucessfully authenticated");

            } else {
                SecureExit.exit();
            }
        } else {
            SecureExit.exit();
        }
    }
}
