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
		return studenteDAO.getStudentiIscrittiAlCorso(codins);
	}
}
