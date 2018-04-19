package test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Assert;

import classifieur.*;

import org.junit.Test;

@SuppressWarnings("unused")
public class domaineTest {
	Domaine d1;
	Domaine d2;
	Domaine d3;
	
	List<String> ens1 = Arrays.asList("ecailles", "fissuree", "lisse", "plaques");
	List<String> ens2 = Arrays.asList("ecailles", "fissuree", "lisse");
	List<String> ens3 = Arrays.asList("ecailles", "fissuree", "lisse", "rugueux");
/*Test Ensemble*/	
	@Test
	public void testEnsembleInclus() {
		d1 = new Ensemble(ens1);
		d2 = new Ensemble(ens2);
		try{
			Assert.assertTrue(d2.inclus(d1));
		}
		catch (DomaineIncompatibleException e){
		}
	}
	@Test
	public void testEnsembleInclus2() {
		d1 = new Ensemble(ens1);
		d3 = new Ensemble(ens3);
		try{
			Assert.assertFalse(d3.inclus(d1));
		}
		catch (DomaineIncompatibleException e){
		}
	}
	
	@Test
	public void testEnsembleAppartient() {
		d1 = new Ensemble(ens1);
		Assert.assertTrue(d1.appartient("plaques"));
	}
	@Test
	public void testEnsembleAppartient2() {
		d1 = new Ensemble(ens1);
		Assert.assertFalse(d1.appartient("rugueux"));
	}
	
/*Test Intervalle */	
	@Test
	public void testIntervalleInclus() {
		d1 = new Intervalle(15.0, 30.0);
		d2 = new Intervalle(10.0, 20.0);

		try{
			Assert.assertFalse(d1.inclus(d2));
		}
		catch (DomaineIncompatibleException e){
		}
	}
	@Test
	public void testIntervalleInclus2() {
		d1 = new Intervalle(15.0, 30.0);
		d3 = new Intervalle(5.0, 40.0);
		try{
			Assert.assertFalse(d3.inclus(d1));
		}
		catch (DomaineIncompatibleException e){
		}
	}
	@Test
	public void testIntervalleAppartient() {
		d1 = new Intervalle(15.0, 30.0);

		Assert.assertFalse(d1.appartient("10.0"));
	}
	@Test
	public void testIntervalleAppartient2() {
		d2 = new Intervalle(10.0, 20.0);

		Assert.assertTrue(d2.appartient("10.0"));
	}
}