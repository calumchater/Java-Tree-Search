package classifieur;


public class Intervalle extends Domaine {						//constructeur
	private double inf;
	private double sup;

//Constructeur
    public Intervalle(double inf, double sup){
    	this.inf=inf;
    	this.sup=sup;
    }
                
//Fonctions
    //Vérifie que l'intervalle appellant est inclus dans englobant
	public boolean inclus(Domaine englobant) throws DomaineIncompatibleException { //Teste si l'intervalle courant est inclus dans englobant
			boolean inclus = false;
			if(((Intervalle)englobant).inf <= this.inf && ((Intervalle)englobant).sup >= this.sup){		//Caster ?
				inclus = true;
				}
			return inclus;
        }

	//Teste si obs appartient à l'intervalle appellant
	public boolean appartient(String obs) {   	
		boolean appartient = true;
		double comp = 0;
		try{
			comp = Double.parseDouble(obs);
		}    	   
		catch(NumberFormatException ex){			//Si l'obs n'est pas parsable
		}
		if(comp >= this.inf && comp <= this.sup){ 	//Si l'obs est appartient à l'intervalle appellant
                appartient = true;
            }
            else{ 									//Sinon
            	appartient = false;
            	}
		return appartient;
	}

//Getter
	public double getInf() {
		return inf;
	}


	public double getSup() {
		return sup;
	}
	
	public String toString(){
		String str = "Intervalle : [" + getInf() + ", " + getSup() + "]";
		return str;
	}
	
}