package com.web.human_milk_bank.serviceImpl;

import com.web.human_milk_bank.exceptions.APIException;
import com.web.human_milk_bank.exceptions.ResourceNotFoundException;
import com.web.human_milk_bank.models.Pasteurization;
import com.web.human_milk_bank.payload.PasteurizationDTO;
import com.web.human_milk_bank.payload.PasteurizationResponse;
import com.web.human_milk_bank.repositories.PasteurizationRepository;
import com.web.human_milk_bank.service.PasteurizationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PasteurizationServiceImpl implements PasteurizationService {
    @Autowired
    private PasteurizationRepository pasteurizationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PasteurizationDTO createPasteurization(PasteurizationDTO pasteurizationDTO) {
        Pasteurization pasteurization = modelMapper.map(pasteurizationDTO, Pasteurization.class);
        pasteurizationRepository.save(pasteurization);
        return modelMapper.map(pasteurization, PasteurizationDTO.class);
    }

    @Override
    public PasteurizationResponse getAllPasteurizeRecords(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sortByAndOrder);
        Page<Pasteurization> pasteurizations = pasteurizationRepository.findAll(pageable);
        List<Pasteurization> pasteurizationList = pasteurizations.getContent();
        if(pasteurizationList.isEmpty())
            throw new APIException("No pasteurizations found");
        List<PasteurizationDTO> dtoList = pasteurizationList.stream()
                .map(pasteurization->modelMapper.map(pasteurization, PasteurizationDTO.class))
                .toList();
        PasteurizationResponse pasteurizationResponse = new PasteurizationResponse();
        pasteurizationResponse.setContent(dtoList);
        pasteurizationResponse.setPageNumber(pasteurizations.getNumber());
        pasteurizationResponse.setPageSize(pasteurizations.getSize());
        pasteurizationResponse.setTotalPages(pasteurizations.getTotalPages());
        pasteurizationResponse.setTotalElements(pasteurizations.getTotalElements());
        pasteurizationResponse.setLastPage(pasteurizations.isLast());
        return pasteurizationResponse;
    }

    @Override
    public PasteurizationDTO updatePasteurization(Long pasteurizationId, PasteurizationDTO pasteurizationDTO) {
        Pasteurization savedPasteurization = pasteurizationRepository.findById(pasteurizationId)
                .orElseThrow(()-> new ResourceNotFoundException("Pasteurization","pasteurizationId", pasteurizationId));
        Pasteurization pasteurization = modelMapper.map(pasteurizationDTO, Pasteurization.class);
        pasteurization.setPasteurizationId(pasteurizationDTO.getPasteurizationId());
        savedPasteurization = pasteurizationRepository.save(pasteurization);
        return modelMapper.map(savedPasteurization, PasteurizationDTO.class);
    }

    @Override
    public PasteurizationDTO deletePasteurization(Long pasteurizationId) {
        Pasteurization pasteurization = pasteurizationRepository.findById(pasteurizationId)
                .orElseThrow(()-> new ResourceNotFoundException("Pasteurization","pasteurizationId",pasteurizationId));
        pasteurizationRepository.delete(pasteurization);
        return modelMapper.map(pasteurization, PasteurizationDTO.class);
    }
}
