package shopapp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "dbo", catalog = "shop")
public class UsersEntity {
    private int id;
    private String name;
    private String password;
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
    @Column(name = "password", nullable = false, length = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password);
    }

    @JsonManagedReference(value = "user-basket")
    @OneToMany(mappedBy = "usersByUsersId", targetEntity = BasketsEntity.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Collection<BasketsEntity> getBasketsById() {
        return basketsById;
    }

    public void setBasketsById(Collection<BasketsEntity> basketsById) {
        this.basketsById = basketsById;
    }
}
