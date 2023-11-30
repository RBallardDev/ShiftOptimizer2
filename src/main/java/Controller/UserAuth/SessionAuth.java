package Controller.UserAuth;

import main.java.Model.Token;

public class SessionAuth {
    private static String sessionDecryptedCode;

    static String getSessionDecryptedCode() {
        return sessionDecryptedCode;
    }

    static void setSessionDecryptedCode(String sessionDecryptedCode) {
        SessionAuth.sessionDecryptedCode = sessionDecryptedCode;
    }

    public static String authenticateToken(Token token){

        if(PasswordAuth.passwordMatches(sessionDecryptedCode, token.getEncryptedPassword())){
            return token.getUsername();
        }
        else return null;
    }
}
