package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo l'elenco di tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				
				Corso c = new Corso();
				c.setCodins(codins);
				c.setNumeroCrediti(numeroCrediti);
				c.setNome(nome);
				c.setPeriodoDidattico(periodoDidattico);
				
				corsi.add(c);
			}

			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		// TODO
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public void getStudentiIscrittiAlCorso(Corso corso) {
		// TODO
	}
	
	

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}
	

	/**
	 * Ottengo il codice insegnamento di un corso
	 * @param String corso
	 * @return String codins
	 */
	public String getCodinsByCorso(String corso) {
		final String sql = "SELECT * FROM corso";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				String nome = rs.getString("nome");
				
				if(nome.equals(corso))
					return codins;
				
			}

			return null;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	/**
	 * Ottengo l'elenco dei corsi frequentati da uno studente
	 * @param matricola
	 * @return Lista dei corsi
	 */
	public List<Corso> elencoCorsiFrequentatiDa(int matricola) {
		// TODO Auto-generated method stub
		final String sql = "SELECT * FROM corso as c WHERE codins IN(SELECT codins FROM iscrizione as i WHERE c.codins = i.codins AND i.matricola = ?)";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, matricola);

			ResultSet rs = st.executeQuery();
			List<Corso> ltemp = new LinkedList<Corso>();
			
			while (rs.next()) {

				String codins = rs.getString("codins");
				int crediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int pd = rs.getInt("pd");	
				Corso c = new Corso();
				c.setCodins(codins);
				c.setNumeroCrediti(crediti);
				c.setNome(nome);
				c.setPeriodoDidattico(pd);
				ltemp.add(c);
			}
		
			return ltemp;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	public boolean iscrittoONo(int matricola, String codins) {
		final String sql = "SELECT s.matricola, s.nome, cognome, c.codins FROM corso AS c, studente AS s, iscrizione AS i\n WHERE s.matricola = i.matricola AND c.codins = i.codins AND i.matricola = ? AND i.codins = ?";
	
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			st.setString(2, codins);

			ResultSet rs = st.executeQuery();

			if(rs.getString("matricola")==null)
				return false;
			return true;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
}
