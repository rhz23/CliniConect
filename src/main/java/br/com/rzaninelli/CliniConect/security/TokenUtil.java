package br.com.rzaninelli.CliniConect.security;

import br.com.rzaninelli.CliniConect.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

public class TokenUtil {

    //Parametros de configuração do TOKEN
    // - duração
    // - emisson
    // - chave

    public static final long SEGUNDOS = 1000;
    public static final long MINUTOS = 60 * SEGUNDOS;
    public static final long HORAS = 60 * MINUTOS;
    public static final long DIAS = 24 * HORAS;
    public static final long EXPIRATION = 5 * DIAS;

    public static final String ISSUER = "*br.com.rzaninelli*";

    public static final String SECRET_KEY = "01234567890123456789012345678901";

    public static final String PREFIX = "Bearer ";
    private static final Logger log = LoggerFactory.getLogger(TokenUtil.class);

    public static CliniToken encode(Usuario usuario) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        String jws = Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject(usuario.getLogin())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return new CliniToken(PREFIX + jws);
    }

    public static Authentication decode(HttpServletRequest request) {

        try {

            String token = request.getHeader("Authorization");
            token = token.replace(PREFIX, "");
            Jws<Claims> claims;
            claims = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())).build().parseSignedClaims(token);
            String subject = claims.getBody().getSubject();
            String issuer = claims.getBody().getIssuer();
            Date expiration = claims.getBody().getExpiration();

            if (isValid(token, issuer, expiration)) {
                return new UsernamePasswordAuthenticationToken(subject, null, Collections.emptyList());
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return null;
    }

    public static boolean isValid(String subject, String issuer, Date expiration) {
        return subject != null && !subject.isEmpty() && issuer.equals(ISSUER) && expiration.after(new Date(System.currentTimeMillis()));
    }
}
