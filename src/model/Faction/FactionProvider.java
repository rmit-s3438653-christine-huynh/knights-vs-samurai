package model.Faction;

import model.Faction.Implementations.KnightFactionGeneratorImpl;
import model.Faction.Implementations.SamuraiFactionGeneratorImpl;

public class FactionProvider {

	public FactionGenerator getSamuraiFactionGenerator() {
		return new SamuraiFactionGeneratorImpl();
	}
	
	public FactionGenerator getKnightFactionGenerator() {
		return new KnightFactionGeneratorImpl();
	}
}
