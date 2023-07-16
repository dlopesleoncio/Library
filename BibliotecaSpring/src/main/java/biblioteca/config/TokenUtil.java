package biblioteca.config;

import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import jakarta.servlet.http.HttpServletRequest;

public class TokenUtil {
	public static Authentication decodeToken(HttpServletRequest request) throws Exception {
		if (request.getHeader("Authorization").equals("diogo")) {
			return new UsernamePasswordAuthenticationToken("user", null, Collections.emptyList());
		}
		return null;
	}
}
