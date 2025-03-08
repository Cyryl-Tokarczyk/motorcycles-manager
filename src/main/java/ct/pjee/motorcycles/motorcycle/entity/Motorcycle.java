package ct.pjee.motorcycles.motorcycle.entity;

import ct.pjee.motorcycles.entity.VersionAndCreationDateAuditable;
import ct.pjee.motorcycles.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "motorcycles")
public class Motorcycle extends VersionAndCreationDateAuditable implements Serializable {

    @Id
    UUID id;

    String name;

    @ManyToOne
    @JoinColumn(name = "brand")
    Brand brand;

    int displacement;

    @ManyToOne
    @JoinColumn(name = "user_name")
    User user;

}
