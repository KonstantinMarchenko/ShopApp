package shopapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.Objects;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@Entity
@Table(name = "baskets", schema = "dbo", catalog = "shop")
public class BasketsEntity {
    private int id;
    private int usersId;
    private int productsId;
    private UsersEntity usersByUsersId;
    private ProductsEntity productsByProductsId;

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
    @Column(name = "users_id", nullable = false, insertable = false, updatable = false)
    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    @Basic
    @Column(name = "products_id", nullable = false, insertable = false, updatable = false)
    public int getProductsId() {
        return productsId;
    }

    public void setProductsId(int productsId) {
        this.productsId = productsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketsEntity that = (BasketsEntity) o;
        return id == that.id &&
                usersId == that.usersId &&
                productsId == that.productsId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usersId, productsId);
    }

    @JsonBackReference(value = "user-basket")
    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    public UsersEntity getUsersByUsersId() {
        return usersByUsersId;
    }

    public void setUsersByUsersId(UsersEntity usersByUsersId) {
        this.usersByUsersId = usersByUsersId;
    }

    @JsonBackReference(value = "product-basket")
    @ManyToOne
    @JoinColumn(name = "products_id", nullable = false)
    public ProductsEntity getProductsByProductsId() {
        return productsByProductsId;
    }

    public void setProductsByProductsId(ProductsEntity productsByProductsId) {
        this.productsByProductsId = productsByProductsId;
    }
}
