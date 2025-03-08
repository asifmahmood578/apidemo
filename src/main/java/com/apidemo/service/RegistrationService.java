package com.apidemo.service;

import com.apidemo.entity.Regstration;
import com.apidemo.exceptions.ResourceNotFound;
import com.apidemo.payload.RegistrationDto;
import com.apidemo.repository.RegstrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    private RegstrationRepository regstrationRepository;

    private ModelMapper modelMapper;


    public RegistrationService(RegstrationRepository regstrationRepository, ModelMapper modelMapper) {
        this.regstrationRepository = regstrationRepository;
        this.modelMapper = modelMapper;
    }

    public RegistrationDto createRegistration(RegistrationDto registrationDto){
        Regstration regstration = convertToEntity(registrationDto);
        Regstration savedregistration = regstrationRepository.save(regstration);
        return convertToDto(savedregistration);

    }

    Regstration convertToEntity(RegistrationDto registrationDto){

        Regstration reg = modelMapper.map(registrationDto, Regstration.class);
//        Regstration regstration=new Regstration();
//        regstration.setEmailId(registrationDto.getEmailId());
//        regstration.setName(registrationDto.getName());
//        regstration.setMobile(registrationDto.getMobile());

        return reg;
    }

    RegistrationDto convertToDto(Regstration registration){
        // copy regstration to dto

       RegistrationDto dto = modelMapper.map(registration, RegistrationDto.class);

//        RegistrationDto registrationDto = new RegistrationDto();
//        registrationDto.setId(registration.getId());
//        registrationDto.setName(registration.getName());
//        registrationDto.setEmailId(registration.getEmailId());
//        registrationDto.setMobile(registration.getMobile());

        return dto;

    }

    public void deleteRegistration(long id) {
        regstrationRepository.deleteById(id);
    }

    public void updateRegistration(long id, RegistrationDto registrationDto) {
        Optional<Regstration> opReg= regstrationRepository.findById(id);
        if(opReg.isPresent()){
            Regstration reg = opReg.get();
            reg.setName(registrationDto.getName());
            reg.setEmailId(registrationDto.getEmailId());
            reg.setMobile(registrationDto.getMobile());
            regstrationRepository.save(reg);
        }
    }

    public List<RegistrationDto> getAllRegistrations(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sorts = sortDir.equalsIgnoreCase("asc") ? Sort.by(Sort.Order.asc(sortBy)) : Sort.by(Sort.Order.desc(sortBy));
        Pageable page = PageRequest.of(pageNo, pageSize,sorts);
        Page<Regstration> all = regstrationRepository.findAll(page);
        List<Regstration> allregistration = all.getContent();
        System.out.println(page.getPageNumber());
        System.out.println(page.getPageSize());
        System.out.println(all.getTotalPages());
        System.out.println(all.isLast());
        System.out.println(all.isFirst());
        return allregistration.stream().map(reg->convertToDto(reg)).collect(Collectors.toList());

    }

    public Regstration getRegistrationById(long id){
            Regstration reg = regstrationRepository.findById(id).orElseThrow(()->new ResourceNotFound("Resource not found"));
            return reg;
    }
}
