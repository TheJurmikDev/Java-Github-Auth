package dev.thejurmik.utils.getter;

import dev.thejurmik.InfoProvider;
import dev.thejurmik.cryptography.Decryptor;

public class AuthInfo {
    public static char[] AuthAddress() {
        return Decryptor.decrypt(InfoProvider.ADDRESS);
    }

    public static char[] AuthKey() {
        return Decryptor.decrypt(InfoProvider.KEY);
    }
}