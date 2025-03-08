package ct.pjee.motorcycles.user.dto.function;

import ct.pjee.motorcycles.user.dto.GetUserResponse;
import ct.pjee.motorcycles.user.entity.User;

import java.util.function.Function;

public class UserToResponseFunction implements Function<User, GetUserResponse> {

    @Override
    public GetUserResponse apply(User user) {
        return GetUserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();
    }

}
