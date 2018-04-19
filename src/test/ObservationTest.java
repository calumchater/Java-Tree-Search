package test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Assert;

import classifieur.*;

import org.junit.Test;

@SuppressWarnings("unused")

public class ObservationTest {
	
	Observation obs1;
	Observation obs2;
	
	Categorie c1;
	Categorie c2;
	Categorie c3;
	Categorie c4;
	
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
	public void testSeClasseSous1() {
		c1 = new Categorie("conifere");
			c1.addCarac("taille", tailleConifere);
			c1.addCarac("forme", formeConifere);
			c1.addCarac("taille du tronc", tailleDuTroncConifere);
			c1.addCarac("ecorce", ecorceConifere);
		
		obs1 = new Observation("arbre");
			obs1.addCarac("taille", "30.0");
			obs1.addCarac("forme", "conique");
			obs1.addCarac("taille du tronc", "1.0");
			obs1.addCarac("ecorce", "ecailles");
			obs1.addCarac("aiguilles", "peigne");
		
		//Doit etre vrai
		Assert.assertTrue(obs1.seClasseSous(c1));
	}
	
	@Test
	public void testSeClasseSous2() {
		c2 = new Categorie("feuillu");
			c2.addCarac("taille", tailleFeuillu);
			c2.addCarac("forme", formeFeuillu);
			c2.addCarac("taille du tronc", tailleDuTroncFeuillu);
			c2.addCarac("ecorce", ecorceFeuillu);
			c2.addCarac("feuilles", feuillesFeuillu);		
			
		obs1 = new Observation("arbre");
			obs1.addCarac("taille", "30.0");
			obs1.addCarac("forme", "conique");
			obs1.addCarac("taille du tronc", "1.0");
			obs1.addCarac("ecorce", "ecailles");
			obs1.addCarac("aiguilles", "peigne");
		
		//Doit etre faux
		Assert.assertFalse(obs1.seClasseSous(c2));
	}
	
	
	@Test
	public void testSeClasseSous3() {
		c3 = new Categorie("epicea");
			c3.addCarac("taille", tailleEpicea);
			c3.addCarac("forme", formeEpicea);
			c3.addCarac("taille du tronc", tailleDuTroncEpicea);
			c3.addCarac("ecorce", ecorceEpicea);
			c3.addCarac("aiguilles", aiguillesEpicea);
			
		obs1 = new Observation("arbre");
			obs1.addCarac("taille", "30.0");
			obs1.addCarac("forme", "conique");
			obs1.addCarac("taille du tronc", "1.0");
			obs1.addCarac("ecorce", "ecailles");
			obs1.addCarac("aiguilles", "peigne");
		
		//Doit etre vrai
		Assert.assertTrue(obs1.seClasseSous(c3));
	}
	
	
	
	@Test
	public void testSeClasseSous4() {
		
		c4 = new Categorie("maison");
			c4.addCarac("taille", tailleMaison);
			c4.addCarac("style", styleMaison);
		
		obs1 = new Observation("arbre");
			obs1.addCarac("taille", "30.0");
			obs1.addCarac("forme", "conique");
			obs1.addCarac("taille du tronc", "1.0");
			obs1.addCarac("ecorce", "ecailles");
			obs1.addCarac("aiguilles", "peigne");
		
		//Doit etre faux
		Assert.assertFalse(obs1.seClasseSous(c4));
	}
	

}