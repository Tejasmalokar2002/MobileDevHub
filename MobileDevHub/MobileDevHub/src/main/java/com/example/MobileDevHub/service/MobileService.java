package com.example.MobileDevHub.service;

import com.example.MobileDevHub.dto.MobileDTO;
import com.example.MobileDevHub.entity.Manufacturer;
import com.example.MobileDevHub.entity.Mobile;
import com.example.MobileDevHub.mapper.MobileMapper;
import com.example.MobileDevHub.repository.ManufacturesRepository;
import com.example.MobileDevHub.repository.MobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MobileService {

    @Autowired
    private MobileRepository mobileRepository;

    @Autowired
    private ManufacturesRepository manufacturesRepository;

    @Autowired
    private MobileMapper mobileMapper;

    public List<MobileDTO> findAll() {
        return mobileRepository.findAll().stream()
                .map(mobileMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<MobileDTO> findById(Long id) {
        return mobileRepository.findById(id)
                .map(mobileMapper::toDTO);
    }

    public MobileDTO saveMobile(MobileDTO mobileDTO) {
        Mobile mobile = mobileMapper.toEntity(mobileDTO);

        if (mobileDTO.getManufacturerId() != null) {
            Manufacturer manufacturer = manufacturesRepository.findById(mobileDTO.getManufacturerId())
                    .orElseThrow(() -> new RuntimeException("Manufacturer not found"));
            mobile.setManufacturer(manufacturer);
        }

        Mobile savedMobile = mobileRepository.save(mobile);
        return mobileMapper.toDTO(savedMobile);
    }

    public Optional<MobileDTO> update(Long id, MobileDTO mobileDTO) {
        if (mobileRepository.existsById(id)) {
            Mobile mobile = mobileMapper.toEntity(mobileDTO);

            if (mobileDTO.getManufacturerId() != null) {
                Manufacturer manufacturer = manufacturesRepository.findById(mobileDTO.getManufacturerId())
                        .orElseThrow(() -> new RuntimeException("Manufacturer not found"));
                mobile.setManufacturer(manufacturer);
            }

            mobile.setMobile_id(id);
            Mobile updatedMobile = mobileRepository.save(mobile);
            return Optional.of(mobileMapper.toDTO(updatedMobile));
        }
        return Optional.empty();
    }

    public void deleteById(Long id) {
        mobileRepository.deleteById(id);
    }
}
