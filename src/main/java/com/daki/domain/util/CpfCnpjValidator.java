package com.daki.domain.util;


import java.util.InputMismatchException;


import com.daki.domain.exception.DomainException;
import com.daki.domain.exception.enums.EnumDomainException;



public class CpfCnpjValidator {

	public static void validateCpf(String cpf) {
		if(StringUtil.isNotNullOrEmpty(cpf)) {
			if(!isCpf(cpf)) {
				throw new DomainException(EnumDomainException.CPF_INVALIDO.getMessage(), cpf);
			}
		}
	}

	public static void validateCnpj(String cnpj){
		if(StringUtil.isNotNullOrEmpty(cnpj)){
			if(!isCnpj(cnpj)){
				throw new DomainException(EnumDomainException.CNPJ_INVALIDO.getMessage(), cnpj);
			}
		}
	}
	
	private static boolean isCpf(String CPF) {
		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
				|| CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
				|| CPF.equals("99999999999") || (CPF.length() != 11))
			return (false);

		char dig10, dig11;
		int sm, i, r, num, peso;

		try {

			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {

				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48);

			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}


	private static boolean isCnpj(String cnpj) {
		cnpj = cnpj.replace(".", "");
		cnpj = cnpj.replace("-", "");
		cnpj = cnpj.replace("/", "");

		try{
			Long.parseLong(cnpj);
		} catch(NumberFormatException e){
			return false;
		}


		if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111")
				|| cnpj.equals("22222222222222") || cnpj.equals("33333333333333")
				|| cnpj.equals("44444444444444") || cnpj.equals("55555555555555")
				|| cnpj.equals("66666666666666") || cnpj.equals("77777777777777")
				|| cnpj.equals("88888888888888") || cnpj.equals("99999999999999") || (cnpj.length() != 14))
			return (false);
		char dig13, dig14;
		int sm, i, r, num, peso;

		try {

			sm = 0;
			peso = 2;
			for (i = 11; i >= 0; i--) {

				num = (int) (cnpj.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso + 1;
				if (peso == 10)
					peso = 2;
			}

			r = sm % 11;
			if ((r == 0) || (r == 1))
				dig13 = '0';
			else
				dig13 = (char) ((11 - r) + 48);


			sm = 0;
			peso = 2;
			for (i = 12; i >= 0; i--) {
				num = (int) (cnpj.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso + 1;
				if (peso == 10)
					peso = 2;
			}
			r = sm % 11;
			if ((r == 0) || (r == 1))
				dig14 = '0';
			else
				dig14 = (char) ((11 - r) + 48);
			if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}
}
