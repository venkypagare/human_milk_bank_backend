package com.web.human_milk_bank.serviceImpl;

import com.web.human_milk_bank.exceptions.APIException;
import com.web.human_milk_bank.exceptions.ResourceNotFoundException;
import com.web.human_milk_bank.models.Donor;
import com.web.human_milk_bank.payload.DonorDTO;
import com.web.human_milk_bank.payload.DonorResponse;
import com.web.human_milk_bank.repositories.DonorRepository;
import com.web.human_milk_bank.service.DonorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DonorServiceImpl implements DonorService {
    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DonorDTO createDonor(DonorDTO donorDTO) {
        Donor donor = modelMapper.map(donorDTO, Donor.class);
        Donor savedDonor = donorRepository.save(donor);
        return modelMapper.map(savedDonor, DonorDTO.class);
    }

    @Override
    public DonorResponse getAllDonors(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Donor> pageDetails = donorRepository.findAll(pageable);
        List<Donor> donorList = pageDetails.getContent();
        if(donorList.isEmpty())
            throw new APIException("No donors found");
        List<DonorDTO> dto = donorList.stream()
                .map(donor -> modelMapper.map(donor, DonorDTO.class))
                .toList();
        DonorResponse donorResponse = new DonorResponse();
        donorResponse.setContent(dto);
        donorResponse.setPageNumber(pageDetails.getNumber());
        donorResponse.setPageSize(pageDetails.getSize());
        donorResponse.setTotalElements(pageDetails.getTotalElements());
        donorResponse.setTotalPages(pageDetails.getTotalPages());
        donorResponse.setLastPage(pageDetails.isLast());
        return donorResponse;
    }

    @Override
    public DonorDTO updateDonor(DonorDTO donorDTO, Long donorId) {
        Donor savedDonor = donorRepository.findById(donorId)
                .orElseThrow(()-> new ResourceNotFoundException("Donor","donorId",donorId));
        Donor donor = modelMapper.map(donorDTO, Donor.class);
        donor.setDonorId(donorDTO.getDonorId());
        savedDonor = donorRepository.save(donor);
        return modelMapper.map(savedDonor, DonorDTO.class);
    }

    @Override
    public DonorDTO deleteDonor(Long donorId) {
        Donor donor = donorRepository.findById(donorId)
                .orElseThrow(()->new ResourceNotFoundException("Donor","donorId",donorId));
        donorRepository.delete(donor);
        return modelMapper.map(donor, DonorDTO.class);
    }
}
