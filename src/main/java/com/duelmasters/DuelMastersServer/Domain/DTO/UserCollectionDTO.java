package com.duelmasters.DuelMastersServer.Domain.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class UserCollectionDTO extends UserDTO {

	private List<String> collection;

	public UserCollectionDTO() {
		super();
	}

	@Override
	public String toString() {
		super.toString();
		return "UserCollectionDTO [collection = " + collection + "]\n";
	}

}
