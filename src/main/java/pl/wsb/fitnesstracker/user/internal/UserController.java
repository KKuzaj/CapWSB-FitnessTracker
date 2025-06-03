package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<pl.wsb.fitnesstracker.user.api.UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public pl.wsb.fitnesstracker.user.api.UserDto addUser(@RequestBody UserDto userDto) {
        var createdUser = userService.addUser(userDto);
        return userMapper.toDto(createdUser);
    }

    @GetMapping("/simple")
    public List<UserSimpleDto> getAllSimpleUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    @GetMapping("/email")
    public List<pl.wsb.fitnesstracker.user.api.UserDto> getUsersByEmail(@RequestParam String email) {
        return userService.findUsersByEmailLike(email).stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public pl.wsb.fitnesstracker.user.api.UserDto getUserById(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }
    @PutMapping("/{id}")
    public pl.wsb.fitnesstracker.user.api.UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User updatedUser = userService.updateUser(id, userMapper.toEntity(userDto));
        return userMapper.toDto(updatedUser);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}