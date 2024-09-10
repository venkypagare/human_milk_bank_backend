package com.web.human_milk_bank.serviceImpl;

import com.web.human_milk_bank.exceptions.APIException;
import com.web.human_milk_bank.exceptions.ResourceNotFoundException;
import com.web.human_milk_bank.models.Culture;
import com.web.human_milk_bank.payload.CultureDTO;
import com.web.human_milk_bank.payload.CultureResponse;
import com.web.human_milk_bank.repositories.CultureRepository;
import com.web.human_milk_bank.service.CultureService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CultureServiceImpl implements CultureService {
    @Autowired
    private CultureRepository cultureRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CultureDTO createCulture(CultureDTO cultureDTO) {
        Culture culture = modelMapper.map(cultureDTO, Culture.class);
        Culture cultureSaved = cultureRepository.save(culture);
        return modelMapper.map(cultureSaved, CultureDTO.class);
    }

    @Override
    public CultureResponse getAllCultures(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Culture> cultures = cultureRepository.findAll(pageable);
        List<Culture> cultureList = cultures.getContent();
        List<CultureDTO> dtoList = cultureList.stream()
                .map(culture -> modelMapper.map(culture, CultureDTO.class))
                .toList();
        if(dtoList.isEmpty())
            throw new APIException("No culture found");
        CultureResponse cultureResponse = new CultureResponse();
        cultureResponse.setContent(dtoList);
        cultureResponse.setPageNumber(cultures.getNumber());
        cultureResponse.setPageSize(cultures.getSize());
        cultureResponse.setTotalPages(cultures.getTotalPages());
        cultureResponse.setTotalElements(cultures.getTotalElements());
        cultureResponse.setLastPage(cultures.isLast());
        return cultureResponse;
    }

    @Override
    public CultureDTO updateCulture(CultureDTO cultureDTO, Long cultureId) {
        Culture savedCulture = cultureRepository.findById(cultureId)
                .orElseThrow(()->new ResourceNotFoundException("Culture", "cultureId", cultureId));
        Culture culture = modelMapper.map(cultureDTO, Culture.class);
        culture.setCultureId(cultureDTO.getCultureId());
        savedCulture = cultureRepository.save(culture);
        return modelMapper.map(savedCulture, CultureDTO.class);
    }

    @Override
    public CultureDTO deleteCulture(Long cultureId) {
        Culture culture = cultureRepository.findById(cultureId)
                .orElseThrow(()-> new ResourceNotFoundException("Culture", "cultureId", cultureId));
        cultureRepository.delete(culture);
        return modelMapper.map(culture, CultureDTO.class);
    }
}
