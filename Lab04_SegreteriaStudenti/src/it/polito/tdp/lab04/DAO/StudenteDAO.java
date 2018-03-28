package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	/**
	 * Ottengo la lista di tutti gli studenti
	 * @return ArrayList di studenti
	 */
	public List<Studente> listaStudenti(){
		
		final String sql = "SELECT * FROM studente";
		
		List<Studente> studenti = new ArrayList<Studente>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String nome = rs.getString("nome");
				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String cds = rs.getString("cds");

				System.out.println(matricola + " " + cognome + " " + nome + " " + cds);

				// Crea un nuovo JAVA Bean Studente
				// Aggiungi il nuovo oggetto Studente alla lista studenti
				
				Studente s = new Studente();
				s.setCds(cds);
				s.setCognome(cognome);
				s.setNome(nome);
				s.setMatricola(matricola);
				
				studenti.add(s);
			}

			return studenti;
			
		} catch(SQLException sqle) {
			
		}
		return null;
	}
	
	public Studente getStudenteByMatricola(int m) {
		
		final String sql = "SELECT * FROM studente";
		
		List<Studente> studenti = new ArrayList<Studente>();
		Studente s = new Studente();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String nome = rs.getString("nome");
				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String cds = rs.getString("cds");

				// Crea un nuovo JAVA Bean Studente
				// Aggiungi il nuovo oggetto Studente alla lista studenti
				
				Studente stemp = new Studente();
				stemp.setCds(cds);
				stemp.setCognome(cognome);
				stemp.setNome(nome);
				stemp.setMatricola(matricola);
				
				if(stemp.getMatricola()==m)
					return stemp;				
				studenti.add(s);
			}
			
		} catch(SQLException sqle) {
			
		}
		return null;
	}
	 
	public List<Studente> getStudentiIscrittiAlCorso(String codins){
		
		final String sql = "SELECT matricola , nome, cognome FROM studente WHERE matricola IN(SELECT matricola FROM iscrizione WHERE codins = ?)";
		
		List<Studente> studenti = new LinkedList<Studente>(); 
		
		try {
			Connection conn = ConnectDB.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codins);
			
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				Studente stud = new Studente();
				stud.setCds(res.getString("CDS"));
				stud.setCognome(res.getString("cognome"));
				stud.setNome(res.getString("nome"));
				stud.setMatricola(res.getInt("matricola"));
				studenti.add(stud);
			}
			
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
		
		return studenti;
		
	}
}
