package pe.sg.springsecurityjwt;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import pe.sg.springsecurityjwt.models.ERole;
import pe.sg.springsecurityjwt.models.RoleEntity;
import pe.sg.springsecurityjwt.models.UserEntity;
import pe.sg.springsecurityjwt.repository.UserRepository;

@SpringBootApplication
public class SpringSecurityJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtApplication.class, args);
	}

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository userRepository;

	@Bean
	CommandLineRunner init() {
		return args -> {

			UserEntity userEntity = UserEntity.builder()
					.email("santiago@email.com")
					.username("Santiago")
					.password(passwordEncoder.encode("1234"))
					.roles(
							Set.of(RoleEntity.builder()
									.name(ERole.valueOf(ERole.ADMIN.name()))
									.build()))
					.build();

			UserEntity userEntity2 = UserEntity.builder()
					.email("ayni2@email.com")
					.username("Ayni")
					.password(passwordEncoder.encode("1234"))
					.roles(
							Set.of(RoleEntity.builder()
									.name(ERole.valueOf(ERole.USER.name()))
									.build()))
					.build();

			UserEntity userEntity3 = UserEntity.builder()
					.email("andrea@email.com")
					.username("Andrea")
					.password(passwordEncoder.encode("1234"))
					.roles(
							Set.of(RoleEntity.builder()
									.name(ERole.valueOf(ERole.INVITED.name()))
									.build()))
					.build();

			userRepository.saveAll(List.of(userEntity, userEntity2, userEntity3));

		};
	}

}
