/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portal.dto;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Komentar")
public class Comment implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="IdVesti")
    private Story vest;
    private String username;
    private String komentar;
    private int prihvacen;

    public int getPrihvacen() {
        return prihvacen;
    }

    public void setPrihvacen(int prihvacen) {
        this.prihvacen = prihvacen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Story getVest() {
        return vest;
    }

    public void setVest(Story vest) {
        this.vest = vest;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", username=" + username + ", komentar=" + komentar + ", prihvacen=" + prihvacen + '}';
    }
    
    
}
