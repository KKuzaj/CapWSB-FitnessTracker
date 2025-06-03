package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;

@Component
public class UserMapper {

    // Główna wersja zwracająca API DTO – używana np. w TrainingDto
    public UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail()
        );
    }

    // Alternatywna wersja zwracająca wewnętrzne DTO
    public pl.wsb.fitnesstracker.user.internal.UserDto toInternalDto(User user) {
        return new pl.wsb.fitnesstracker.user.internal.UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail()
        );
    }

    public UserSimpleDto toSimpleDto(User user) {
        return new UserSimpleDto(user.getId(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    public UserEmailDto toEmailDto(User user) {
        return new UserEmailDto(user.getId(), user.getEmail());
    }

    public User toEntity(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
    }
}