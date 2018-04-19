package classifieur;
import java.util.*;

public class Ensemble extends Domaine {

	private List<String> attributs = new ArrayList<String>(); 	// On allouera une place de plus si on rajoute des
																// attributs lors de la creation de nouvelles typologies
	
//Constructeur
	public Ensemble(List<String> atts) {
		this.attributs.addAll(atts);
	}
	
//fonctions
	// Verifie que l'ensemble appellant est inclus dans l'ensemble en parametre. C'est un domaine sinon la fonction est différente
	public boolean inclus(Domaine englobant) throws DomaineIncompatibleException { 	
			boolean inclus = true;
			Iterator<String> iterator = this.attributs.iterator(); 					// on itere sur les attributs de l'ensemble appellant
			while (iterator.hasNext() && inclus == true) {							// Tant qu'il reste des attributs et inclus est VRAI
				String attribut = (String) iterator.next();
				if(((Ensemble)englobant).attributs.contains(attribut) == false){	//Si l'attribut itéré n'est pas contenu dans les attributs de englobant
					inclus = false;
					}
				}
			return inclus;
        }
	
	//Vérifie qu'un attribut appartient à l'ensemble appellant
	public boolean appartient(String obs){							
		boolean appartient = false;
		if(this.attributs.contains(obs)){
			appartient = true;
		}
		return appartient;
	}
	

	
//Getter
	public List<String> getAttributs() {
		return attributs;
	}

	public String toString(){
		String str = "Ensemble : {" + attributs + "}";
		return str;
	}
}
