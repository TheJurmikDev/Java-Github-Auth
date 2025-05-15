package dev.thejurmik.cryptography;

import dev.thejurmik.utils.system.SecureExit;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class Decryptor {
    private static final char[] KEY = "1A6F3E8D".toCharArray();
    private static final char[] INIT_VECTOR = "RandomInitVector".toCharArray();
    private static final String AES_MODE = "AES/CBC/PKCS5Padding";

    public static char[] decrypt(char[] encryptedInput) {
        if (encryptedInput == null || encryptedInput.length == 0) {
            return new char[0];
        }

        byte[] keyBytes = null;
        byte[] ivBytes = null;
        byte[] decodedBytes = null;
        byte[] decryptedBytes = null;

        try {
            Cipher cipher = Cipher.getInstance(AES_MODE);
            keyBytes = padKey();
            ivBytes = new String(INIT_VECTOR).getBytes();
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            decodedBytes = Base64.getDecoder().decode(new String(encryptedInput));
            decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes).toCharArray();
        } catch (Exception e) {
            SecureExit.exit();
            return new char[0];
        } finally {
            if (keyBytes != null) Arrays.fill(keyBytes, (byte) 0);
            if (ivBytes != null) Arrays.fill(ivBytes, (byte) 0);
            if (decodedBytes != null) Arrays.fill(decodedBytes, (byte) 0);
            if (decryptedBytes != null) Arrays.fill(decryptedBytes, (byte) 0);
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