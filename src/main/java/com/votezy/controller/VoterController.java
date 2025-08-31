package com.votezy.controller;

import com.votezy.entity.Voter;
import com.votezy.service.VoterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voters")
@CrossOrigin
public class VoterController {
    private final VoterService voterService;
    @Autowired
    public VoterController(VoterService voterService) {
        this.voterService = voterService;
    }

    @PostMapping("/register")
    public ResponseEntity<Voter> registerVoter(@RequestBody @Valid Voter voter) {
        Voter newVoter = voterService.registerVoter(voter);
        return new ResponseEntity<>(newVoter, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voter> getVoterById(@PathVariable Long id) {
        Voter voter = voterService.getVoterById(id);
        return new ResponseEntity<>(voter, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Voter>> getAllVoters() {
        List<Voter> voters = voterService.getAllVoters();
        return new ResponseEntity<>(voters, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Voter> updateVoter(@PathVariable Long id, @RequestBody Voter voter) {
        Voter updatedVoter = voterService.updateVoter(id, voter);
        return new ResponseEntity<>(updatedVoter, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>  deleteVoter(@PathVariable Long id) {
        voterService.deleteVoter(id);
        return new ResponseEntity<>("Voter has been deleted", HttpStatus.OK);
    }
}
