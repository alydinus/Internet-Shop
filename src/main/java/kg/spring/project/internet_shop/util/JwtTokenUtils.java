package kg.spring.project.internet_shop.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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

  public String generateToken(UserDetails userDetails) {
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
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
  }
  private Claims getClaimsFromToken(String token) {
    return Jwts.parser()
        .setSigningKey(secret)
        .parseClaimsJws(token)
        .getBody();
  }

  public String getUsername(String token) {
    return getClaimsFromToken(token).getSubject();
  }

  public List<String> roles(String token) {
    return getClaimsFromToken(token).get("roles", List.class);
  }
}
