package com.duelmasters.DuelMastersServer.Domain;

public enum Abilities {

	//WTCA
	WTCA01("Choose0(1, GV0) + SEL:MTH"),
	WTCA02("Count8(BZ0) + DRAW(SEL)"),
	WTCA03("Choose1(1, ~FIRE, MN0) + SEL:MTG + OP{Choose1(1, ~FIRE, MN0) - SEL:MTG}"),
	WTCA04("???"),
	WTCA05("Choose0(2, MN0) + SEL:MTH"),
	WTCA06("Choose0(1, MN1) + SEL:MTG"),
	WTCA07("???"),
	WTCA08("Count1(WATER, BZ0) + DRAW(SEL)"),
	WTCA09("Get0(MN0) + SEL:MTH"),
	WTCA10(""),
	WTCA11(""),
	WTCA12("Get(1) + SEL:MTS"),
	WTCA13("Get0(BZ1) + SEL:TAP"),
	WTCA14("Choose0(1, HD0) + SEL:MTS/MTM"),
	WTCA15("Choose0(1, MN0) + SEL:MTH"),
	WTCA16("???"),
	WTCA17("Choose0(1, HD0) + SEL:MTG"),
	WTCA18("???"),
	WTCA19("Get11(GV0, LIGHT) + SEL:MTH"),
	WTCA20("Choose0(2, MN1) + SEL:MTG"),
	WTCA21("???"),
	WTCA22("OP{Choose0(1, HD0) - SEL:MTG}"),
	WTCA23("Choose3(1, 4000, BZ1) + SEL:MTG"),
	WTCA24("Look(2, SD1)"),
	WTCA25("OP{Get0(HD1) - SEL:MTG}"),
	WTCA26("SEL:Give(TCCG3000, EOT)"),
	WTCA27("Choose3(SEL, BZ1) + SEL:MTH"),
	WTCA28("Choose9(1, BZ0) + ?"),
	WTCA29("Choose0(1, SD0) + SEL:MTG"),
	WTCA30("Choose0(1, SD0) + SEL:MTG + ?"),
	WTCA31("Choose0(1, BZ0) + SEL:MTG"),
	WTCA32("Choose3(SEL, BZ1) + SEL:MTG"),
	//new format
	WTCA33("CHS_0_1_NUL_MN0 MTH"),
	
	WTCA34("Choose11(1, DK0) + SEL:{MTH, SHOW} + ?"),
	WTCA35("Choose1(1, DK0) + SEL:{MTH, SHOW} + ?"),
	WTCA36("Choose0(1, MZ1) + SEL:MTH"),
	WTCA37("Choose3(1, 2000, BZ2) + SEL:MTH"),
	WTCA38("Choose1(1, DARKNESS/FIRE, BZ2) + SEL:TAP"),
	WTCA39("Choose1(1, FIRE/NATURE, BZ0) + SEL:MTH"),
	WTCA40("Choose6(1, BZ1) + SEL:MTG"),
	WTCA41("Choose3(1, 2000, BZ1) + SEL:MTG"),
	WTCA42("Choose0(1, BZ0) + SEL:{Give(TCCG2000, EOT), Give(DB0, EOT)}"),
	WTCA43("Count0(BZ1) + DRAW(SEL)"),
	WTCA44("Look(1, SD1)"),
	WTCA45("Look(HD1) + Look(1, TD1)"),
	WTCA46("Choose0(1, MN0) + SEL:MTG + SEL0:{Give(PA2, EOT), Give(DB0, EOT)"),
	WTCA47("Choose1(1, NATURE, GV0) + SEL:MTZ"),
	WTCA48("Get12(1) + SEL:MTM"),
	WTCA49("Choose1(1, DARKNESS, GV0) + SEL:MTH"),
	WTCA50("Choose11(GV0, LIGHT) + SEL:MTH"),
	WTCA51("Choose10(1, HD0) + SEL:MTG"),
	WTCA52("Count1(DARKNESS, MN0) + ? + Choose10(1, HD1) + MTG"),
	WTCA53("Count1(WATER, MN0) + ?");
	
	private String ability;
	
	Abilities(String ability) { 
		this.ability = ability;
	}
	
	public String getAbility() {
		return ability;
	}
	
}
