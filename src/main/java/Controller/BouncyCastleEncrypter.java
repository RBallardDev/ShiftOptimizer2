package Controller;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.util.encoders.Hex;


public class BouncyCastleEncrypter {
    public static String hashPassword(String plainTextPassword) {
        Digest digest = new SHA256Digest();
        byte[] data = plainTextPassword.getBytes();
        byte[] hash = new byte[digest.getDigestSize()];

        digest.update(data, 0, data.length);
        digest.doFinal(hash, 0);

        return new String(Hex.encode(hash));
    }




}
