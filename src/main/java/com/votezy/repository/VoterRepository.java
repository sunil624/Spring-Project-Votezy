package com.votezy.repository;

import com.votezy.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepository extends JpaRepository<Voter, Long> {
   boolean existsByEmail(String email);
}
