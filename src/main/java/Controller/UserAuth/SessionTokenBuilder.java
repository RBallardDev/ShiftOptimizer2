package Controller.UserAuth;

import Controller.BouncyCastleEncrypter;
import Model.Token;

import java.util.UUID;
public class SessionTokenBuilder {


    public static Token buildToken(String username){
        SessionAuth.setSessionDecryptedCode(generateRandomCode(10));
        return new Token(username, BouncyCastleEncrypter.hashPassword(SessionAuth.getSessionDecryptedCode()));
    }



    private static String generateRandomCode(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }

        // Generate a random UUID
        UUID uuid = UUID.randomUUID();

        // Convert the UUID to a string and remove hyphens
        String alphanumericCode = uuid.toString().replace("-", "");

        // Take the first 'length' characters
        return alphanumericCode.substring(0, Math.min(length, alphanumericCode.length()));
    }
}
