package ct.pjee.motorcycles.user.dto.function;

import ct.pjee.motorcycles.user.dto.PutUserRequest;
import ct.pjee.motorcycles.user.entity.User;

import java.util.UUID;
import java.util.function.BiFunction;

public class RequestToUserFunction implements BiFunction<UUID, PutUserRequest, User> {

    @Override
    public User apply(UUID id,PutUserRequest request) {
        return User.builder()
                .id(id)
                .username(request.getUsername())
                .password(request.getPassword())
                .roles(request.getRoles())
                .build();
    }

}
