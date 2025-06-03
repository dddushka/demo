package com.example.demo.model.repository;

import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.School;
import com.example.demo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    List<User> findBySchool(School school);

    @Query("""
    SELECT u FROM User u
    WHERE u.school.id = :schoolId
      AND :role MEMBER OF u.roles
      AND u.id NOT IN (
        SELECT s.user.id FROM Schoolchild s WHERE s.user IS NOT NULL
      )
      AND u.id NOT IN (
        SELECT t.user.id FROM Teacher t WHERE t.user IS NOT NULL
      )
""")
    List<User> findAvailableUsersWithRole(@Param("schoolId") Integer schoolId, @Param("role") Role role);

    List<User> findByUsernameContainingAndSchool(String username, School school);

}
