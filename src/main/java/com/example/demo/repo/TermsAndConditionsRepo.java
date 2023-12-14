package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TermsAndConditionsModel;
@Repository
public interface TermsAndConditionsRepo extends JpaRepository<TermsAndConditionsModel, Long> {

	Optional<TermsAndConditionsModel> findByUserId(Long userId);

}
