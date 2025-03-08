package ct.pjee.motorcycles.motorcycle.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class MotorcycleEditModel {

    private UUID id;

    private String name;

    private int displacement;

    private NoMotorcyclesBrandModel brand;

    private Long version;

}
