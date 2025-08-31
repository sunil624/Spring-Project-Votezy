package com.votezy.service;

import com.votezy.entity.Candidate;
import com.votezy.entity.Vote;
import com.votezy.exception.ResourceNotFoundException;
import com.votezy.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {
    private final CandidateRepository candidateRepository;
    @Autowired
    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Candidate addCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public Candidate getCandidateById(Long id) {
        Candidate candidate = candidateRepository.findById(id).orElse(null);
        if(candidate == null) {
            throw new ResourceNotFoundException(("Candidate with id " + id + " not found"));
        }
        return candidate;
    }

    public Candidate updateCandidate(Long id, Candidate updatedCandidate) {
        Candidate candidate = getCandidateById(id);
        if (updatedCandidate.getName() != null) {
            candidate.setName(updatedCandidate.getName());
        }
        if (updatedCandidate.getParty() != null) {
            candidate.setParty(updatedCandidate.getParty());
        }
        return candidateRepository.save(candidate);
    }

    public void deleteCandidate(Long id) {
        Candidate candidate = getCandidateById(id);
        List<Vote> votes = candidate.getVotes();
        for (Vote vote : votes) {
            vote.setCandidate(null);
        }
        candidate.getVotes().clear();
        candidateRepository.delete(candidate);
    }
}
