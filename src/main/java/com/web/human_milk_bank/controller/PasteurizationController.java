package com.web.human_milk_bank.controller;

import com.web.human_milk_bank.config.AppConstants;
import com.web.human_milk_bank.payload.PasteurizationDTO;
import com.web.human_milk_bank.payload.PasteurizationResponse;
import com.web.human_milk_bank.service.PasteurizationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PasteurizationController {
    @Autowired
    private PasteurizationService pasteurizationService;

    @PostMapping("/public/pasteurize")
    public ResponseEntity<PasteurizationDTO> createPasteurization(@Valid @RequestBody PasteurizationDTO pasteurizationDTO) {
        PasteurizationDTO savedDTO = pasteurizationService.createPasteurization(pasteurizationDTO);
        return new ResponseEntity<>(savedDTO, HttpStatus.CREATED);
    }

    @GetMapping("/public/pasteurizes")
    public ResponseEntity<PasteurizationResponse> getAllPasteurizes(
            @RequestParam(name = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_PASTEURIZE_BY, required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    ) {
        PasteurizationResponse response = pasteurizationService.getAllPasteurizeRecords(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/admin/pasteurize/{pasteurizationId}")
    public ResponseEntity<PasteurizationDTO> updatePasteurization(@Valid @PathVariable Long pasteurizationId, @RequestBody PasteurizationDTO pasteurizationDTO) {
        PasteurizationDTO updatedDTO = pasteurizationService.updatePasteurization(pasteurizationId,pasteurizationDTO);
        return new ResponseEntity<>(updatedDTO, HttpStatus.OK);
    }

    @DeleteMapping("/admin/pasteurize/{pasteurizationId}")
    public ResponseEntity<PasteurizationDTO> deletePasteurization(@PathVariable Long pasteurizationId){
        PasteurizationDTO deletedDTO = pasteurizationService.deletePasteurization(pasteurizationId);
        return new ResponseEntity<>(deletedDTO, HttpStatus.OK);
    }
}
