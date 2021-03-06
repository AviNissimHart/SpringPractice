package com.qa.practicespring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.practicespring.dto.GuitaristDTO;
import com.qa.practicespring.exception.GuitaristNotFoundException;
import com.qa.practicespring.persistence.domain.Guitarist;
import com.qa.practicespring.persistence.repository.GuitaristRepository;
import com.qa.practicespring.utils.SpringyBeanUtils;

@Service
public class GuitaristService {

    private GuitaristRepository repo;

    private ModelMapper mapper;

    @Autowired
    public GuitaristService(GuitaristRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    private GuitaristDTO mapToDTO(Guitarist guitarist) {
        return this.mapper.map(guitarist, GuitaristDTO.class);
    }

    private Guitarist mapFromDTO(GuitaristDTO guitaristDTO) {
        return this.mapper.map(guitaristDTO, Guitarist.class);
    }

    // create
    public GuitaristDTO create(Guitarist guitarist) {
        //Guitarist toSave = this.mapFromDTO(guitarist);
        Guitarist saved = this.repo.save(guitarist);
        return this.mapToDTO(saved);
    }

    // readAll
    public List<GuitaristDTO> read() {
        List<Guitarist> found = this.repo.findAll();
        List<GuitaristDTO> streamed = found.stream().map(this::mapToDTO).collect(Collectors.toList());
        return streamed;
    }

    // readById
    public GuitaristDTO read(Long id) {
        Guitarist found = this.repo.findById(id).orElseThrow(GuitaristNotFoundException::new);
        return this.mapToDTO(found);
    }

    // update
    public GuitaristDTO update(GuitaristDTO guitaristDTO, Long id) {
        Guitarist toUpdate = this.repo.findById(id).orElseThrow(GuitaristNotFoundException::new);
        SpringyBeanUtils.mergeNotNull(guitaristDTO, toUpdate);
        return this.mapToDTO(this.repo.save(toUpdate));
    }

    // delete
    public boolean delete(Long id) {
        if (!this.repo.existsById(id)) {
            throw new GuitaristNotFoundException();
        }
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }

}
