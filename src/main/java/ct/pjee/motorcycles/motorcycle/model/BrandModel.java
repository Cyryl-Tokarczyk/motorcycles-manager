package ct.pjee.motorcycles.motorcycle.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class BrandModel {

    private String name;

    private LocalDate dateOfFounding;

    private int numberOfEmployees;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Motorcycle {

        private UUID id;

        private String name;

        private Long version;

        private LocalDateTime creationDateTime;

        private LocalDateTime updateDateTime;

    }

    @Singular
    private List<Motorcycle> motorcycles;

}
