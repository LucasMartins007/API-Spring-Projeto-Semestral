package com.daki.domain.util;

import java.io.UnsupportedEncodingException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DomainUtil {

	public static String criptografaSenha(String senha) {
		try {
			senha = criptografaString(senha);
			return senha;	
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static  String criptografaString(String string) throws NoSuchAlgorithmException, UnsupportedEncodingException {
	     MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
	     byte[] messageDigest = algorithm.digest(string.getBytes(StandardCharsets.UTF_8));
	     StringBuilder hexString = new StringBuilder();
	     for (byte b : messageDigest) {
	     	hexString.append(String.format("%02X", 0xFF & b));
	     }
		return hexString.toString();
	}

//	public static EnderecoTO validateCep(String cep) {
//		String webService = "http://viacep.com.br/ws/";
//		int codigoSucesso = 200;
//
//		String urlParaChamada = webService + cep + "/json";
//
//		try {
//			URL url = new URL(urlParaChamada);
//			HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
//
//			if (conexao.getResponseCode() != codigoSucesso)
//				throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());
//
//			BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
//			String jsonEmString = CepUtil.converteJsonEmString(resposta);
//
//			Gson gson = new Gson();
//			EnderecoTO endereco = gson.fromJson(jsonEmString, EnderecoTO.class);
//
//			if (jsonEmString.equals("{  \"erro\": true}")) {
//				throw new DomainException(EnumDomainException.NOT_FOUND_CEP.formatMessage(cep));
//			}
//
//			return endereco;
//
//		} catch (Exception e) {
//			throw new DomainException(EnumDomainException.INVALID_CEP.formatMessage(cep));
//		}
//	}
}
