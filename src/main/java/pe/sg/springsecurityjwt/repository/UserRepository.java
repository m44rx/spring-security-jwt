package pe.sg.springsecurityjwt.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import pe.sg.springsecurityjwt.models.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

}
