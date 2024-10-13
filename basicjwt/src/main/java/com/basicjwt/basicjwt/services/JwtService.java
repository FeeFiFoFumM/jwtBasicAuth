package com.basicjwt.basicjwt.services;


/*
 * 
 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.security.core.userdetails.UserDetails;
 import org.springframework.stereotype.Service;
 import javax.crypto.SecretKey;
 import javax.crypto.spec.SecretKeySpec;
 import java.nio.charset.StandardCharsets;
 import io.jsonwebtoken.Claims;
 import io.jsonwebtoken.Jwts;
 import io.jsonwebtoken.security.MacAlgorithm;
 /* 
 import io.jsonwebtoken.io.Decoders;
 import io.jsonwebtoken.security.KeyPair;
 import io.jsonwebtoken.security.Keys;
 import io.jsonwebtoken.SignatureAlgorithm;
 import java.security.Key;
 */
/*
 * 
 import java.util.Date;
 import java.util.HashMap;
 import java.util.Map;
 import java.util.function.Function;
 
 @Service
 public class JwtService {
    @Value("${security.jwt.secret-key}")
    private String jwtSecret;
    int jwtExpiration = 3600000;
    String keyTest = "GFskdl2yMFHu4viZKq6m/5/uSebqK0ax+oPaigxJZGLtfO7SOg8L4cZ66vc7NXjuhd72bQ+zuwr5QV5LOEGEA==";
    
    MacAlgorithm alg = Jwts.SIG.HS512; // or HS384 or HS256
    
    SecretKey key = new SecretKeySpec(keyTest.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
    
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }
    
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }
    
    public long getExpirationTime() {
        return jwtExpiration;
    }
    
    private String buildToken(
        Map<String, Object> extraClaims,
        UserDetails userDetails,
        long expiration) {
            Date now = new Date(System.currentTimeMillis());
            Date expiryDate = new Date(now.getTime() + expiration);
            return Jwts
            .builder()
            .claims().empty().add(extraClaims).and()
            .claims().subject(userDetails.getUsername()).and()
            .claims().issuedAt(now).and()
            .claims().expiration(new Date((new Date()).getTime() + jwtExpiration)).and()
            .signWith(key, alg) // HMAC-SHA256 algoritması ile imzalama
            .compact();
        }
        
        public boolean isTokenValid(String token, UserDetails userDetails) {
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
        }
        
        private boolean isTokenExpired(String token) {
            return extractExpiration(token).before(new Date());
        }
        
        private Date extractExpiration(String token) {
            return extractClaim(token, Claims::getExpiration);
        }
        
        private Claims extractAllClaims(String token) {
            return Jwts
            .parser()
            .decryptWith(key)
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload();
        }
        */
        
        /*
        * 
        * private Key getSignInKey() {
            * byte[] keyBytes = Decoders.BASE64.decode(secretKey);
            * // java.security.KeyPair keyPair = Jwts.SIG.RS256.keyPair().build(); //or
            * RS384, RS512, PS256, etc...
            * return Keys.hmacShaKeyFor(keyBytes);
            * }
            */
            /* 
            * 
            */
            // SecretKey key = alg.key().build();
            
      //  }
        