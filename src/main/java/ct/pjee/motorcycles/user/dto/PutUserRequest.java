package ct.pjee.motorcycles.user.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutUserRequest {

    private String username;

    private String password;

    private List<String> roles;

}
