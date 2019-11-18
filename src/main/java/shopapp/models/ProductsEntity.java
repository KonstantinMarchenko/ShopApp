package shopapp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "products", schema = "dbo", catalog = "shop")
public class ProductsEntity {
    private int id;
    private String name;
    private int price;
    private Collection<BasketsEntity> basketsById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price", nullable = false)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductsEntity that = (ProductsEntity) o;
        return id == that.id &&
                price == that.price &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    @JsonManagedReference(value = "product-basket")
    @OneToMany(mappedBy = "productsByProductsId", targetEntity = BasketsEntity.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Collection<BasketsEntity> getBasketsById() {
        return basketsById;
    }

    public void setBasketsById(Collection<BasketsEntity> basketsById) {
        this.basketsById = basketsById;
    }
}
