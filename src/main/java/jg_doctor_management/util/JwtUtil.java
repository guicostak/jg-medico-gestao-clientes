package jg_doctor_management.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY;

    public JwtUtil() {
        this.SECRET_KEY = generateSecretKey();
    }

    public String generateSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32]; // 256 bits
        random.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }

    public String generateToken(String username) {
        return createToken(username, generateTokenExpirationDate());
    }

    public String generateRefreshToken(String username) {
        return createToken(username, generateRefreshTokenExpirationDate());
    }

    private String createToken(String subject, Date expirationDate) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        return JWT.create()
                .withSubject(subject)
                .withIssuedAt(new Date())
                .withExpiresAt(expirationDate)
                .sign(algorithm);
    }

    public Boolean validateToken(String token, String username) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            String extractedUsername = decodedJWT.getSubject();
            return (extractedUsername.equals(username) && !isTokenExpired(decodedJWT));
        } catch (Exception exception) {
            return false;
        }
    }

    private Boolean isTokenExpired(DecodedJWT decodedJWT) {
        Date expiration = decodedJWT.getExpiresAt();
        return expiration.before(new Date());
    }

    public Date generateTokenExpirationDate() {
        return new Date(System.currentTimeMillis() + 1000 * 60 * 60); // 1 hora de validade
    }

    public Date generateRefreshTokenExpirationDate() {
        return new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7); // 7 dias de validade
    }

    public String encryptPassword(String rawPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(rawPassword);
    }

    public boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
