package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.*;

public class Model {
	private CorsoDAO corsoDAO ;
	private StudenteDAO studenteDAO ;
	
	public Model() {
		this.corsoDAO = new CorsoDAO();
		this.studenteDAO = new StudenteDAO();
	}
	public Studente getNomeCognomeFromMatricola(int matricola) {
		Studente s = studenteDAO.getStudenteByMatricola(matricola);
		
		return s;
	}
	
	public String getCodIns(String corso) {
		String codins = corsoDAO.getCodinsByCorso(corso);
		return codins;
	}
	
	public List<Studente> getStudentiByCodins(String codins) {
		return studenteDAO.getStudentiIscrittiAlCorso(codins);
	}
	
	/**
	 * 
	 * @param matricola
	 * @return true se lo studente è presente nel database, false altrimenti
	 */
	public boolean cercaStudenteByMatricola(int matricola) {
		if(studenteDAO.getStudenteByMatricola(matricola)==null) 
			return false;
		return true;
	}
	public List<Corso> corsiFrequentatiDa(int matricola) {
		// TODO Auto-generated method stub
		return corsoDAO.elencoCorsiFrequentatiDa(matricola);
	}
	public boolean iscrittoONo(String codins, int m) {
		// TODO Auto-generated method stub
		return corsoDAO.iscrittoONo(m, codins);
	}
}
