package etc.bda.utils;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.management.relation.Role;

public class Util {

	public static final ExecutorService EXECUTOR_SERVICE_POOL = Executors.newFixedThreadPool(10);

	public static boolean checkUserType(String type) {
		String t = type.trim().toLowerCase();
		return t.equals("cpf") || t.equals("telegram") || t.equals("telefone") || t.equals("email")
				|| t.equals("blipid");
	}

	public static String normalizaWhatsapp(String s) {
		String digitos = s.replaceAll("[^0-9]", "");
		if (!digitos.startsWith("55"))
			digitos = "55".concat(digitos);

		return "whatsapp:+".concat(digitos);
	}

	public static boolean isEmptyString(String s) {
		if (s != null && !s.equals(""))
			return false;
		else
			return true;
	}

	public static boolean isEmptyString(Object s) {
		if (!(s instanceof String)) {
			return false;
		}
		if (s != null && !s.equals(""))
			return false;
		else
			return true;
	}

	public static boolean findRole(List<Role> roles, String identificador) {
		for (Role role : roles) {
			if (role.getRoleName().equals(identificador)) {
				return true;
			}
		}
		return false;
	}

	public static String formatDouble(String s) {
		if (isEmptyString(s))
			return null;

		s = s.replaceAll(",", ".");

		if (s.indexOf(".") == -1)
			return s.concat(".00");
		else if (s.indexOf(".") == s.length() - 2)
			return s.concat("0");
		else if (s.indexOf(".") == s.length() - 1)
			return s.concat("00");

		return s;
	}

}
