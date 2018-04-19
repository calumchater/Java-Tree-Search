package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import classifieur.*;

@SuppressWarnings("unused")
public class ClassifieurTest {
	
	Classifieur classifieur;
	
	Observation obs1;
	Observation obs2;
	
	Categorie c1;
	Categorie c2;
	Categorie c3;
	Categorie c4;
	Categorie c5;
	
	
	//Caracteristiques de arbre
	
	Domaine tailleArbre = new Intervalle(5.0, 50.0);
	
	List<String> ensformeArbre = Arrays.asList("arrondi", "irregulier","conique");
	Domaine formeArbre = new Ensemble(ensformeArbre);
	
	Domaine tailleDuTroncArbre = new Intervalle(0.2, 3.0);
	
	List<String> ensEcorceArbre = Arrays.asList("ecailles", "fissuree", "lisse", "plaques");
	Domaine ecorceArbre = new Ensemble(ensEcorceArbre);
	
	
	//Caracteristiques de conifere
	Domaine tailleConifere = new Intervalle(5.0, 40.0);
	
	List<String> ensformeConifere = Arrays.asList("conique");
	Domaine formeConifere = new Ensemble(ensformeConifere);
	
	Domaine tailleDuTroncConifere = new Intervalle(0.5, 2.0);
	
	List<String> ensEcorceConifere = Arrays.asList("ecailles", "fissuree", "lisse");
	Domaine ecorceConifere = new Ensemble(ensEcorceConifere);
	
	
	//Caracteristiques de feuillu
	Domaine tailleFeuillu = new Intervalle(5.0, 50.0);
	
	List<String> ensformeFeuillu = Arrays.asList("conique", "arrondi", "irregulier");
	Domaine formeFeuillu = new Ensemble(ensformeFeuillu);
	
	Domaine tailleDuTroncFeuillu = new Intervalle(0.5, 2.0);
	
	List<String> ensEcorceFeuillu = Arrays.asList("ecailles", "fissuree", "lisse", "plaques");
	Domaine ecorceFeuillu = new Ensemble(ensEcorceFeuillu);	
	
	List<String> ensFeuillesFeuillu = Arrays.asList("dentelee", "lobe", "ovale");
	Domaine feuillesFeuillu = new Ensemble(ensFeuillesFeuillu);
	
	
	//Caracteristiques d'epicea
	Domaine tailleEpicea = new Intervalle(20.0, 30.0);
	
	List<String> ensformeEpicea = Arrays.asList("conique");
	Domaine formeEpicea = new Ensemble(ensformeEpicea);
	
	Domaine tailleDuTroncEpicea = new Intervalle(0.5, 1.5);
	
	List<String> ensEcorceEpicea = Arrays.asList("ecailles");
	Domaine ecorceEpicea = new Ensemble(ensEcorceEpicea);	
	
	List<String> ensAiguillesEpicea = Arrays.asList("brosse", "peigne");
	Domaine aiguillesEpicea = new Ensemble(ensAiguillesEpicea);
	
	
	//Caracteristiques de maison
	List<String> ensTailleMaison = Arrays.asList("petite", "moyenne", "grande");
	Domaine tailleMaison = new Ensemble(ensTailleMaison);
	
	List<String> ensStyleMaison = Arrays.asList("chalet", "villa", "ferme", "mas");
	Domaine styleMaison = new Ensemble(ensStyleMaison);
	
	
	
	@Test
	public void testClasser() {

		c1 = new Categorie("arbre");
			c1.addCarac("taille", tailleArbre);
			c1.addCarac("forme", formeArbre);
			c1.addCarac("taille du tronc", tailleDuTroncArbre);
			c1.addCarac("ecorce", ecorceArbre);
		
		c2 = new Categorie("maison");
			c2.addCarac("taille", tailleMaison);
			c2.addCarac("style", styleMaison);
			
			
		c3 = new Categorie("epicea");
			c3.addCarac("taille", tailleEpicea);
			c3.addCarac("forme", formeEpicea);
			c3.addCarac("taille du tronc", tailleDuTroncEpicea);
			c3.addCarac("ecorce", ecorceEpicea);
			c3.addCarac("aiguilles", aiguillesEpicea);
			
			
		c4 = new Categorie("conifere");
			c4.addCarac("taille", tailleConifere);
			c4.addCarac("forme", formeConifere);
			c4.addCarac("taille du tronc", tailleDuTroncConifere);
			c4.addCarac("ecorce", ecorceConifere);
			
		c5 = new Categorie("feuillu");
			c5.addCarac("taille", tailleFeuillu);
			c5.addCarac("forme", formeFeuillu);
			c5.addCarac("taille du tronc", tailleDuTroncFeuillu);
			c5.addCarac("ecorce", ecorceFeuillu);
			c5.addCarac("feuilles", feuillesFeuillu);
		
		
		obs1 = new Observation("arbre");
			obs1.addCarac("taille", "25.0");
			obs1.addCarac("forme", "conique");
			obs1.addCarac("taille du tronc", "1.0");
			obs1.addCarac("ecorce", "ecailles");
			obs1.addCarac("aiguilles", "peigne");

//Ajout de Feuillu et Conifere a Arbre			
		try {
			c1.ajouterA(c4);
		} catch (DomaineIncompatibleException e) {
		}	
		
		try {
			c1.ajouterA(c5);
		} catch (DomaineIncompatibleException e) {
		}
		
//Ajout de Epicea a Conifere 
		try {
			c4.ajouterA(c3);
		} catch (DomaineIncompatibleException e) {
		}
		
		
		classifieur = new Classifieur(c1);
		
		classifieur.classer(obs1, c1);
		
		Assert.assertTrue(obs1.getCatEnglobe().contains(c3));
		
	
	}

}