package com.votezy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteResponseDTO {
    private String message;
    private boolean success;
    private Long candidateId;
    private Long voteId;
}
