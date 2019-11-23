package shopapp.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "Product", schema = "dbo", catalog = "SHOP")
public class ProductEntity {
    private int id;
    private String name;
    private float price;
    private int quantity;
    private Collection<BasketEntity> basketsById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Price", nullable = false, precision = 0)
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Basic
    @Column(name = "Quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return id == that.id &&
                Double.compare(that.price, price) == 0 &&
                quantity == that.quantity &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, quantity);
    }

    @OneToMany(mappedBy = "productByProductId", cascade = CascadeType.REMOVE)
    public Collection<BasketEntity> getBasketsById() {
        return basketsById;
    }

    public void setBasketsById(Collection<BasketEntity> basketsById) {
        this.basketsById = basketsById;
    }
}
