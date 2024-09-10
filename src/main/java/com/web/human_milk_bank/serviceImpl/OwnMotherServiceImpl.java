package com.web.human_milk_bank.serviceImpl;

import com.web.human_milk_bank.exceptions.APIException;
import com.web.human_milk_bank.exceptions.ResourceNotFoundException;
import com.web.human_milk_bank.models.OwnMother;
import com.web.human_milk_bank.payload.OwnMotherDTO;
import com.web.human_milk_bank.payload.OwnMotherResponse;
import com.web.human_milk_bank.repositories.OwnMotherRepository;
import com.web.human_milk_bank.service.OwnMotherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OwnMotherServiceImpl implements OwnMotherService {
    @Autowired
    private OwnMotherRepository ownMotherRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OwnMotherDTO createOwnMother(OwnMotherDTO ownMotherDTO) {
        OwnMother ownMother = modelMapper.map(ownMotherDTO, OwnMother.class);
        ownMotherRepository.save(ownMother);
        return modelMapper.map(ownMother, OwnMotherDTO.class);
    }

    @Override
    public OwnMotherResponse getAllOwnMothers(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<OwnMother> ownMothers = ownMotherRepository.findAll(pageable);
        List<OwnMother> getAllLists = ownMothers.getContent();
        if(getAllLists.isEmpty())
            throw new APIException("Own mother list is empty");
        List<OwnMotherDTO> ownMotherDTOS = getAllLists.stream()
                .map(ownMother -> modelMapper.map(ownMother, OwnMotherDTO.class))
                .toList();
        OwnMotherResponse ownMotherResponse = new OwnMotherResponse();
        ownMotherResponse.setContent(ownMotherDTOS);
        ownMotherResponse.setPageNumber(ownMothers.getNumber());
        ownMotherResponse.setPageSize(ownMothers.getSize());
        ownMotherResponse.setTotalPages(ownMothers.getTotalPages());
        ownMotherResponse.setTotalElements(ownMothers.getTotalElements());
        ownMotherResponse.setLastPage(ownMothers.isLast());
        return ownMotherResponse;
    }

    @Override
    public OwnMotherDTO updateOwnMother(OwnMotherDTO ownMotherDTO, Long ownmotherId) {
        OwnMother savedMother = ownMotherRepository.findById(ownmotherId)
                .orElseThrow(()->new ResourceNotFoundException("Own MOther","ownMotherId",ownmotherId));
        OwnMother ownMother = modelMapper.map(ownMotherDTO, OwnMother.class);
        ownMother.setOwnMotherId(ownMotherDTO.getOwnMotherId());
        savedMother = ownMotherRepository.save(ownMother);
        return modelMapper.map(savedMother, OwnMotherDTO.class);
    }

    @Override
    public OwnMotherDTO deleteOwnMother(Long ownmotherId) {
        OwnMother ownMother = ownMotherRepository.findById(ownmotherId)
                .orElseThrow(()-> new ResourceNotFoundException("Own Mother","ownMotherId",ownmotherId));
        ownMotherRepository.delete(ownMother);
        return modelMapper.map(ownMother,OwnMotherDTO.class);
    }
}
