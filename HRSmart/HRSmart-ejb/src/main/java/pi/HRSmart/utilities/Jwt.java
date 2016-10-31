package pi.HRSmart.utilities;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hadhe on 10/31/2016.
 */
public class Jwt {

    public static String SignJWT(String nameOfObject, Object theObject) {

        final String  issuer ="https://HRSmart.com";
        final String  secret ="1993";
        final long iat = System.currentTimeMillis() / 1000L; // issued at claim
        final long exp = iat + 60L; // expires claim. In this case the token expires in 60 seconds

        final JWTSigner signer = new JWTSigner(secret);
        final HashMap<String, Object> claims = new HashMap<String, Object>();
        claims.put("iss", issuer);
        claims.put("exp", exp);
        claims.put("iat", iat);
        claims.put(nameOfObject, theObject);

        final String jwt = signer.sign(claims);
        return jwt;
    }

    public static Map<String, Object> VerifyJWT(String jwtToVerify) {

        final String  secret ="1993";
        try {
            final JWTVerifier verifier = new JWTVerifier(secret);
            final Map<String, Object> claims = verifier.verify(jwtToVerify);
            System.out.println("Verification : " + claims);
            return claims;
        } catch (JWTVerifyException | InvalidKeyException | NoSuchAlgorithmException | IllegalStateException | SignatureException | IOException e) {
            // Invalid Token
            System.out.println("FAIL ");
            return null;
        }

    }
}

