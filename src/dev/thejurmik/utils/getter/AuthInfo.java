package dev.thejurmik.utils.getter;

import dev.thejurmik.InfoProvider;
import dev.thejurmik.cryptography.Decryptor;
import dev.thejurmik.utils.system.SecureExit;

public class AuthInfo {
    public static char[] AuthAddress() {
        try {
            return Decryptor.decrypt(InfoProvider.ADDRESS);
        } catch (Exception e) {
            SecureExit.exit();
        } finally {
            InfoProvider.ADDRESS = null;
        }
        return null;
    }

    public static char[] AuthKey() {
        try {
            return Decryptor.decrypt(InfoProvider.KEY);
        } catch (Exception e) {
            SecureExit.exit();
        } finally {
            InfoProvider.KEY = null;
        }
        return null;
    }
}