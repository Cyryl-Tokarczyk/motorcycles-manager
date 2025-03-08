package ct.pjee.motorcycles.motorcycle.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PatchMotorcycleRequest {

    String name;

    int displacement;

}
