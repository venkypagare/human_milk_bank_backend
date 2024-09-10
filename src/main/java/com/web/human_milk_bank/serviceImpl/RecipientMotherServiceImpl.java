package com.web.human_milk_bank.serviceImpl;

import com.web.human_milk_bank.exceptions.APIException;
import com.web.human_milk_bank.exceptions.ResourceNotFoundException;
import com.web.human_milk_bank.models.RecipientMother;
import com.web.human_milk_bank.payload.RecipientMotherDTO;
import com.web.human_milk_bank.payload.RecipientMotherResponse;
import com.web.human_milk_bank.repositories.RecipientMotherRepository;
import com.web.human_milk_bank.service.RecipientMotherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RecipientMotherServiceImpl implements RecipientMotherService {
    @Autowired
    private RecipientMotherRepository recipientMotherRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RecipientMotherDTO createRecipient(RecipientMotherDTO recipientMotherDTO) {
        RecipientMother recipientMother = modelMapper.map(recipientMotherDTO, RecipientMother.class);
        recipientMotherRepository.save(recipientMother);
        return modelMapper.map(recipientMother, RecipientMotherDTO.class);
    }

    @Override
    public RecipientMotherResponse getAllRecipients(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber,pageSize,sortByAndOrder);
        Page<RecipientMother> recipientMothers = recipientMotherRepository.findAll(pageable);
        List<RecipientMother> recipientMotherList = recipientMothers.getContent();
        if(recipientMotherList.isEmpty())
            throw new APIException("No Recipient mother found.");
        List<RecipientMotherDTO> dto = recipientMotherList.stream()
                .map(recipientMother -> modelMapper.map(recipientMother, RecipientMotherDTO.class))
                .toList();
        RecipientMotherResponse response = new RecipientMotherResponse();
        response.setContent(dto);
        response.setPageNumber(recipientMothers.getNumber());
        response.setPageSize(recipientMothers.getSize());
        response.setTotalElements(recipientMothers.getTotalElements());
        response.setTotalPages(recipientMothers.getTotalPages());
        response.setLastPage(recipientMothers.isLast());
        return response;
    }

    @Override
    public RecipientMotherDTO updateRecipient(RecipientMotherDTO recipientMotherDTO, Long recipientId) {
        RecipientMother recipientMother = recipientMotherRepository.findById(recipientId)
                .orElseThrow(()->new ResourceNotFoundException("Recipient Mother","recipientId",recipientId));
        RecipientMother userRecipient = modelMapper.map(recipientMotherDTO, RecipientMother.class);
        userRecipient.setRecipientId(recipientId);
        recipientMother = recipientMotherRepository.save(userRecipient);
        return modelMapper.map(recipientMother, RecipientMotherDTO.class);
    }

    @Override
    public RecipientMotherDTO deleteRecipient(Long recipientId) {
        RecipientMother recipientMother = recipientMotherRepository.findById(recipientId)
                .orElseThrow(()->new ResourceNotFoundException("Recipient Mother","recipientId",recipientId));
        recipientMotherRepository.delete(recipientMother);
        return modelMapper.map(recipientMother, RecipientMotherDTO.class);
    }
}
