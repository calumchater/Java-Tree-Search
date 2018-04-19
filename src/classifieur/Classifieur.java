package classifieur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import parser.*; 
@SuppressWarnings("unused")
public class Classifieur {

//Variables Globales
		private Categorie cateTypo;
		private HashMap<String,Categorie> categs = new HashMap<String,Categorie>();
		List<String> caracTypo = new ArrayList<String>();

//Constructeur
		public Classifieur(){
		}
		
		public Classifieur(Categorie catTyp) {
        	cateTypo = catTyp;
        }

//Fonctions
        //Classe une observation dans une typologie en ajoutant les catégories dans sa liste catEnglobe
		public void classer(Observation obs, Categorie treeTop){
            Iterator<Categorie> itFilles = treeTop.getFilles().iterator();  						// Iteration a travers les filles de treeTop
			while(itFilles.hasNext()){																// Tant Qu'il existe des filles de treeTop
				Categorie fille = (Categorie)itFilles.next();
				if(obs.seClasseSous(fille) == true && obs.getCatEnglobe().contains(fille) != true){	// Si l'observation appartient à la categorie et qu'elle n'eest pas déjà dans la liste des catégories qui englobent l'observation
					obs.ajouterCatEnglobe(fille); 													// Ajouter la categorie à la liste
					classer(obs, fille); 															// Lancer recurssivement la methode classer sur la fille iteree
				}
			}
        }		
		
        //Collecte les intitules des caracteristiques d'une typologie
        public void collecteCaracTypo(Categorie typo) {
        	
        	Iterator<Categorie> itFilles = typo.getFilles().iterator();
        	while(itFilles.hasNext()) {
        		Categorie fille = (Categorie)itFilles.next();
        		Iterator<Map.Entry<String,Domaine>> itCaracFille = fille.getCarac().entrySet().iterator();
        		while(itCaracFille.hasNext()){
        			Map.Entry<String,Domaine> caracFille = (Map.Entry<String,Domaine>)itCaracFille.next();
        			if(this.caracTypo.contains(caracFille.getKey()) != true) {
        				this.caracTypo.add(caracFille.getKey());
        			}
        		}
        		collecteCaracTypo(fille);
        	}
        }
        
        //Affiche de maniere indentée l'arborescence a partir d'une catégorie donnée
        public void affichageIndente(Categorie cat) {
        	cat.toString();
        	System.out.print("\n");
        	System.out.print("\t");
        	for(Categorie c : cat.getFilles()){
        		affichageIndente(c);
        	}
        }
        
        public void loadXML(String fileName) throws SAXException, IOException{
        	// Le parseur SAX
            XMLReader reader = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");

            // Creation d'un flot XML sur le fichier d'entree
            InputSource input = new InputSource(new FileInputStream(fileName + ".xml"));

            // Creation et connexion du ContentHandler specifique
            Handler monHandler = new Handler();
            reader.setContentHandler(monHandler);
            // Lancement du traitement...
            reader.parse(input);
            
            this.cateTypo = monHandler.classif.cateTypo;
        }
        
        public void traverseFilles(Categorie cat,BufferedWriter bw) throws IOException {
            for (Categorie c : cat.getFilles()) {    	
            	bw.newLine();
            	bw.write(cat.getNomCat() +"-> " + c.getNomCat());
            	traverseFilles(c, bw);
            }
        }
        
        public void show(String fileName, String format) throws InterruptedException, IOException {
       
        	String srcFile = fileName + ".gv", destFile = fileName + format;
        	BufferedWriter bw = null;
        	FileWriter fw = null;
        	fw = new FileWriter(srcFile);
        	bw = new BufferedWriter(fw);
        	bw.write("digraph G {");
        	// traverseFilles remplit notre writer avec les noms des categories et leurs filles
        	traverseFilles(cateTypo, bw);
        	bw.newLine();
        	bw.write("}");
        	bw.close();
        	fw.close();
        	String commande = "dot -Tpng -o " + destFile + " " + srcFile ; 
        	Runtime.getRuntime().exec(commande).waitFor();
        	
        }
        
        public void showClassification(Observation obs, String fileName, String format) throws InterruptedException, IOException {
            
        	String srcFile = fileName + ".gv", destFile = fileName + format;
        	BufferedWriter bw = null;
        	FileWriter fw = null;
        	fw = new FileWriter(srcFile);
        	bw = new BufferedWriter(fw);
        	bw.write("digraph G {");
        	bw.newLine();
        	bw.write("node [color = green]; ");
        	for(Categorie c : obs.getCatEnglobe()) {
        		bw.write(c.getNomCat() + " ");
        	}
        	
        	System.out.print("Categories trouvees: " + obs.getCatEnglobe());
        	bw.write(";"); bw.newLine(); bw.write("node [color = black];");
        	// traverseFilles remplit notre writer avec les noms des categories et leurs filles
        	traverseFilles(cateTypo, bw);
        	bw.newLine();
        	bw.write("}");
        	bw.close();
        	fw.close();
        	String commande = "dot -Tpng -o " + destFile + " " + srcFile ; 
        	Runtime.getRuntime().exec(commande).waitFor();
        }
        
// Getters et setters
     		public Categorie getCateTypo() {
     			return cateTypo;
     		}

     		public void setCateTypo(Categorie cateTypo) {
     			this.cateTypo = cateTypo;
     		}

     		public HashMap<String, Categorie> getCategs() {
     			return categs;
     		}

     		public void setCategs(HashMap<String, Categorie> categs) {
     			this.categs = categs;
     		}

     		public List<String> getCaracTypo() {
     			return caracTypo;
     		}

     		public void setCaracTypo(List<String> caracTypo) {
     			this.caracTypo = caracTypo;
     		}        
        
}

        
        
        
        
        