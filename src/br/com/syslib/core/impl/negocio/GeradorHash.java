package br.com.syslib.core.impl.negocio;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GeradorHash {
	
	public String senhaHash(String senha) {
        
        MessageDigest algorithm = null;

        try {
            algorithm = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {

        }
        byte messageDigest[];
        try {
            messageDigest = algorithm.digest(senha.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }
            senha = hexString.toString();
        } catch (UnsupportedEncodingException ex) {

        }
        return senha;

    }

}
