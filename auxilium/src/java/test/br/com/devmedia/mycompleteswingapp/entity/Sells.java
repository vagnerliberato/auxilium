/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test.br.com.devmedia.mycompleteswingapp.entity;

import java.util.Date;


public class Sells implements java.io.Serializable{

    private Integer id;
    private User vendor;
    private Date dateofsale;
    private float total;

    public Sells() {
    }

    public Sells(Integer id) {
        this.id = id;
    }

    public Sells(Integer id, User vendor, Date dateofsale, float total) {
        this.id = id;
        this.vendor = vendor;
        this.dateofsale = dateofsale;
        this.total = total;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sells other = (Sells) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
}
