package Model;

public class Token {
    private String username;
    private String encryptedPassword;

    public Token(String username, String encryptedPassword){
        this.username = username;
        this.encryptedPassword = encryptedPassword;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }


}
