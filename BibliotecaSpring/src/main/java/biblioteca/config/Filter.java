package biblioteca.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Filter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if(request.getHeader("Authorization") != null) {
			try {
				Authentication auth = TokenUtil.decodeToken(request);
				if (auth != null) {
					SecurityContextHolder.getContext().setAuthentication(auth);
				} else {
					System.out.println("token inválido");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
		}
		filterChain.doFilter(request, response);	
	}
}
