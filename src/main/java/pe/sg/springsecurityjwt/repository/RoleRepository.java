package pe.sg.springsecurityjwt.repository;

import org.springframework.data.repository.CrudRepository;

import pe.sg.springsecurityjwt.models.RoleEntity;

public interface RoleRepository extends CrudRepository<RoleEntity,Long>{

}
