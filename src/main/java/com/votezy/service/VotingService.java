package com.votezy.service;

import com.votezy.entity.Candidate;
import com.votezy.entity.Vote;
import com.votezy.entity.Voter;
import com.votezy.exception.ResourceNotFoundException;
import com.votezy.exception.VoteNotAllowedException;
import com.votezy.repository.CandidateRepository;
import com.votezy.repository.VoteRepository;
import com.votezy.repository.VoterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotingService {
    private final VoteRepository voteRepository;
    private final CandidateRepository candidateRepository;
    private final VoterRepository voterRepository;

    @Autowired
    public VotingService(VoteRepository voteRepository, CandidateRepository candidateRepository, VoterRepository voterRepository) {
        this.voteRepository = voteRepository;
        this.candidateRepository = candidateRepository;
        this.voterRepository = voterRepository;
    }

    @Transactional
    public Vote castVote(Long candidateId, Long voterId) {
        if(!voterRepository.existsById(voterId)){
            throw new ResourceNotFoundException("Voter ID: " + voterId + " not found");
        }
        if(!candidateRepository.existsById(candidateId)){
            throw new ResourceNotFoundException("Candidate ID: " + candidateId + " not found");
        }
        Voter voter = voterRepository.findById(voterId).get();
        if(voter.isHasVoted()){
            throw new VoteNotAllowedException("Voter ID: " + voterId + " is already voted");
        }
        Candidate candidate = candidateRepository.findById(candidateId).get();
        Vote vote = new Vote();
        vote.setCandidate(candidate);
        vote.setVoter(voter);
        voteRepository.save(vote);
        candidate.setVoteCount(candidate.getVoteCount() + 1);
        candidateRepository.save(candidate);
        voter.setHasVoted(true);
        voterRepository.save(voter);
        return vote;
    }

    public List<Vote> getAllVotes(){
        return voteRepository.findAll();
    }
}
