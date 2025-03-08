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
public class PatchBrandRequest {

    private String name;

    private LocalDate dateOfFounding;

    private int numberOfEmployees;

}
