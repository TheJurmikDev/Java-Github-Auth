package dev.thejurmik.utils.hwid;

import dev.thejurmik.cryptography.Encryptor;
import dev.thejurmik.utils.system.SecureExit;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class HardwareID {
    public static char[] getHardwareID() {
        byte[] toEncrypt = null;
        try {
            String raw = System.getenv("COMPUTERNAME") +
                    System.getProperty("user.name") +
                    System.getenv("PROCESSOR_IDENTIFIER") +
                    System.getenv("PROCESSOR_LEVEL");
            toEncrypt = raw.getBytes();

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(toEncrypt);
            byte[] byteData = md.digest();

            StringBuilder hexString = new StringBuilder();
            for (byte b : byteData) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return Encryptor.encrypt(hexString.toString().toCharArray());
        } catch (NoSuchAlgorithmException e) {
            SecureExit.exit();
            return new char[0];
        } finally {
            if (toEncrypt != null) Arrays.fill(toEncrypt, (byte) 0);
        }
    }
}