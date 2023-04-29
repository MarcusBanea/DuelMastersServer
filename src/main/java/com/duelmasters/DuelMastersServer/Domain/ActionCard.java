package com.duelmasters.DuelMastersServer.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActionCard {

	private Integer index;
	private String zone;
	private String player;
	
}
