package com.duelmasters.DuelMastersServer.Service.DAO;

import java.util.List;

import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Card;
import com.duelmasters.DuelMastersServer.Domain.Entity.cards.Pack;

public interface PackService {

	public List<Card> openPack(Pack pack);
	
}
