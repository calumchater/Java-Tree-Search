package classifieur;

import java.util.*;


public class Categorie {

	private String nomCat;
	private HashMap<String, Domaine> carac = new HashMap<String, Domaine>();
	private List<Categorie> filles = new ArrayList<Categorie>();
	
	
//Constructeur
	public Categorie(String _nom) {
		nomCat = _nom;
	}
	
//Fonctions
	public boolean testEnglobe(Categorie catFille) throws DomaineIncompatibleException {	//Test si la catégorie appelant englobe la catégorie passée en paramètre

		boolean englobe = true;
		if (this.carac.size() > catFille.carac.size()) { 									// Si la catégorie mère possède plus de caractéristiques que la fille, englobe = false
			englobe = false;
		}

		Iterator<Map.Entry<String,Domaine>> iterator = (this.carac).entrySet().iterator(); 	// on itere a travers toutes les carac de la fille car elle en aura moins -> facteur limitant
		
		while (iterator.hasNext() && englobe == true) {										// Tant qu'il reste des caracteristiques et englobe est VRAI
			Map.Entry<String,Domaine> elt = (Map.Entry<String, Domaine>) iterator.next(); 	// Passage a la carac suivante
			if (catFille.carac.containsKey(elt.getKey()) == false) {						// Si la categorie appelant ne possède pas une des caractéristique de la fille, englobe = FAUX
				return false;
			}
			
			//Test inclusion
			if(catFille.carac.get(elt.getKey()).inclus(this.carac.get(elt.getKey())) == false){// Si la caractéristique de catFille n'est pas inclus dans celle de la catégorie Appellant
				englobe = false;
			}
		}
		return englobe;
	}

	public void ajouterA(Categorie cat) throws DomaineIncompatibleException {
		if(this.testEnglobe(cat)){			//Si la catégorie courante englobe la catégorie en paramètre
			this.filles.add(cat);			//On l'ajoute aux filles de la catégorie courante
		}
	}

	public void addCarac(String nom, Domaine dom){
		this.carac.put(nom, dom);
	}
	
	
//Getter
	public String getNomCat() {
		return nomCat;
	}

	public HashMap<String, Domaine> getCarac() {
		return carac;
	}

	public List<Categorie> getFilles() {
		return filles;
	}
//Affichage	
	public String toString(){
		String str = "CATEGORIE : " + this.getNomCat();
		Iterator<Map.Entry<String,Domaine>> iterator = (this.carac).entrySet().iterator();
		
		while (iterator.hasNext()){
			Map.Entry<String, Domaine> elt = (Map.Entry<String, Domaine>) iterator.next();
			str += "\n- " + elt.getKey() + " : " + elt.getValue().toString();
		}
		return str;
	}
	
	

}
