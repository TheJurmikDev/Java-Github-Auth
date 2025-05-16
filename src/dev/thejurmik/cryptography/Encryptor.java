package dev.thejurmik.cryptography;

import dev.thejurmik.utils.system.SecureExit;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class Encryptor {
    private static char[] KEY = "1A6F3E8D".toCharArray();
    private static char[] INIT_VECTOR = "RandomInitVector".toCharArray();
    private static String AES_MODE = "AES/CBC/PKCS5Padding";

    public static char[] encrypt(char[] input) {
        if (input == null || input.length == 0) {
            return new char[0];
        }

        byte[] keyBytes = null;
        byte[] ivBytes = null;
        byte[] encryptedBytes = null;

        try {
            Cipher cipher = Cipher.getInstance(AES_MODE);
            keyBytes = padKey();
            ivBytes = new String(INIT_VECTOR).getBytes();
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

            encryptedBytes = cipher.doFinal(new String(input).getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes).toCharArray();
        } catch (Exception e) {
            SecureExit.exit();
            return new char[0];
        } finally {
            if (keyBytes != null) Arrays.fill(keyBytes, (byte) 0);
            if (ivBytes != null) Arrays.fill(ivBytes, (byte) 0);
            if (encryptedBytes != null) Arrays.fill(encryptedBytes, (byte) 0);
            Arrays.fill(KEY, (char) 0);
            Arrays.fill(INIT_VECTOR, (char) 0);
            Arrays.fill(AES_MODE.toCharArray(), (char) 0);
            Arrays.fill(input, (char) 0);
            input = null;
            keyBytes = null;
            ivBytes = null;
            encryptedBytes = null;
            KEY = null;
            INIT_VECTOR = null;
            AES_MODE = null;
        }
    }

    private static byte[] padKey() {
        StringBuilder paddedKey = new StringBuilder(new String(KEY));
        while (paddedKey.length() < 16) {
            paddedKey.append(" ");
        }
        return paddedKey.substring(0, 16).getBytes();
    }
}