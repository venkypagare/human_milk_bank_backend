package com.web.human_milk_bank.controller;

import com.web.human_milk_bank.config.AppConstants;
import com.web.human_milk_bank.payload.OwnMotherDTO;
import com.web.human_milk_bank.payload.OwnMotherResponse;
import com.web.human_milk_bank.service.OwnMotherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OwnMotherController {
    @Autowired
    private OwnMotherService ownMotherService;

    @PostMapping("/public/ownmother")
    public ResponseEntity<OwnMotherDTO> createOwnMother(@Valid @RequestBody OwnMotherDTO ownMotherDTO) {
        OwnMotherDTO savedDTO = ownMotherService.createOwnMother(ownMotherDTO);
        return new ResponseEntity<>(savedDTO, HttpStatus.CREATED);
    }

    @GetMapping("/public/ownmother")
    public ResponseEntity<OwnMotherResponse> getAllOwnMothers(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSIze", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_OWNMOTHER_BY, required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    ) {
        OwnMotherResponse ownMotherResponse = ownMotherService.getAllOwnMothers(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(ownMotherResponse, HttpStatus.OK);
    }

    @PutMapping("/public/ownmother/{ownmotherId}")
    public ResponseEntity<OwnMotherDTO> updateOwnMother(@Valid @RequestBody OwnMotherDTO ownMotherDTO, @PathVariable Long ownmotherId) {
        OwnMotherDTO updatedDTO = ownMotherService.updateOwnMother(ownMotherDTO,ownmotherId);
        return new ResponseEntity<>(updatedDTO, HttpStatus.OK);
    }

    @DeleteMapping("/public/ownmother/{ownmotherId}")
    public ResponseEntity<OwnMotherDTO> deleteOwnMother(@PathVariable Long ownmotherId) {
        OwnMotherDTO ownMotherDTO = ownMotherService.deleteOwnMother(ownmotherId);
        return new ResponseEntity<>(ownMotherDTO, HttpStatus.OK);
    }

}
