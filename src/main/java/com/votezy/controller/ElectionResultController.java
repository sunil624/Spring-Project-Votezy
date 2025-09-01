package com.votezy.controller;

import com.votezy.dto.ElectionResultRequestDTO;
import com.votezy.dto.ElectionResultResponseDTO;
import com.votezy.entity.ElectionResult;
import com.votezy.service.ElectionResultService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/election-result")
@CrossOrigin
public class ElectionResultController {
    private final ElectionResultService electionResultService;
    @Autowired
    public ElectionResultController(ElectionResultService electionResultService){
        this.electionResultService = electionResultService;
    }

    @PostMapping("/declare")
    public ResponseEntity<ElectionResultResponseDTO> declareElectionResult(@RequestBody @Valid ElectionResultRequestDTO electionResultRequestDTO){
        ElectionResult electionResult = electionResultService.declareElectionResult(electionResultRequestDTO.getElectionName());
        ElectionResultResponseDTO electionResultResponseDTO = new ElectionResultResponseDTO();
        electionResultResponseDTO.setElectionName(electionResult.getElectionName());
        electionResultResponseDTO.setTotalVotes(electionResult.getTotalVoters());
        electionResultResponseDTO.setWinnerId(electionResult.getWinnerId());
        electionResultResponseDTO.setWinnerPoints(electionResult.getWinner().getVoteCount());
        return new ResponseEntity<>(electionResultResponseDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ElectionResult>>getAllElectionResult(){
        List<ElectionResult> electionResults = electionResultService.getAllElectionResult();
        return new ResponseEntity<>(electionResults, HttpStatus.OK);
    }
}
