package br.edu.ifpb.mt.ads.dac.util.criptografia;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CriptografiaSHA256 {

	public static String getStringCriptografada(String string) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(string.getBytes("UTF-8"));
			byte[] digest = md.digest();
			String output = Base64.getEncoder().encodeToString(digest);
			return output;
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new RuntimeException();
		}
	}

}
