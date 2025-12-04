/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import java.util.ArrayList;
import java.util.List;
import model.Knjiga;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Autor;
import model.Zanr;

/**
 *
 * @author Administrator
 */
public class DBBroker {

    public List<Knjiga> ucitajListuKnjigaIzBaze() {
        
        List<Knjiga> lista = new ArrayList<>();
        
        try {
            String upit = "SELECT * FROM knjiga k JOIN autor a ON a.id = k.autorID";
            Statement statement = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery(upit);
            while(rs.next()){
                int id = rs.getInt("k.id");
                String naslov = rs.getString("k.naslov");
                int godIzdanja = rs.getInt("k.godinaIzdanja");
                String ISBN = rs.getString("k.ISBN");
                Zanr zanr = Zanr.valueOf(rs.getString("k.zanr"));
                int autorId = rs.getInt("a.id");
                String ime = rs.getString("a.ime");
                String prezime = rs.getString("a.prezime");
                int godRodjenja = rs.getInt("a.godinaRodjenja");
                String biografija = rs.getString("a.biografija");
                
                Autor a = new Autor(autorId, ime, prezime, godRodjenja, biografija);
                
                Knjiga k = new Knjiga(id, naslov, a, ISBN, godIzdanja, zanr);
                lista.add(k);
            }
        } catch (SQLException ex) {
            System.out.println("Objekti nisu uspesno ucitani iz baze!");
            ex.printStackTrace();
        }
        
        return lista;
    }

    public List<Autor> ucitajListuAutoraIzBaze() {
        
        List<Autor> lista = new ArrayList<>();
         
        try {
           
            
            String upit = "SELECT * FROM autor";
            Statement statement = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery(upit);
            
            while(rs.next()){
                int id = rs.getInt("id");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                int godinaRodjenja = rs.getInt("godinaRodjenja");
                String biografija = rs.getString("biografija");
                 
                Autor a = new Autor(id, ime, prezime, godinaRodjenja, biografija);
                
                lista.add(a);
            }
            
        } catch (SQLException ex) {
            System.out.println("Objekti nisu uspesno ucitani iz baze!");
            ex.printStackTrace();        
        }
        
        return lista;
    }

    public void obrisiKnjigu(int id) {
        try {
            String upit = "DELETE FROM knjiga WHERE id =  ?";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setInt(1, id);
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();
        } catch (SQLException ex) {
            System.out.println("Nije uspesno brisanje u bazi!");
            ex.printStackTrace();
        }
    }

    public void dodajKnjiguUBazu(Knjiga novaKnjiga) {
        try{
            String upit = "INSERT INTO knjiga VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setInt(1, novaKnjiga.getId());       
            ps.setString(2, novaKnjiga.getNaslov());
            ps.setInt(3, novaKnjiga.getAutor().getId());
            ps.setInt(4, novaKnjiga.getGodinaIzdanja());
            ps.setString(5, novaKnjiga.getISBN());
            ps.setString(6, String.valueOf(novaKnjiga.getZanr()));
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();
        }catch (SQLException ex) {
            System.out.println("Nije uspesno dodavanje u bazu!");
            ex.printStackTrace();
        }
    }

    public void azurirajKnjiguUbazi(Knjiga knjigaZaIzmenu) {
        try{
            String upit = "UPDATE knjiga SET naslov = ?, autorId = ?, godinaIzdanja = ?, zanr = ? where id = ?";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, knjigaZaIzmenu.getNaslov());
            ps.setInt(2, knjigaZaIzmenu.getAutor().getId());
            ps.setInt(3, knjigaZaIzmenu.getGodinaIzdanja());
            ps.setString(4, String.valueOf(knjigaZaIzmenu.getZanr()));
            ps.setInt(5, knjigaZaIzmenu.getId());
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();
        }catch (SQLException ex) {
            System.out.println("Nije uspesno azurirano u bazi!");
            ex.printStackTrace();
        }
    }
    
}
