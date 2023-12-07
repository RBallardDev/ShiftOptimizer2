package Controller.XMLControllers;
import Model.Token;
public class Session {
    protected static Token token;
    public static Token getToken() {
        return token;
    }
    public static void setToken(Token token) {
        Session.token = token;
    }
}