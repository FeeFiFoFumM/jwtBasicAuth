package com.basicjwt.basicjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basicjwt.basicjwt.model.entity.User;
import com.basicjwt.basicjwt.request.LoginRequest;
import com.basicjwt.basicjwt.request.RegisterRequest;
import com.basicjwt.basicjwt.response.UserResponse;
import com.basicjwt.basicjwt.security.JwtTokenProvider;
import com.basicjwt.basicjwt.services.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {

       // private UserResponse userResponse;
    
        private AuthenticationManager authenticationManager;
        
        private JwtTokenProvider jwtTokenProvider;
        
        private UserService userService;
        
        private PasswordEncoder passwordEncoder;
        @Autowired
        public LoginController(AuthenticationManager authenticationManager, 
                           JwtTokenProvider jwtTokenProvider, 
                           UserService userService, 
                           PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
        
    
        // Yeni kullanıcı oluşturma
        
        @PostMapping("/register")
        public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest registerRequest) {
            User user = new User();
            UserResponse userResponse = new UserResponse();
            user.setUsername(registerRequest.getUsername());
            user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            user.setEmail(registerRequest.getEmail());
            userService.saveOneUser(user);
            userResponse.setUsername(user.getUsername());
            userResponse.setEmail(user.getEmail());
            return ResponseEntity.ok(userResponse);
    
    }

    /* 
    @PostMapping("/register")
	public ResponseEntity<UserDto> register(@RequestBody LoginRequest registerRequest) {
		UserDto authResponse = new UserDto();
		if(userService.getOneUserByUserName(registerRequest.getUsername()) != null) {
			authResponse.setMessage("Username already in use.");
			return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
		}
		
		User user = new User();
		user.setUserName(registerRequest.getUsername());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		userService.saveOneUser(user);
		
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(registerRequest.getUserName(), registerRequest.getPassword());
		Authentication auth = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwtToken = jwtTokenProvider.generateJwtToken(auth);
		
		authResponse.setMessage("User successfully registered.");
		authResponse.setAccessToken("Bearer " + jwtToken);
		authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
		authResponse.setUserId(user.getId());
		return new ResponseEntity<>(authResponse, HttpStatus.CREATED);		
	}
    */

    // Giriş işlemi
    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Kullanıcı kimlik doğrulama
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()));

            // Başarılı kimlik doğrulaması sonrası JWT token oluştur
            String jwtToken = jwtTokenProvider.generateJwtTokenByUsername(loginRequest.getUsername());

            // JWT token'ı ResponseEntity ile dön
            return ResponseEntity.ok(jwtToken);

        } catch (AuthenticationException e) {
            // Hatalı giriş durumunda UNAUTHORIZED hatası döndür
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Geçersiz kimlik bilgileri");
        } catch (Exception e) {
            // Diğer hatalar için 500 INTERNAL_SERVER_ERROR döndür
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Bir hata oluştu");
        }
    }
}
