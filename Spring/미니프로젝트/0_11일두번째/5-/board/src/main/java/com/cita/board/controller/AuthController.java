package com.cita.board.controller;

import com.cita.board.dto.ResponseDto;
//import com.cita.board.dto.SignInDto;
import com.cita.board.dto.SignUpDto;
//import com.cita.board.service.AuthService;
//import com.cita.board.dto.SignInResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//PS C:\Users\citad\eclipse-workspace\boardspring> .\gradlew clean build
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
//	@Autowired 
//	private AuthService authService;
	
	@PostMapping("/signUp")
	public ResponseDto<?> signUp(@RequestBody SignUpDto requestBody) {
		System.out.println(requestBody.toString());
		//ResponseDto<?> result = authService.signup(requestBody);
		//return result;
		return null;
	}
	
//	@PostMapping("/signIn")
//	public ResponseDto<SignInResponseDto> signIn(@RequestBody SignInDto requestBody) {
//		ResponseDto<SignInResponseDto> result = authService.signIn(requestBody);
//		return result;
//	}
}

