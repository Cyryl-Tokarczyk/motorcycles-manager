package ct.pjee.motorcycles.motorcycle.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutBrandRequest {

    private String name;

    private LocalDate dateOfFounding;

    int numberOfEmployees;

}
