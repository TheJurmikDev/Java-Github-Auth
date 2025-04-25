package dev.thejurmik.utils.getter;

import dev.thejurmik.InfoProvider;
import dev.thejurmik.cryptography.Decryptor;

public class AuthInfo {

    public static String AuthAdress() {
        return Decryptor.decrypt(InfoProvider.ADRESS);
    }

    public static String AuthKey() {
        return Decryptor.decrypt(InfoProvider.KEY);
    }

    public static String adress = AuthAdress();
    public static String key = AuthKey();
}
