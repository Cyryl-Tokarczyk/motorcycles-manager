package ct.pjee.motorcycles.user.entity;

import ct.pjee.motorcycles.motorcycle.entity.Motorcycle;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    UUID id;

    String username;

    @ToString.Exclude
    String password;

    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    List<String> roles;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    List<Motorcycle> motorcycles;

}
