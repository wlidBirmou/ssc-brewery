package guru.sfg.brewery.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestHeaderAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


    protected RestHeaderAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    public RestHeaderAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String username = request.getHeader("api-key");
        String password = request.getHeader("api-secret");

        if (username == null) username = "";
        if (password == null) password = "";
        AbstractAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);


        return this.getAuthenticationManager().authenticate(token);
    }
}
