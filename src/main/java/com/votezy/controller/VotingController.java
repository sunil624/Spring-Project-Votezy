package com.votezy.controller;

import com.votezy.dto.VoteRequestDTO;
import com.votezy.dto.VoteResponseDTO;
import com.votezy.entity.Vote;
import com.votezy.service.VotingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votes")
@CrossOrigin
public class VotingController {
    private final VotingService votingService;
    @Autowired
    public VotingController(VotingService votingService) {
        this.votingService = votingService;
    }

    @PostMapping("/cast")
    public ResponseEntity<VoteResponseDTO> castVote(@RequestBody @Valid VoteRequestDTO voteRequestDTO){
        Vote vote = votingService.castVote(voteRequestDTO.getCandidateId(),voteRequestDTO.getVoterId());
        VoteResponseDTO voteResponseDTO = new VoteResponseDTO("Vote casted successfully.",true,vote.getCandidateId(),vote.getVoterId());
        return new ResponseEntity<>(voteResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Vote>> getAllVotes(){
        List<Vote> votes = votingService.getAllVotes();
        return new ResponseEntity<>(votes, HttpStatus.OK);
    }

}
