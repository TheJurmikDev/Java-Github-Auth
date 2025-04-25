package dev.thejurmik.cryptography;

import dev.thejurmik.utils.system.SecureExit;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Encryptor {

    private static final String KEY = "1A6F3E8D";
    private static final String INIT_VECTOR = "RandomInitVector";
    private static final String AES_MODE = "AES/CBC/PKCS5Padding";

    public static String encrypt(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        try {
            Cipher cipher = Cipher.getInstance(AES_MODE);
            SecretKeySpec keySpec = new SecretKeySpec(padKey().getBytes(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(INIT_VECTOR.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

            byte[] encryptedBytes = cipher.doFinal(input.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            SecureExit.exit();
        }
        return null;
    }

    private static String padKey() {
        StringBuilder paddedKey = new StringBuilder(Encryptor.KEY);
        while (paddedKey.length() < 16) {
            paddedKey.append(" ");
        }
        return paddedKey.substring(0, 16);
    }
}