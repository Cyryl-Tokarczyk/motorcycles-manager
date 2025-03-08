package ct.pjee.motorcycles.motorcycle.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class MotorcycleModel {

    private String name;

    private String brand;

    private int displacement;

}