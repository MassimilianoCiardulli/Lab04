/**
 * Sample Skeleton for 'SegreteriaStudenti.fxml' Controller Class
 */

package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="elencoCorsi"
    private ComboBox<String> elencoCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader
    
    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader
    
    @FXML
    private TextArea txtArea; 
    
    private CorsoDAO corsoDAO = new CorsoDAO();

    private Model model ;
    

    @FXML
    void handleCercaCorsi(ActionEvent event) {
    	int matricola = 0;
    	
    	try {
    		matricola = Integer.parseInt(txtMatricola.getText());
    	}catch(Exception e) {
    		txtArea.setText("Errore: la matricola deve essere un codice identificativo numerico.");
    		return ;
    	}
    	
    	if(model.cercaStudenteByMatricola(matricola)) {
    		
    		List<Corso> ltemp = new LinkedList<Corso>(model.corsiFrequentatiDa(matricola));
    		
    		if(ltemp.isEmpty()){
    			txtArea.setText("Lo studente non è iscritto a nessun corso.");
    			return ;
    		}
    		
    		txtArea.setText("");
    		
    		for(Corso c:ltemp) {
    			txtArea.appendText(c.getCodins() + "\t\t\t" + c.getNumeroCrediti() + "\t\t\t" + c.getNome() + "\t\t\t" + c.getPeriodoDidattico() + "\n");
    		}
    		
    		
    	}
    	else {
    		txtArea.setText("Errore: lo studente non esiste.");
    	}
    }
    
    @FXML
    void handleCercaIscritti(ActionEvent event) {
    	
    	if(elencoCorsi.getValue()==null) //Se non ho selezionato un corso
    		this.txtArea.setText("Errore: seleziona un corso.");
    	
    	else {
    		String corso = elencoCorsi.getValue();
    		
    		try {
    			String matricola = txtMatricola.getText();
    			int m = Integer.parseInt(matricola);
    			
    			if(matricola!=null) {
    				String codins = model.getCodIns(corso);
    				boolean iscritto = model.iscrittoONo(codins, m);
    				if(iscritto) {
    					txtArea.setText("Studente già iscritto a questo corso.");   					
    				}
    				else {
    					txtArea.setText("Studente non iscritto a questo corso");
    				}
    			}
   
    			return ;
    			
    		}catch(NullPointerException npe) {
    			
    		}catch(NumberFormatException nfe) {
    			txtArea.setText("Errore: la matricola deve essere un codice identificativo numerico.");
    			return ;
    		}
    			String codins = model.getCodIns(corso);
        		
        		List<Studente> ltemp = new LinkedList<Studente>(model.getStudentiByCodins(codins)); 
        		
        		if(ltemp.isEmpty()) {
        			txtArea.setText("Non ci sono studenti iscritti al corso.");
        			return ;
        		}
        		
        		txtArea.setText("");
        		    
        		for(Studente s:ltemp) {
        		
        			txtArea.appendText(s.getMatricola() + "\t\t\t"+ s.getNome() + "\t\t\t"+ s.getCognome() +"\t\t\t" +s.getCds() + "\n"); //come allineare i dati?
        		}
    	}	
    }

    @FXML
    void handleIscrivi(ActionEvent event) {
    	
    }

    @FXML
    void handleReset(ActionEvent event) {
    	txtArea.clear();
    	this.txtCognome.clear();
    	this.txtMatricola.clear();
    	this.txtNome.clear();
    }
    
    @FXML
    void handleVerde(ActionEvent event) {
    	int matricola = 0;
    	try {
    		matricola = Integer.parseInt(txtMatricola.getText());
    		Studente s = model.getNomeCognomeFromMatricola(matricola);
    		if(s!=null) {
    			txtNome.setText(s.getNome());
    			txtCognome.setText(s.getCognome());
    		}
    	}catch(Exception e) {
    		txtArea.setText("Errore: la matricola deve essere un codice identificativo numerico.");
    		txtNome.setText("");
    		txtCognome.setText("");
    		return ;
    	}
    	
    }
    
    @FXML
    void handleElenco(ActionEvent event) {

    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert elencoCorsi != null : "fx:id=\"elencoCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";        
    }

	public void setModel(Model model) {
		// TODO Auto-generated method stub
		this.model = model;
		for(Corso c: corsoDAO.getTuttiICorsi()) {
        	elencoCorsi.getItems().add(c.getNome());
        }
	}
}
