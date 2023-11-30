package Controller.XMLControllers;
import Model.Token;
public class Session {
    protected static Token token;
    protected static Token getToken() {
        return token;
    }
    protected static void setToken(Token token) {
        Session.token = token;
    }
}