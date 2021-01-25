package com.zka.lyceena.services;

import com.zka.lyceena.constants.CacheNames;
import com.zka.lyceena.dao.ClassLevelRefJpaRepository;
import com.zka.lyceena.dao.LevelMaterialNumberOfHoursJpaRepository;
import com.zka.lyceena.dao.MaterialRefJpaRepository;
import com.zka.lyceena.dto.ClassLevelRefDto;
import com.zka.lyceena.dto.LevelMaterialNumberOfHoursDto;
import com.zka.lyceena.dto.MaterialDto;
import com.zka.lyceena.dto.SaveMaterialToClassLevelDto;
import com.zka.lyceena.entities.material.LevelMaterialNumberOfHours;
import com.zka.lyceena.entities.material.Material;
import com.zka.lyceena.entities.ref.ClassLevelRef;
import com.zka.lyceena.mappers.ClassLevelRefMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClassLevelRefServiceImpl implements ClassLevelRefService {

    @Autowired
    private ClassLevelRefJpaRepository classLevelRefJpaRepository;

    @Autowired
    private LevelMaterialNumberOfHoursJpaRepository levelMaterialNumberOfHoursJpaRepository;

    @Autowired
    private MaterialRefJpaRepository materialRefJpaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Cacheable(CacheNames.LEVELS)
    @Override
    public List<ClassLevelRefDto> findAll() {
        return this.classLevelRefJpaRepository
                .findAll(Sort.by(Sort.Direction.ASC, "name"))
                .stream().map(ClassLevelRefMapper::map)
                .collect(Collectors.toList());
    }

    @CacheEvict(value=CacheNames.LEVELS, allEntries=true)
    @Override
    public void save(ClassLevelRefDto dto) {
        ClassLevelRef entity = this.classLevelRefJpaRepository.findById(dto.getId()).orElse(new ClassLevelRef());
        entity.setName(dto.getName());
        this.classLevelRefJpaRepository.save(entity);
    }

    @CacheEvict(value=CacheNames.LEVELS, allEntries=true)
    @Override
    public void deleteById(Integer id) {
        this.classLevelRefJpaRepository.deleteById(id);
    }

    @Override
    public ClassLevelRefDto findById(Integer id) {
        ClassLevelRef levelEntity = this.classLevelRefJpaRepository.findById(id).get();
        return this.modelMapper.map(levelEntity, ClassLevelRefDto.class);
    }

    @Override
    public List<LevelMaterialNumberOfHoursDto> findMaterialsByClassLevelId(Integer id) {
        return levelMaterialNumberOfHoursJpaRepository.findByLevelId(id)
                .stream()
                .map(m-> modelMapper.map(m, LevelMaterialNumberOfHoursDto.class))
                .collect(Collectors.toList());
    }

    @CacheEvict(value=CacheNames.LEVELS, allEntries=true)
    @Override
    public void deleteClassLevelMaterialByClassIdMaterialId(Integer levelId, String materialId) {
        ClassLevelRef classLevelRef = this.classLevelRefJpaRepository.findById(levelId).get();
        Material material = classLevelRef.getMaterials().stream().filter(m->m.getId().equals(materialId)).findAny().get();
        classLevelRef.getMaterials().remove(material);
        LevelMaterialNumberOfHours numberOfHours = this.levelMaterialNumberOfHoursJpaRepository.findFirstByLevelIdAndMaterialId(levelId, materialId).get();

        this.classLevelRefJpaRepository.save(classLevelRef);
        this.levelMaterialNumberOfHoursJpaRepository.delete(numberOfHours);

    }

    @CacheEvict(value=CacheNames.LEVELS, allEntries=true)
    @Override
    public void addMaterialToClassLevel(Integer levelId, SaveMaterialToClassLevelDto saveMaterialToClassLevelDto) {
        ClassLevelRef classLevelRef = this.classLevelRefJpaRepository.findById(levelId).get();
        Material material = this.materialRefJpaRepository.findById(saveMaterialToClassLevelDto.getMaterialId()).get();

        if(classLevelRef.getMaterials().stream().filter(c-> c.getId().equals(material.getId())).count() == 0){
            classLevelRef.getMaterials().add(material);
            this.classLevelRefJpaRepository.save(classLevelRef);
        }

        Optional<LevelMaterialNumberOfHours> levelMatNbHoursExist = this.levelMaterialNumberOfHoursJpaRepository.findFirstByLevelIdAndMaterialId(levelId, saveMaterialToClassLevelDto.getMaterialId());
        if(!levelMatNbHoursExist.isPresent()){
            LevelMaterialNumberOfHours nbhl = new LevelMaterialNumberOfHours();
            nbhl.setMaterial(material);
            nbhl.setClassLevelRef(classLevelRef);
            nbhl.setHourNumberPerWeek(saveMaterialToClassLevelDto.getNumberOfHours());
            this.levelMaterialNumberOfHoursJpaRepository.save(nbhl);
        } else {
            levelMatNbHoursExist.get().setHourNumberPerWeek(saveMaterialToClassLevelDto.getNumberOfHours());
            this.levelMaterialNumberOfHoursJpaRepository.save(levelMatNbHoursExist.get());
        }
    }
}
