package com.web.human_milk_bank.service;

import com.web.human_milk_bank.payload.PasteurizationDTO;
import com.web.human_milk_bank.payload.PasteurizationResponse;

public interface PasteurizationService {
    PasteurizationDTO createPasteurization(PasteurizationDTO pasteurizationDTO);

    PasteurizationResponse getAllPasteurizeRecords(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    PasteurizationDTO updatePasteurization(Long pasteurizationId, PasteurizationDTO pasteurizationDTO);

    PasteurizationDTO deletePasteurization(Long pasteurizationId);
}
