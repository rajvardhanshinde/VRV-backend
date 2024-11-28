package VRV.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;

import java.util.Date;

@Component
public class JWTUtility {

    // Use an environment variable or config file to store the secret key securely
    private static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 256-bit key for HMACSHA256

    /**
     * Generate a JWT token with the specified email and role.
     *
     * @param email the email of the user
     * @param role  the role of the user
     * @return the generated JWT token
     */
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours expiration
                .signWith(secretKey)  // Use the SecretKey for signing
                .compact();
    }

    /**
     * Extract the role from the JWT token.
     *
     * @param token the JWT token
     * @return the role contained in the token
     */
    public String extractRole(String token) {
        Claims claims = parseClaims(token);
        return claims.get("role", String.class);
    }

    /**
     * Extract the email (subject) from the JWT token.
     *
     * @param token the JWT token
     * @return the email (subject)
     */
    public String extractEmail(String token) {
        Claims claims = parseClaims(token);
        return claims.getSubject();
    }

    /**
     * Check if the token is expired.
     *
     * @param token the JWT token
     * @return true if expired, false otherwise
     */
    public boolean isTokenExpired(String token) {
        Date expirationDate = parseClaims(token).getExpiration();
        return expirationDate.before(new Date());
    }

    /**
     * Validate the token by matching the email and checking expiration.
     *
     * @param token the JWT token
     * @param email the email to validate against
     * @return true if valid, false otherwise
     */
    public boolean validateToken(String token, String email) {
        return email.equals(extractEmail(token)) && !isTokenExpired(token);
    }

    /**
     * Parse the JWT token and return the claims.
     *
     * @param token the JWT token
     * @return the claims extracted from the token
     * @throws RuntimeException if token is invalid or expired
     */
    private Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)  // Use the SecretKey for parsing
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException e) {
            throw new RuntimeException("Invalid JWT signature. The token's signature does not match the expected signature.", e);
        } catch (Exception e) {
            throw new RuntimeException("Invalid token", e); // Handle other parsing errors
        }
    }
    
}
