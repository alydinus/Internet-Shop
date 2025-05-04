package kg.spring.project.internet_shop.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtils {
  @Value("${jwt.secret}")
  private String secret;
  @Value("${jwt.expiration}")
  private long expiration;

  public String generateAccessToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    List<String> rolesList = userDetails.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority).toList();
    claims.put("roles", rolesList);

    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + expiration);
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(now)
        .setExpiration(expiryDate)
        .signWith(getSignKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  public String generateRefreshToken(UserDetails user) {
    return Jwts.builder()
        .setSubject(user.getUsername())
        .setIssuedAt(new Date())
        .setExpiration(Date.from(Instant.now().plus(7, ChronoUnit.DAYS)))
        .signWith(getSignKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  private Claims getClaimsFromToken(String token) {
    return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token)
        .getBody();
  }

  public String getUsername(String token) {
    return getClaimsFromToken(token).getSubject();
  }

  public List<String> roles(String token) {
    return getClaimsFromToken(token).get("roles", List.class);
  }

  private Key getSignKey() {
    return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = getUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return Jwts.parserBuilder().setSigningKey(getSignKey()).build()
        .parseClaimsJws(token).getBody().getExpiration().before(new Date());
  }
}
