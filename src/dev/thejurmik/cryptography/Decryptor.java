package dev.thejurmik.cryptography;

import dev.thejurmik.utils.system.SecureExit;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Decryptor {
    private static final String KEY = "1A6F3E8D";
    private static final String INIT_VECTOR = "RandomInitVector";
    private static final String AES_MODE = "AES/CBC/PKCS5Padding";

    public static String decrypt(String encryptedInput) {
        if (encryptedInput == null || encryptedInput.isEmpty()) {
            return encryptedInput;
        }
        try {
            Cipher cipher = Cipher.getInstance(AES_MODE);
            SecretKeySpec keySpec = new SecretKeySpec(padKey().getBytes(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(INIT_VECTOR.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            byte[] decodedBytes = Base64.getDecoder().decode(encryptedInput);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);
        } catch (Exception idk) {
            SecureExit.exit();
        }
        return null;
    }

    private static String padKey() {
        StringBuilder paddedKey = new StringBuilder(Decryptor.KEY);
        while (paddedKey.length() < 16) {
            paddedKey.append(" ");
        }
        return paddedKey.substring(0, 16);
    }
}