/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test.br.com.devmedia.mycompleteswingapp.entity;

/**
 *
 * @author VAGNER
 */
public class Customers implements java.io.Serializable {

    private Integer id;
    private String name;
    private int age;
    private String addrress;
    private String telephone;

    public Customers() {
    }

    public Customers(Integer id, String name, int age, String addrress, String telephone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.addrress = addrress;
        this.telephone = telephone;
    }

    public Customers(Integer id) {
        this.id = id;
    }


    public String getAddrress() {
        return addrress;
    }

    public void setAddrress(String addrress) {
        this.addrress = addrress;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customers other = (Customers) obj;
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
