package ru.arsentiev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.arsentiev.dto.UserPasDto;
import ru.arsentiev.dto.UserReadDto;
import ru.arsentiev.entity.Role;
import ru.arsentiev.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<UserPasDto> findUserByUsername(String username);

    List<UserReadDto> findAllByRoleOrderByUsernameDesc(Role role);
}
