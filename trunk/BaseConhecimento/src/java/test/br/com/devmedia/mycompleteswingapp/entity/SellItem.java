
package test.br.com.devmedia.mycompleteswingapp.entity;

public class SellItem implements java.io.Serializable {

    private Integer id;
    private Product product;
    private int qnt;
    private Sells sell;

    public SellItem() {
    }

    public SellItem(Integer id, Product product, int qnt, Sells sell) {
        this.id = id;
        this.product = product;
        this.qnt = qnt;
        this.sell = sell;
    }

    public SellItem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQnt() {
        return qnt;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
    }

    public Sells getSell() {
        return sell;
    }

    public void setSell(Sells sell) {
        this.sell = sell;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SellItem other = (SellItem) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
}
