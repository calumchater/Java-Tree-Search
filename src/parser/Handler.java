package parser;
import classifieur.*;

import java.util.*;
import org.xml.sax.*;

/**
 *
 * @author carre
 */
public class Handler implements ContentHandler {

    private String elementType;
    private Set<String> categories = new TreeSet<String>();
    private String categorieCourante;
    private String caracteristiqueCourante;
    private String mere;
    private double inf;
    private double sup;
    public Classifieur classif;
    
    // Do these need to be local or global ?
    Categorie catActuelle;
    Categorie catMere;
    Domaine dom;
    List<String> atts = new ArrayList<String>();

    public Handler() {
    }

    public Set<String> getCategories() {
        return categories;
    }

    // trace du document
    public void startDocument() throws SAXException {
        System.out.println("Start document...\n");
    }

    public void endDocument() throws SAXException {
        System.out.println("... document charge.");
    }
    // trace de certains elements
    public void startElement(String namespaceURI, String localName, String rawName, Attributes atts) throws SAXException {
        elementType = localName;     
        switch(elementType){
    	case "categorie": 
            System.out.println("New categorie:");
    		break;
        case "caracteristique":
            System.out.print("\tnew caracteristique: ");
        	break;
        case "ensemble":
            System.out.print("\tnew Ensemble: ");
        	break;
        case "intervalle":
            System.out.print("\tnew Intervalle: ");
        	break;
        }
  
    }

    public void endElement(String namespaceURI, String localName, String rawName) throws SAXException {
    	// this is the end of the elements. this means that at the closing brackets
    	// we will have all the necessary info to create our database:
    	switch(localName){
    	
    	case "categorie": 
    		// a bit shite, can make it more efficient. need to get rid of one of classifieur's arguments
    		
    		// list of strings vvv
    		categories.add(categorieCourante);
    		
    		// si mere = top, on cree le classifieur qui sera utilisé
            if(mere.equals("TOP")){
            	// creation of the top of the tree
            	classif = new Classifieur(catActuelle);
            	classif.getCategs().put(categorieCourante, catActuelle);
            }
            else {
            	
            	classif.getCategs().put(categorieCourante, catActuelle);
                // I need to access the category which has the nomCat = mere, in order to access
                // its filles and then add it in that list.
                
            	// catMere = classif.categs.get(classif.categs.indexOf(mere));
                catMere = classif.getCategs().get(mere);
            	try {
            		catMere.ajouterA(catActuelle);
            	} catch (DomaineIncompatibleException e) {
            		// TODO Auto-generated catch block
            		e.printStackTrace();
            	}
            }
            System.out.println("end categorie.\n");
    		break;
        case "caracteristique":
        	// need to add domaine to this map entry to categorie.carac
        	catActuelle.getCarac().put(caracteristiqueCourante, dom);
        	System.out.println("\tend caracteristique: " + this.caracteristiqueCourante + ".");
        	atts.clear();

        	break;
        case "ensemble":
        	// need to add this to domaine, therefore cast as ensemble
        	dom = new Ensemble(atts);
        	break;
        case "intervalle":
        	// need to add this to domaine, therefore cast as intervalle
        	dom = new Intervalle(inf,sup);
            System.out.println("\t\tintervalle: [" + inf + ", " + sup + "]");
        	break;
        }
        elementType = null; // fin traitement de contenu
    }

    //contenu characteres de l'element courant

    public void characters(char[] ch, int start, int length) throws SAXException {
        if (elementType != null) {
            if ((elementType.equals("docbase")) || (elementType.equals("categorie")) || (elementType.equals("caracteristique")) || (elementType.equals("intervalle")) || (elementType.equals("ensemble"))) {
                // pas de contenu
            } else {
                String contenu = new String(ch, start, length);
                switch(elementType){
                case "nom": 
                	System.out.println("nom: " + contenu);
                    categorieCourante = contenu;
                	catActuelle = new Categorie(categorieCourante);
                    categories.add(contenu);
                    break;
                case "mere":
                	mere = contenu;
	                System.out.println("categorie mere: " + contenu);
                	break;
                case "intitule": 
                	System.out.println("" + contenu);
                    caracteristiqueCourante = contenu;
                    break;
                case "element":
                	atts.add(contenu);
                	System.out.println(atts.size());
                    break;
                case "inf":
                    inf = Double.parseDouble(contenu);
                    break;
                case "sup":
                    sup = Double.parseDouble(contenu);
                	break;
                }
            }
        }
    }


// NOP
    public void startPrefixMapping(String prefix, String uri) {
    }

    public void endPrefixMapping(String prefix) {
    }

    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
    }

    public void processingInstruction(String target, String data) throws SAXException {
    }

    public void skippedEntity(String name) throws SAXException {
    }

    public void setDocumentLocator(Locator arg0) {
    }
}