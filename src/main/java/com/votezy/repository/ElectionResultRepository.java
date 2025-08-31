package com.votezy.repository;

import com.votezy.entity.ElectionResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ElectionResultRepository extends JpaRepository<ElectionResult, Long> {
    Optional<ElectionResult> findByElectionName(String electionName);
}
