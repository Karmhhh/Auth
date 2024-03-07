package com.example.Auth.repos;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.Auth.Entity.UserDb;

@Repository
public interface UserRepos extends JpaRepository<UserDb, Long> {

    @Query("SELECT u FROM UserDb u WHERE u.fiscalCode = :fiscalCode")
    public Optional<UserDb> findUserByFiscalCode(@Param("fiscalCode") String fiscalCode);

    @Query("SELECT u FROM UserDb u WHERE u.role = :role")
    public List<UserDb> findAllUsersByRole(@Param("role") String role);

}