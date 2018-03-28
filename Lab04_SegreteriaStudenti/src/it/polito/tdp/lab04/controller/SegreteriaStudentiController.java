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
import javafx.collections.FXCollections;
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

    @FXML
    void handleCercaCorsi(ActionEvent event) {

    }
    
    CorsoDAO corsoDAO = new CorsoDAO();

    Model model ;
    
    @FXML
    void handleCercaIscritti(ActionEvent event) {
    	if(elencoCorsi.getValue()==null)
    		this.txtArea.setText("Errore: seleziona un corso.");
    	else {
    		String corso = elencoCorsi.getValue();
    		String codins = model.getCodIns(corso);
    		
    		if(model.getStudentiByCodins(codins).isEmpty()) {
    			txtArea.setText("Non ci sono studenti iscritti al corso.");
    			return ;
    		}
    		
    		List<Studente> ltemp = new LinkedList<Studente>(model.getStudentiByCodins(codins));
    		for(Studente s:ltemp) {
    			txtArea.appendText(s.toString() + "\n");
    		}
    		
    	}
    }

    @FXML
    void handleElenco(ActionEvent event) {

    }

    @FXML
    void handleIscrivi(ActionEvent event) {

    }

    @FXML
    void handleReset(ActionEvent event) {

    }
    
    @FXML
    void handleVerde(ActionEvent event) {
    	int matricola = Integer.parseInt(txtMatricola.getText());
    	Studente s = model.getNomeCognomeFromMatricola(matricola);
    	if(s!=null) {
    		txtNome.setText(s.getNome());
    		txtCognome.setText(s.getCognome());
    	}
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
