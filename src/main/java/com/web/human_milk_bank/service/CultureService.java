package com.web.human_milk_bank.service;

import com.web.human_milk_bank.payload.CultureDTO;
import com.web.human_milk_bank.payload.CultureResponse;
import jakarta.validation.Valid;

public interface CultureService {
    CultureDTO createCulture(@Valid CultureDTO cultureDTO);

    CultureResponse getAllCultures(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    CultureDTO updateCulture(@Valid CultureDTO cultureDTO, Long cultureId);

    CultureDTO deleteCulture(Long cultureId);
}
