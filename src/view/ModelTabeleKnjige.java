/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.table.AbstractTableModel;
import controller.Controller;
import java.util.List;
import model.Knjiga;

/**
 *
 * @author Administrator
 */
public class ModelTabeleKnjige extends AbstractTableModel{
    //private List<Knjiga> listaKnjiga = Controller.getInstance().getListaKnjiga();; mada svkk msm da ce se prosledi lista preko konstruktora
    private List<Knjiga> listaKnjiga;
    private String[] kolone = new String[]{"Naslov", "Autor", "ISBN", "Godina izdanja", "Zanr"};
    
    public ModelTabeleKnjige(List<Knjiga> listaKnjiga){
        this.listaKnjiga = listaKnjiga;
    }
    
    @Override
    public int getRowCount() {
        return listaKnjiga.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Knjiga knjiga = listaKnjiga.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return knjiga.getNaslov();
            case 1:
                return knjiga.getAutor().getIme() + " " + knjiga.getAutor().getPrezime();
            case 2:
                return knjiga.getISBN();
            case 3:
                return knjiga.getGodinaIzdanja();
            case 4:
                return knjiga.getZanr();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

//    void osveziPodatke() {
//        fireTableDataChanged();
//    }
//    

    public List<Knjiga> getListaKnjiga() {
        return listaKnjiga;
    }
    
    
}
