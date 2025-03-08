package ct.pjee.motorcycles.motorcycle.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutMotorcycleRequest {

    private String name;

    private int displacement;

}
