package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.*;

public class Model {
	private List<Corso> corsi ;
	private List<Studente> studenti ;
	private CorsoDAO corsoDAO ;
	private StudenteDAO studenteDAO ;
	
	public Model() {
		this.corsi = new ArrayList<Corso>();
		this.studenti = new ArrayList<Studente>();
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
		List<Studente> ltemp = new LinkedList<Studente>(studenteDAO.getStudentiIscrittiAlCorso(codins));

		return ltemp;
	}
	
	/**
	 * 
	 * @param matricola
	 * @return true se lo studente è presente, false altrimenti
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
}
