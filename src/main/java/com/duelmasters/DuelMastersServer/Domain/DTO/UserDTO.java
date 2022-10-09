package com.duelmasters.DuelMastersServer.Domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
	
	private String id;

	private String nickname;

	private Integer money;

	private Integer level;
	private Integer xp;

	public UserDTO() {
		super();
	}

	@Override
	public String toString() {
		return "UserDTO [nickname = " + nickname + ", money = " + money + ", level = " + level + ", xp = " + xp + "]\n";
	}
	
	

}
