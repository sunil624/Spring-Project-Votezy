package com.votezy.service;

import com.votezy.entity.Candidate;
import com.votezy.entity.ElectionResult;
import com.votezy.exception.ResourceNotFoundException;
import com.votezy.repository.CandidateRepository;
import com.votezy.repository.ElectionResultRepository;
import com.votezy.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElectionResultService {
    private final ElectionResultRepository electionResultRepository;
    private final CandidateRepository candidateRepository;
    private final VoteRepository voteRepository;
    @Autowired
    public ElectionResultService(ElectionResultRepository electionResultRepository, CandidateRepository candidateRepository, VoteRepository voteRepository) {
        this.electionResultRepository = electionResultRepository;
        this.candidateRepository = candidateRepository;
        this.voteRepository = voteRepository;
    }

    public ElectionResult declareElectionResult(String electionName){
        Optional<ElectionResult> electionResult = electionResultRepository.findByElectionName(electionName);
        if(electionResult.isPresent()){
            return electionResult.get();
        }
        if(voteRepository.count()==0){
            throw new IllegalStateException("Cannot declare the result as no votes have casted.");
        }
        List<Candidate> candidates = candidateRepository.findAllByOrderByVoteCountDesc();
        if(candidates.isEmpty())
            throw new ResourceNotFoundException("Cannot declare the result as no candidates have casted.");
        Candidate winner = candidates.getFirst();
        int totalVotes = 0;
        for(Candidate candidate :  candidates){
            totalVotes+=candidate.getVoteCount();
        }
        ElectionResult result = new ElectionResult();
        result.setElectionName(electionName);
        result.setWinner(winner);
        result.setTotalVoters(totalVotes);
        return electionResultRepository.save(result);
    }

    public List<ElectionResult> getAllElectionResult(){
        return electionResultRepository.findAll();
    }
}
