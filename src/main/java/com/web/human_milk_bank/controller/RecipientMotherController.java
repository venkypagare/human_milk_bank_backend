package com.web.human_milk_bank.controller;

import com.web.human_milk_bank.config.AppConstants;
import com.web.human_milk_bank.payload.RecipientMotherDTO;
import com.web.human_milk_bank.payload.RecipientMotherResponse;
import com.web.human_milk_bank.service.RecipientMotherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RecipientMotherController {
    @Autowired
    private RecipientMotherService recipientMotherService;

    @PostMapping("/public/recipient")
    public ResponseEntity<RecipientMotherDTO> createRecipient(@Valid @RequestBody RecipientMotherDTO recipientMotherDTO) {
        RecipientMotherDTO dto = recipientMotherService.createRecipient(recipientMotherDTO);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/public/recipients")
    public ResponseEntity<RecipientMotherResponse> getAllRecipients(
            @RequestParam(name = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_RECIPIENTS_BY, required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = AppConstants.SORT_DIR,required = false) String sortOrder
    ) {
        RecipientMotherResponse response = recipientMotherService.getAllRecipients(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/public/recipient/{recipientId}")
    public ResponseEntity<RecipientMotherDTO> updateRecipient(@Valid @RequestBody RecipientMotherDTO recipientMotherDTO
                                                            , @PathVariable Long recipientId) {
        RecipientMotherDTO dto = recipientMotherService.updateRecipient(recipientMotherDTO,recipientId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/public/recipient/{recipientId}")
    public ResponseEntity<RecipientMotherDTO> deleteRecipient(@PathVariable Long recipientId) {
       RecipientMotherDTO recipientMotherDTO = recipientMotherService.deleteRecipient(recipientId);
       return new ResponseEntity<>(recipientMotherDTO, HttpStatus.OK);
    }
}
