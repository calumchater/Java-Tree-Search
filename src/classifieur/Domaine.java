package classifieur;

public abstract class Domaine {

	public abstract boolean inclus(Domaine englobant) throws DomaineIncompatibleException;

	public abstract boolean appartient(String obs);
	
	public abstract String toString();
}
