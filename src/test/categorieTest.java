package test;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;


import classifieur.*;

@SuppressWarnings("unused")
public class categorieTest {
	Categorie c1;
	Categorie c2;
	Categorie c3;
	
	//Caracteristiques de conifere
	Domaine tailleConifere = new Intervalle(5.0, 40.0);
	
	List<String> ensformeConifere = Arrays.asList("conique");
	Domaine formeConifere = new Ensemble(ensformeConifere);
	
	Domaine tailleDuTroncConifere = new Intervalle(0.5, 2.0);
	
	List<String> ensEcorceConifere = Arrays.asList("ecailles", "fissuree", "lisse");
	Domaine ecorceConifere = new Ensemble(ensEcorceConifere);
	
	
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
	public void testCategEnglobe1() {
			
		c1 = new Categorie("conifere");
			c1.addCarac("taille", tailleConifere);
			c1.addCarac("forme", formeConifere);
			c1.addCarac("taille du tronc", tailleDuTroncConifere);
			c1.addCarac("ecorce", ecorceConifere);

		c2 = new Categorie("epicea");
			c2.addCarac("taille", tailleEpicea);
			c2.addCarac("forme", formeEpicea);
			c2.addCarac("taille du tronc", tailleDuTroncEpicea);
			c2.addCarac("ecorce", ecorceEpicea);
			c2.addCarac("aiguilles", aiguillesEpicea);
		
		
			
		try{
			Assert.assertTrue(c1.testEnglobe(c2));
		}
		catch (DomaineIncompatibleException e){
		}
		
	}
	
	@Test
	public void testCategEnglobe2() {
		c2 = new Categorie("epicea");
			c2.addCarac("taille", tailleEpicea);
			c2.addCarac("forme", formeEpicea);
			c2.addCarac("taille du tronc", tailleDuTroncEpicea);
			c2.addCarac("ecorce", ecorceEpicea);
			c2.addCarac("aiguilles", aiguillesEpicea);
		
		c3 = new Categorie("maison");
			c3.addCarac("taille", tailleMaison);
			c3.addCarac("style", styleMaison);
		
		c2.toString();
			
		try{
			Assert.assertFalse(c2.testEnglobe(c3));
		}
		catch (DomaineIncompatibleException e){
		}
	}
		
}