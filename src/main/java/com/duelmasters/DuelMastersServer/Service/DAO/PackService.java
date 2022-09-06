package com.duelmasters.DuelMastersServer.Service.DAO;

import java.util.List;

import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Card;
import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Pack;

public interface PackService {

	public Pack createPack(Pack pack);
	
	public List<Pack> getAllPacks();
	
	public List<Card> openPack(Pack pack);
	
	public List<Card> openPackType(String packType);
	
}
