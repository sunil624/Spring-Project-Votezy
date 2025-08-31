package com.votezy.controller;

import com.votezy.entity.Candidate;
import com.votezy.service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidate")
@CrossOrigin
public class CandidateController {
    private final CandidateService candidateService;
    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping("/register")
    public ResponseEntity<Candidate> addCandidate(@RequestBody @Valid Candidate candidate) {
        Candidate newCandidate = candidateService.addCandidate(candidate);
        return new ResponseEntity<>(newCandidate, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id) {
        Candidate candidate = candidateService.getCandidateById(id);
        return new ResponseEntity<>(candidate, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        List<Candidate> candidates = candidateService.getAllCandidates();
        return new ResponseEntity<>(candidates, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Candidate> updateCandidate(@PathVariable Long id, @RequestBody Candidate candidate) {
        Candidate updatedCandidate = candidateService.updateCandidate(id, candidate);
        return new ResponseEntity<>(updatedCandidate, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>  deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return new ResponseEntity<>("Candidate has been deleted", HttpStatus.OK);
    }
}
