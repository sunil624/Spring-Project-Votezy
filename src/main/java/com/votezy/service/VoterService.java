package com.votezy.service;

import com.votezy.entity.Candidate;
import com.votezy.entity.Vote;
import com.votezy.entity.Voter;
import com.votezy.exception.DuplicateResourceException;
import com.votezy.exception.ResourceNotFoundException;
import com.votezy.repository.CandidateRepository;
import com.votezy.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VoterService {
    private final VoterRepository voterRepository;
    private final CandidateRepository candidateRepository;

    @Autowired
    public VoterService(VoterRepository voterRepository, CandidateRepository candidateRepository) {
        this.voterRepository = voterRepository;
        this.candidateRepository = candidateRepository;
    }

    public Voter registerVoter(Voter voter) {
        if (voterRepository.existsByEmail(voter.getEmail())) {
            throw new DuplicateResourceException("Voter with email " + voter.getEmail() + " already exists");
        }
        return voterRepository.save(voter);
    }

    public List<Voter> getAllVoters() {
        return voterRepository.findAll();
    }

    public Voter getVoterById(Long id) {
        Voter voter =  voterRepository.findById(id).orElse(null);
        if (voter == null) {
            throw new ResourceNotFoundException("Voter with id " + id + " not found");
        }
        return voter;
    }

    public Voter updateVoter(Long id, Voter updateVoter) {
        Voter voter = voterRepository.findById(id).orElse(null);
        if (voter == null) {
            throw new ResourceNotFoundException("Voter with id " + id + " not found");
        }
        if(updateVoter.getName() != null) {
            voter.setName(updateVoter.getName());
        }
        if(updateVoter.getEmail() != null) {
            voter.setEmail(updateVoter.getEmail());
        }
        return voterRepository.save(voter);
    }

    @Transactional
    public void deleteVoter(Long id) {
        Voter voter = voterRepository.findById(id).orElse(null);
        if (voter == null) {
            throw new ResourceNotFoundException("Cannot delete voter with id " + id + " not exists");
        }
        Vote vote = voter.getVote();
        if (vote != null) {
            Candidate candidate = vote.getCandidate();
            candidate.setVoteCount(candidate.getVoteCount() - 1);
            candidateRepository.save(candidate);
        }
        voterRepository.delete(voter);
    }
}
