package pi.HRSmart.utilities;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.auth0.jwt.internal.com.fasterxml.jackson.core.JsonFactory;
import com.auth0.jwt.internal.com.fasterxml.jackson.core.JsonParseException;
import com.auth0.jwt.internal.com.fasterxml.jackson.core.JsonParser;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.JsonNode;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.ObjectMapper;
import com.auth0.jwt.internal.org.apache.commons.codec.binary.CharSequenceUtils;
import com.auth0.jwt.internal.org.apache.commons.codec.binary.StringUtils;
import com.auth0.jwt.internal.org.bouncycastle.util.encoders.Base64;

import pi.HRSmart.interfaces.UserServiceLocal;
import pi.HRSmart.persistence.User;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 * Created by hadhe on 10/31/2016.
 */
public class Jwt {

	public static String SignJWT(String nameOfObject, Object theObject) {

		final String issuer = "https://HRSmart.com";
		final String secret = "1993";
		final long iat = System.currentTimeMillis() / 1000L; // issued at claim
		final long exp = iat + 60L; // expires claim. In this case the token
		User u = (User) theObject;		// expires in 60 seconds

		final JWTSigner signer = new JWTSigner(secret);
		final HashMap<String, Object> claims = new HashMap<String, Object>();
		claims.put("iss", issuer);
		claims.put("exp", exp);
		claims.put("iat", iat);
		claims.put(nameOfObject, u.getLogin());

		final String jwt = signer.sign(claims);
		return jwt;
	}

	public static Map<String, Object> VerifyJWT(String jwtToVerify) {

		final String secret = "1993";
		try {
			final JWTVerifier verifier = new JWTVerifier(secret);
			final Map<String, Object> claims = verifier.verify(jwtToVerify);
			System.out.println("Verification : " + claims);
			return claims;
		} catch (JWTVerifyException | InvalidKeyException | NoSuchAlgorithmException | IllegalStateException
				| SignatureException | IOException e) {
			// Invalid Token
			System.out.println("FAIL ");
			return null;
		}

	}

	public static String decodeJWT(String token) {
		String[] parts = token.split("\\.");

		String jwt = StringUtils.newStringUtf8(Base64.decode(parts[1]));
		jwt = cleanJson(jwt);

		return jwt;
	}

	public static String cleanJson(String jsonString) {

		jsonString = jsonString.substring(0, jsonString.length() - 3);
		jsonString = jsonString + "\"}";
		return jsonString;

	}

	public static JsonNode stringToJson(String s) {
		JsonNode jn = null;
		ObjectMapper om = new ObjectMapper();
		JsonFactory jf = om.getJsonFactory();
		try {
			JsonParser jp = jf.createJsonParser(s);
			jn = om.readTree(jp);

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			System.out.println("failed in parsing json in method stringToJson in class Jwt in package utilities");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// JsonObject jo =Json.createReader(new StringReader(s)).readObject();

		return jn;

	}
}
