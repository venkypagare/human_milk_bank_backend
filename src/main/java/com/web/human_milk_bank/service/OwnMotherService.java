package com.web.human_milk_bank.service;

import com.web.human_milk_bank.payload.OwnMotherDTO;
import com.web.human_milk_bank.payload.OwnMotherResponse;
import jakarta.validation.Valid;

public interface OwnMotherService {
    OwnMotherDTO createOwnMother(OwnMotherDTO ownMotherDTO);

    OwnMotherResponse getAllOwnMothers(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    OwnMotherDTO updateOwnMother(@Valid OwnMotherDTO ownMotherDTO, Long ownmotherId);

    OwnMotherDTO deleteOwnMother(Long ownmotherId);
}
