package ct.pjee.motorcycles.motorcycle.model;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class NoMotorcyclesBrandModel {

    private UUID id;

    private String name;

    private LocalDate dateOfFounding;

    private int numberOfEmployees;

}
