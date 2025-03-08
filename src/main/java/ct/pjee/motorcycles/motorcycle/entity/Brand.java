package ct.pjee.motorcycles.motorcycle.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
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
@Table(name = "brands")
public class Brand implements Serializable {

    @Id
    UUID id;

    String name;

    LocalDate dateOfFounding;

    int numberOfEmployees;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "brand", cascade = CascadeType.REMOVE)
    private List<Motorcycle> motorcycles;
}
