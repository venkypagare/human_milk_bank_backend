package com.web.human_milk_bank.controller;

import com.web.human_milk_bank.config.AppConstants;
import com.web.human_milk_bank.payload.CultureDTO;
import com.web.human_milk_bank.payload.CultureResponse;
import com.web.human_milk_bank.service.CultureService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CultureController {
    @Autowired
    private CultureService cultureService;

    @PostMapping("/public/culture")
    public ResponseEntity<CultureDTO> createCulture(@Valid @RequestBody CultureDTO cultureDTO) {
        CultureDTO savedDTO = cultureService.createCulture(cultureDTO);
        return new ResponseEntity<>(savedDTO, HttpStatus.CREATED);
    }

    @GetMapping("/public/cultures")
    public ResponseEntity<CultureResponse> getAllCultures(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_CULTURE_BY,required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder
    ) {
        CultureResponse response = cultureService.getAllCultures(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/public/culture/{cultureId}")
    public ResponseEntity<CultureDTO> updateCulture(@Valid @RequestBody CultureDTO cultureDTO, @PathVariable Long cultureId) {
        CultureDTO updatedDTO = cultureService.updateCulture(cultureDTO,cultureId);
        return new ResponseEntity<>(updatedDTO, HttpStatus.OK);
    }

    @DeleteMapping("/public/culture/{cultureId}")
    public ResponseEntity<CultureDTO> deleteCulture(@PathVariable Long cultureId) {
        CultureDTO deletedDTO = cultureService.deleteCulture(cultureId);
        return new ResponseEntity<>(deletedDTO, HttpStatus.OK);
    }
}
