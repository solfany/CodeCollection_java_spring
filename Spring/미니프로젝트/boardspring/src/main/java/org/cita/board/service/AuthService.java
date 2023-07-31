package org.cita.board.service;

import org.cita.board.dto.ResponseDto;
import org.cita.board.dto.SignInDto;
import org.cita.board.dto.SignInResponseDto;
import org.cita.board.dto.SignUpDto;
import org.cita.board.entity.UserEntity;
import org.cita.board.repository.UserRepository;
import org.cita.board.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	@Autowired UserRepository userRepository;	
	@Autowired TokenProvider tokenProvider;
	
	public ResponseDto<?> signup(SignUpDto dto){
		String userPassword = dto.getUserPassword();
	    String userPasswordCheck = dto.getUserPasswordCheck();
	    
	    String userEmail = dto.getUserEmail();
	    
		//이메일 중복확인
	    try {
	    if(userRepository.existsById(userEmail ))
	    	return ResponseDto.setFailed("Existed Email");
	    } catch(Exception error) {
	    	return ResponseDto.setFailed("Data base Error");
	    }
	    
	    if(!userPassword.equals(userPasswordCheck))
	    	return ResponseDto.setFailed("Password does not matched!!");
//	    else {
//	    	// 비밀번호가 일치하는 경우에 대한 처리 로직을 여기에 추가하세요.
//	    	// 예를 들어, 회원가입을 처리한 후 성공적인 응답을 반환하려면 아래와 같이 작성할 수 있습니다.
//	    	return ResponseDto.setSuccess("Signup successful.", null);
	    
	    	UserEntity userEntity = new UserEntity(dto);
	    	//UserRepository를 이용해서 데이터베이스에 Entity에 저장
	    	userRepository.save(userEntity);
	    	
	    	  try {
	    		    userRepository.save(userEntity);	    		    	
	    		    } catch(Exception error) {
	    		    	return ResponseDto.setFailed("Data base Error");
	    		    }
	     //성공시 success response반환
	    	return ResponseDto.setSuccess("Signup successful.", null);
	    }
	public ResponseDto<SignInResponseDto> signIn(SignInDto dto){
		String userEmail = dto.getUserEmail();
		String userPassword = dto.getUserPassword();
		try {
			boolean existed = userRepository.existsByUserEmailAndUserPassword(userEmail, userPassword);
			if(!existed) 
				return ResponseDto.setFailed("sign in information does not match");
		}catch(Exception error) {
			return ResponseDto.setFailed("Database Error");
		}
		UserEntity userEntity = null;
		try {
			userEntity = userRepository.findById(userEmail).get();
		}catch(Exception error) {
			return ResponseDto.setFailed("Database Error");
		}		
		
		userEntity.setUserPassword("");
		
		String token = tokenProvider.create(userEmail);		
		int exprTime = 3600000;
		
		SignInResponseDto signInReposeDto = new SignInResponseDto(token, exprTime, userEntity);
		return ResponseDto.setSuccess("sign in success", signInReposeDto);
		
	}
}
