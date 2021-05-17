/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portal.dto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Bojan
 */
@Entity
@Table(name = "Vest")
public class Story implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String naslov;
    private String kratakOpis;
    private String tekst;
    private String url;
    @OneToMany(fetch=FetchType.EAGER, mappedBy="vest", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Comment> komentari;

    public Story() {
        
    }

    public List<Comment> getKomentari() {
        return komentari;
    }

    public void setKomentari(List<Comment> komentari) {
        this.komentari = komentari;
    }
    
    public Story(int id, String naslov, String kratakOpis, String tekst) {
        this.id = id;
        this.naslov = naslov;
        this.kratakOpis = kratakOpis;
        this.tekst = tekst;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getKratakOpis() {
        return kratakOpis;
    }

    public void setKratakOpis(String kratakOpis) {
        this.kratakOpis = kratakOpis;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "New{" + "id=" + id + ", naslov=" + naslov + ", kratakOpis=" + kratakOpis + ", tekst=" + tekst + ", url=" + url + ", komentari=" + komentari + '}';
    }
    
    
    
}
