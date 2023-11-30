package Controller.UserAuth;

import main.java.Model.Token;

import static main.java.Controller.File.JacksonGetter.getIndexFromUsername;
import static main.java.Controller.File.JacksonGetter.getPasswordFromIndex;

public class SignInAuth {

    //Temp Signin
    public static Token signIn(String username, String password) {
        int index = getIndexFromUsername(username);
        if (index != -1 && PasswordAuth.passwordMatches(password, getPasswordFromIndex(index))) {
            //Create a token
            return SessionTokenBuilder.buildToken(username);
        }
        return null;
    }
}
