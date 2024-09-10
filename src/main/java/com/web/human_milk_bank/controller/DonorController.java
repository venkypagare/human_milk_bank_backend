package com.web.human_milk_bank.controller;

import com.web.human_milk_bank.config.AppConstants;
import com.web.human_milk_bank.payload.DonorDTO;
import com.web.human_milk_bank.payload.DonorResponse;
import com.web.human_milk_bank.service.DonorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DonorController {
    @Autowired
    private DonorService donorService;

    @PostMapping("/admin/donor")
    public ResponseEntity<DonorDTO> createDonor(@Valid @RequestBody DonorDTO donorDTO) {
        DonorDTO savedDonorDTO = donorService.createDonor(donorDTO);
        return new ResponseEntity<>(savedDonorDTO, HttpStatus.CREATED);
    }

    @GetMapping("/public/donors")
    public ResponseEntity<DonorResponse> getAllDonors(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_DONORS_BY,required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    ) {
        DonorResponse donorsList = donorService.getAllDonors(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(donorsList, HttpStatus.OK);
    }

    @PutMapping("/public/donor/{donorId}")
    public ResponseEntity<DonorDTO> updateDonor(@Valid @RequestBody DonorDTO donorDTO, @PathVariable Long donorId) {
        DonorDTO dto = donorService.updateDonor(donorDTO,donorId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/public/donor/{donorId}")
    public ResponseEntity<DonorDTO> deleteDonor(@PathVariable Long donorId) {
        DonorDTO deletedDonor = donorService.deleteDonor(donorId);
        return new ResponseEntity<>(deletedDonor, HttpStatus.OK);
    }
}
