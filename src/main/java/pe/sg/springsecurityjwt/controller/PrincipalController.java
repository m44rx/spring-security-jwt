package pe.sg.springsecurityjwt.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.sg.springsecurityjwt.models.ERole;
import pe.sg.springsecurityjwt.models.RoleEntity;
import pe.sg.springsecurityjwt.models.UserEntity;
import pe.sg.springsecurityjwt.repository.UserRepository;
import pe.sg.springsecurityjwt.request.CreateUserDto;

@RestController
public class PrincipalController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/hello")
    public String hello() {
        return "Hello world, not secured";
    }

    @GetMapping("/helloSecured")
    public String heloSecured() {
        return "hello world secured";
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDto dto) {

        Set<RoleEntity> roles = dto.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .roles(roles)
                .build();

        userRepository.save(userEntity);

        return ResponseEntity.ok(userEntity);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam String id) {

        userRepository.deleteById(Long.parseLong(id));
        return "Se ha borrado el user con id ".concat(id);

    }

}
