package classifieur;
import java.util.*;
import java.util.Map.Entry;


public class Observation {

    private String type;
    private HashMap<String,String> obs = new HashMap<String, String>();   // on passe toutes les valeurs en string pour simplifier
    private List<Categorie> catEnglobe = new ArrayList<Categorie>(); 

//Constructeur    
    public Observation(String _type, HashMap<String,String> _obs){
        type = _type;
        obs = _obs;
    }
    
    public Observation(String _type){
        type = _type;
    }
    
    public Observation() {}
    

    
//Fonctions
    //Teste si l'observation courante se classe sous une catégorie cat donnée
    public boolean seClasseSous(Categorie cat){
        boolean seClasse = true;
        Iterator<Entry<String, Domaine>> iterator = cat.getCarac().entrySet().iterator();     	// on itere a travers toutes les carac de l'observation car elle en aura moins -> facteur limitant
        while (iterator.hasNext() && seClasse == true) {										//Tant qu'il y a des caracteristiques
             Map.Entry<String, Domaine> elt = (Map.Entry<String, Domaine>) iterator.next();    
             if((this.obs).containsKey(elt.getKey()) != true || elt.getValue().appartient(this.obs.get(elt.getKey())) == false){  // on teste si le nom de la carac est pas présent et si il est pas inclus dedans.  																													//Il utilisera la fonction inclus correspondant au type de domaine
                seClasse = false;
             }
        }
        return seClasse;
    }

    //Ajoute une caractéristique à l'observation courante
    public void addCarac(String nom, String caracobs){
		this.obs.put(nom, caracobs);
	}
    
//Getter
	public String getType() {
		return type;
	}

	public List<Categorie> getCatEnglobe() {
		return catEnglobe;
	}
    
	public void ajouterCatEnglobe(Categorie cat){
		catEnglobe.add(cat);
	}
	
	public HashMap<String,String> getObs(){
		return obs;
	}
}
