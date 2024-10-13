package com.basicjwt.basicjwt.security;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


@Component
public class JwtTokenProvider {

	private final SecretKey key = Jwts.SIG.HS256.key().build();


	
	public String generateJwtToken(Authentication auth) {
		JwtUserDetails userDetails = (JwtUserDetails) auth.getPrincipal();
		Date expireDate = new Date(new Date().getTime() + 99999999);
		Date notBefore = new Date(System.currentTimeMillis()); // Token şu andan itibaren geçerli

		return Jwts.builder()
			.subject(userDetails.getUsername())
			.issuedAt(new Date())
			.expiration(expireDate)
			.notBefore(notBefore)
			.signWith(key)
			.compact();
	}
	
	public String generateJwtTokenByUserId(Long userId) {
		Date expireDate = new Date(new Date().getTime() + 99999999);
		return Jwts.builder()
			.subject(Long.toString(userId))
			.issuedAt(new Date())
			.expiration(expireDate)
			.signWith(key)
			.compact();
	}

	public String generateJwtTokenByUsername(String username) {
		Date expireDate = new Date(new Date().getTime() + 99999999);
		return Jwts.builder()
			.subject(username)
			.issuedAt(new Date())
			.expiration(expireDate)
			.signWith(key)
			.compact();
	}
	
	Long getUserIdFromJwt(String token) {
		Claims claims = Jwts.parser()
		.verifyWith(key)
		.build()
		.parseSignedClaims(token)
		.getPayload();
		return Long.parseLong(claims.getSubject());
	}
	
	boolean validateToken(String token) {
		try {
			Jwts.parser()
			.verifyWith(key)
			.build()
			.parseSignedClaims(token);
			return !isTokenExpired(token);
		} catch (SignatureException e) {
            return false;
        } catch (MalformedJwtException e) {
            return false;
        } catch (ExpiredJwtException e) {
            return false;
        } catch (UnsupportedJwtException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
	}

	private boolean isTokenExpired(String token) {
		Date expiration = Jwts.parser()
		.verifyWith(key)
		.build()
		.parseSignedClaims(token)
		.getPayload()
		.getExpiration();
		return expiration.before(new Date());
	}

}