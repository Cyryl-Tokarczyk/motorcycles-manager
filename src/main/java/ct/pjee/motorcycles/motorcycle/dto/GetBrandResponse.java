package ct.pjee.motorcycles.motorcycle.dto;

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
public class GetBrandResponse {

    UUID id;

    String name;

    LocalDate dateOfFounding;

    int numberOfEmployees;

}
