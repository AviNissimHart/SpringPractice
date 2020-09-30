package com.qa.practicespring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.practicespring.dto.BandDTO;
import com.qa.practicespring.exception.BandNotFoundException;
import com.qa.practicespring.persistence.domain.Band;
import com.qa.practicespring.persistence.repository.BandRepository;
import com.qa.practicespring.utils.SpringyBeanUtils;

@Service
public class BandService {

    private BandRepository repo;

    private ModelMapper mapper;

    @Autowired
    public BandService(BandRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    private BandDTO mapToDTO(Band band) {
        return this.mapper.map(band, BandDTO.class);
    }

    private Band mapFromDTO(BandDTO bandDTO) {
        return this.mapper.map(bandDTO, Band.class);
    }

    public BandDTO create(Band band) {
       // Band toSave = this.mapFromDTO(bandDTO);
        Band saved = this.repo.save(band);
        return this.mapToDTO(saved);
    }

    public List<BandDTO> read() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public BandDTO read(Long id) {
        Band found = this.repo.findById(id).orElseThrow(BandNotFoundException::new);
        return this.mapToDTO(found);
    }

    public BandDTO update(BandDTO bandDTO, Long id) {
        Band toUpdate = this.repo.findById(id).orElseThrow(BandNotFoundException::new);
        SpringyBeanUtils.mergeNotNull(bandDTO, toUpdate);
        return this.mapToDTO(toUpdate);
    }

    public boolean delete(Long id) {
        if (!this.repo.existsById(id)) {
            throw new BandNotFoundException();
        }
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }

}