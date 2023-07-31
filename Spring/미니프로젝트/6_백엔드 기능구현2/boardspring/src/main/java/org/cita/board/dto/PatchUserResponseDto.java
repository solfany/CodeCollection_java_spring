package org.cita.board.dto;

import org.cita.board.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchUserResponseDto {
	
	private UserEntity user;

}
