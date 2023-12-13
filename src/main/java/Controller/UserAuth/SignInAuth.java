package Controller.UserAuth;

import Model.Token;

import static Controller.File.JacksonGetter.*;

public class SignInAuth {

    //Temp Signin
    public static Token signIn(String username, String password) {

        int index = getWorkerIndexFromUsername(username);
        if (index != -1 && PasswordAuth.passwordMatches(password, getWorkerPasswordFromIndex(index))) {
            //Create a token
            return SessionTokenBuilder.buildToken(username);
        }

        index = getManagerIndexFromUsername(username);
        if(index != -1 && PasswordAuth.passwordMatches(password, getManagerPasswordFromIndex(index))){
            return SessionTokenBuilder.buildToken(username);
        }


        return null;
    }
}
