package com.aleixo.lbd.security;

import java.io.IOException;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtils {

	private static final String KEY = "spring.jwt.sec";

	
	public static String generateToken(Authentication usuario, com.aleixo.lbd.model.User user) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Login usuarioSemSenha = new Login();
		usuarioSemSenha.setUsername(usuario.getName());
		if (!user.getRole().isEmpty()) {
			usuarioSemSenha.setAutorizacao(user.getRole());
			usuarioSemSenha.setId(user.getId());
		}
		String usuarioJson = mapper.writeValueAsString(usuarioSemSenha);
		Date agora = new Date();
		Long hora = 1000L * 60L * 60L; // Uma hora
		return Jwts.builder().claim("userDetails", usuarioJson).setIssuer("com.aleixo.lbd")
				.setSubject(usuario.getName()).setExpiration(new Date(agora.getTime() + hora))
				.signWith(SignatureAlgorithm.HS512, KEY).compact();
	}

	public static Authentication parseToken(String token) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String credentialsJson = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody().get("userDetails",
				String.class);
		Login usuario = mapper.readValue(credentialsJson, Login.class);
		UserDetails userDetails = User.builder().username(usuario.getUsername()).password("secret")
				.authorities(usuario.getAutorizacao()).build();
		return new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword(),
				userDetails.getAuthorities());
	}
}
