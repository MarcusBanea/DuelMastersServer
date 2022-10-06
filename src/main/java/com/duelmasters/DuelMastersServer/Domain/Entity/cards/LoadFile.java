package com.duelmasters.DuelMastersServer.Domain.Entity.cards;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoadFile {

	private String name;
	private String type;
	private String size;
	private byte[] content;

	public LoadFile() {

	}

}
