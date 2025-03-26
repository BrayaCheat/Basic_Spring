package com._5.basic.repository;

import com._5.basic.model.AuthorProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorProfileRepository extends JpaRepository<AuthorProfile, Long> {
}
