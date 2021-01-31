package com.wwsis.modelowanie.healthgenic.security;

import com.wwsis.modelowanie.healthgenic.dao.UserRepository;
import com.wwsis.modelowanie.healthgenic.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class JwtProvider {

    @Value("${secret-key}")
    private final String secret;

    @Value("${expiration-time}")
    private final String expirationTime;

    private Key key;

    UserRepository repository;

//    public JwtProvider(UserRepository repository) {
//        this.repository = repository;
//    }

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRoles());

        long expiration = Long.parseLong(expirationTime);
        Date createdAt = new Date();
        Date expiredAt = new Date(createdAt.getTime() + expiration * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(createdAt)
                .setExpiration(expiredAt)
                .signWith(key)
                .compact();
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getIdFromToken(String token) {
        if (token.startsWith("Bearer")) token = token.substring(7);
        User found = repository.findByUsername(getIdFromToken(token)).orElse(null);
        return (found != null) ? found.getId() : null;
    }

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Date getExpirationTimeFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    private Boolean isTokenExpired(String token) {
        return getExpirationTimeFromToken(token).before(new Date());
    }
}


//package com.wwsis.modelowanie.healthgenic.security;
//
//import com.wwsis.modelowanie.healthgenic.dao.UserRepository;
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.impl.DefaultClock;
//import lombok.AccessLevel;
//import lombok.AllArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Component
//@Slf4j
//@AllArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//public class JwtProvider {
//    Clock CLOCK = DefaultClock.INSTANCE;
//    String SECRET = "s3cr3t";
//    Long EXPIRATION = 3600L;
//
//    public String getUsernameFromToken(String token) {
//        return getClaimFromToken(token, Claims::getSubject);
//    }
//
//    public Date getIssuedAtDateFromToken(String token) {
//        return getClaimFromToken(token, Claims::getIssuedAt);
//    }
//
//    public Date getExpirationDateFromToken(String token) {
//        return getClaimFromToken(token, Claims::getExpiration);
//    }
//
//    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = getAllClaimsFromToken(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims getAllClaimsFromToken(String token) {
//        return Jwts.parser()
//                .setSigningKey(SECRET)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    private Boolean isTokenExpired(String token) {
//        return getExpirationDateFromToken(token).before(CLOCK.now());
//    }
//
//    private Boolean ignoreTokenExpiration(String token) {
//        return getUsernameFromToken(token).equals(SECRET);
//    }
//
//    public String generateToken(UserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//        return doGenerateToken(claims, userDetails.getUsername());
//    }
//
//    public String generateTokenFromAuthentication(Authentication authentication) {
//        JwtUserPrincipal user = (JwtUserPrincipal) authentication.getPrincipal();
//        Date expiryTime = new Date(new Date().getTime() + EXPIRATION);
//
//        return Jwts.builder()
//                .setSubject(user.getUsername())
//                .setIssuedAt(new Date())
//                .setExpiration(expiryTime)
//                .signWith(SignatureAlgorithm.HS512, SECRET)
//                .compact();
//    }
//
//    public String getUsernameFromJWT(String token) {
//        return Jwts.parser()
//                .setSigningKey(SECRET)
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//
//    public boolean validateToken(String authToken) {
//        try {
//            Jwts.parser()
//                    .setSigningKey(SECRET)
//                    .parseClaimsJws(authToken);
//            return true;
//        } catch (SignatureException ex) {
//            log.error("Invalid JWT signature");
//        } catch (MalformedJwtException ex) {
//            log.error("Invalid JWT token");
//        } catch (ExpiredJwtException ex) {
//            log.error("Expired JWT token");
//        } catch (UnsupportedJwtException ex) {
//            log.error("Unsupported JWT token");
//        } catch (IllegalArgumentException ex) {
//            log.error("JWT claims string is empty.");
//        }
//        return false;
//    }
//
//    private String doGenerateToken(Map<String, Object> claims, String subject) {
//        final Date createdDate = CLOCK.now();
//        final Date expirationDate = calculateExpirationDate(createdDate);
//
//        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(createdDate)
//                .setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, SECRET).compact();
//    }
//
//    public Boolean canTokenBeRefreshed(String token) {
//        return (!isTokenExpired(token) || ignoreTokenExpiration(token));
//    }
//
//    public String refreshToken(String token) {
//        final Date createdDate = CLOCK.now();
//        final Date expirationDate = calculateExpirationDate(createdDate);
//
//        final Claims claims = getAllClaimsFromToken(token);
//        claims.setIssuedAt(createdDate);
//        claims.setExpiration(expirationDate);
//
//        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, SECRET).compact();
//    }
//
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        JwtUserPrincipal user = (JwtUserPrincipal) userDetails;
//        final String username = getUsernameFromToken(token);
//        return (username.equals(user.getUsername()) && !isTokenExpired(token));
//    }
//
//    private Date calculateExpirationDate(Date createdDate) {
//        return new Date(createdDate.getTime() + EXPIRATION * 1000);
//    }
//}
