package com.web.human_milk_bank.service;

import com.web.human_milk_bank.payload.DonorDTO;
import com.web.human_milk_bank.payload.DonorResponse;
import jakarta.validation.Valid;

public interface DonorService {
    DonorDTO createDonor(DonorDTO donorDTO);

    DonorResponse getAllDonors(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    DonorDTO updateDonor(@Valid DonorDTO donorDTO, Long donorId);

    DonorDTO deleteDonor(Long donorId);
}
