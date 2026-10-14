
package com.example.jobtracker;

import com.example.jobtracker.entity.User;
import com.example.jobtracker.repository.UserRepository;
import com.example.jobtracker.service.CustomUserDetailsService;
import com.example.jobtracker.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import io.jsonwebtoken.JwtException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobtrackerApplicationTests {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private CustomUserDetailsService userDetailsService;

	private final JwtService jwtService = new JwtService();


	@Test
	void extractUsernameShouldRejectExpiredToken() {
		String secret =
				"my-jobtracker-secret-key-my-jobtracker-secret-key";

		SecretKey key = Keys.hmacShaKeyFor(
				secret.getBytes(StandardCharsets.UTF_8)
		);

		long now = System.currentTimeMillis();

		String expiredToken = Jwts.builder()
				.subject("testuser")
				.issuedAt(new Date(now - 120_000))
				.expiration(new Date(now - 60_000))
				.signWith(key)
				.compact();

		assertThrows(
				JwtException.class,
				() -> jwtService.extractUsername(expiredToken)
		);
	}

	@Test
	void extractUsernameShouldRejectTamperedSignature() {
		String token = jwtService.generateToken("testuser");

		String tamperedToken = token.substring(0, token.length() - 2)
				+ (token.endsWith("a") ? "b" : "a");

		assertThrows(
				JwtException.class,
				() -> jwtService.extractUsername(tamperedToken)
		);
	}


	@Test
	void loadUserByUsernameShouldReturnUserDetails() {
		User user = new User();
		user.setUsername("testuser");
		user.setPassword("encoded-password");
		user.setRole("USER");

		when(userRepository.findByUsername("testuser"))
				.thenReturn(Optional.of(user));

		UserDetails result =
				userDetailsService.loadUserByUsername("testuser");

		assertEquals("testuser", result.getUsername());
		assertEquals("encoded-password", result.getPassword());

		assertTrue(result.getAuthorities().stream()
				.anyMatch(authority ->
						authority.getAuthority().equals("ROLE_USER")));

		verify(userRepository).findByUsername("testuser");
	}

	@Test
	void loadUserByUsernameShouldThrowWhenUserDoesNotExist() {
		when(userRepository.findByUsername("unknown"))
				.thenReturn(Optional.empty());

		assertThrows(
				UsernameNotFoundException.class,
				() -> userDetailsService.loadUserByUsername("unknown")
		);

		verify(userRepository).findByUsername("unknown");
	}

	@Test
	void generateTokenShouldContainCorrectUsername() {
		String token = jwtService.generateToken("testuser");

		assertNotNull(token);
		assertFalse(token.isBlank());
		assertEquals("testuser", jwtService.extractUsername(token));
	}

	@Test
	void extractUsernameShouldReturnCorrectUsername() {
		String token = jwtService.generateToken("clmuser");

		assertEquals(
				"clmuser",
				jwtService.extractUsername(token)
		);
	}

	@Test
	void extractUsernameShouldRejectInvalidToken() {
		String invalidToken = "this.is.not-a-valid-jwt";

		assertThrows(
				JwtException.class,
				() -> jwtService.extractUsername(invalidToken)
		);
	}
}
