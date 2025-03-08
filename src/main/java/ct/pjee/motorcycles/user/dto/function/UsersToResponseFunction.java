package ct.pjee.motorcycles.user.dto.function;

import ct.pjee.motorcycles.user.dto.GetUsersResponse;
import ct.pjee.motorcycles.user.entity.User;

import java.util.List;
import java.util.function.Function;

public class UsersToResponseFunction implements Function<List<User>, GetUsersResponse> {

    @Override
    public GetUsersResponse apply(List<User> users) {
        return GetUsersResponse.builder()
                .users(users.stream()
                        .map(user -> GetUsersResponse.User.builder()
                                .id(user.getId())
                                .username(user.getUsername())
                                .build())
                        .toList())
                .build();
    }

}
