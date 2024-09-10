package com.web.human_milk_bank.service;

import com.web.human_milk_bank.payload.RecipientMotherDTO;
import com.web.human_milk_bank.payload.RecipientMotherResponse;
import jakarta.validation.Valid;

public interface RecipientMotherService {

    RecipientMotherDTO createRecipient(RecipientMotherDTO recipientMotherDTO);

    RecipientMotherResponse getAllRecipients(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    RecipientMotherDTO updateRecipient(@Valid RecipientMotherDTO recipientMotherDTO, Long recipientId);

    RecipientMotherDTO deleteRecipient(Long recipientId);
}
