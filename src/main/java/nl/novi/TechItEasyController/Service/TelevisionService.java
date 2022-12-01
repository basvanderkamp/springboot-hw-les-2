package nl.novi.TechItEasyController.Service;


import nl.novi.TechItEasyController.Dto.TelevisionDto;
import nl.novi.TechItEasyController.Exceptions.RecordNotFoundException;
import nl.novi.TechItEasyController.Models.Television;
import nl.novi.TechItEasyController.Repositorys.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TelevisionService {

    private final TelevisionRepository repos;

    public TelevisionService(TelevisionRepository repos) {
        this.repos = repos;
    }


    public TelevisionDto transferToTelevisionDto(Television television) {
        TelevisionDto dto = new TelevisionDto();

        dto.setType(television.getType());
        dto.setBrand(television.getBrand());
        dto.setName(television.getName());
        dto.setPrice(television.getPrice());
        dto.setAvailableSize(television.getAvailableSize());
        dto.setRefreshRate(television.getRefreshRate());
        dto.setScreenType(television.getScreenType());
        dto.setScreenQuality(television.getScreenQuality());
        dto.setSmartTv(television.isSmartTv());
        dto.setWifi(television.isWifi());
        dto.setVoiceControl(television.isVoiceControl());
        dto.setHdr(television.isHdr());
        dto.setBluetooth(television.isBluetooth());
        dto.setAmbiLight(television.isAmbiLight());
        dto.setOriginalStock(television.getOriginalStock());
        dto.setSold(television.getSold());
        return dto;
    }

    public Long createTelevision(TelevisionDto televisionDto) {
        Television newTelevision = new Television();

        newTelevision.setType(televisionDto.getType());
        newTelevision.setBrand(televisionDto.getBrand());
        newTelevision.setName(televisionDto.getName());
        newTelevision.setPrice(televisionDto.getPrice());
        newTelevision.setAvailableSize(televisionDto.getAvailableSize());
        newTelevision.setRefreshRate(televisionDto.getRefreshRate());
        newTelevision.setScreenType(televisionDto.getScreenType());
        newTelevision.setScreenQuality(televisionDto.getScreenQuality());
        newTelevision.setSmartTv(televisionDto.isSmartTv());
        newTelevision.setWifi(televisionDto.isWifi());
        newTelevision.setVoiceControl(televisionDto.isVoiceControl());
        newTelevision.setHdr(televisionDto.isHdr());
        newTelevision.setBluetooth(televisionDto.isBluetooth());
        newTelevision.setAmbiLight(televisionDto.isAmbiLight());
        newTelevision.setOriginalStock(televisionDto.getOriginalStock());
        newTelevision.setSold(televisionDto.getSold());

        Television savedTelevision = repos.save(newTelevision);
        return savedTelevision.getId();
    }

    public Iterable<TelevisionDto> getTelevisions() {
        ArrayList<TelevisionDto> televisionDtoList = new ArrayList<>();

        Iterable<Television> allTelevisions = repos.findAll();
        for (Television television : allTelevisions) {
            televisionDtoList.add(transferToTelevisionDto(television));
        }
        return televisionDtoList;
    }

    public TelevisionDto getOneTelevision(Long id) {
        Optional<Television> television = repos.findById(id);

        if (television.isEmpty()) {
            throw new RecordNotFoundException("No television found with id: " + id);

        } else {
            return transferToTelevisionDto(television.get());
        }
    }

    public String deleteTelevision(Long id) {
        Optional<Television> deleteTelevision = repos.findById(id);

        if (deleteTelevision.isEmpty()) {
            throw new RecordNotFoundException("No television found with id: " + id);
        } else {
            repos.deleteById(id);
            return "Tv with id: " + id + " is deleted!";
        }
    }

    public TelevisionDto overrideTelevision(Long id, TelevisionDto televisionDto) {
        Optional<Television> toOverrideTelevision = repos.findById(id);

        if (toOverrideTelevision.isEmpty()) {
            throw new RecordNotFoundException("No television found with id: " + id);
        } else {

            Television updateTelevision = toOverrideTelevision.get();

            updateTelevision.setType(televisionDto.getType());
            updateTelevision.setBrand(televisionDto.getBrand());
            updateTelevision.setName(televisionDto.getName());
            updateTelevision.setPrice(televisionDto.getPrice());
            updateTelevision.setAvailableSize(televisionDto.getAvailableSize());
            updateTelevision.setRefreshRate(televisionDto.getRefreshRate());
            updateTelevision.setScreenType(televisionDto.getScreenType());
            updateTelevision.setScreenQuality(televisionDto.getScreenQuality());
            updateTelevision.setSmartTv(televisionDto.isSmartTv());
            updateTelevision.setWifi(televisionDto.isWifi());
            updateTelevision.setVoiceControl(televisionDto.isVoiceControl());
            updateTelevision.setHdr(televisionDto.isHdr());
            updateTelevision.setBluetooth(televisionDto.isBluetooth());
            updateTelevision.setAmbiLight(televisionDto.isAmbiLight());
            updateTelevision.setOriginalStock(televisionDto.getOriginalStock());
            updateTelevision.setSold(televisionDto.getSold());

            repos.save(updateTelevision);
            return transferToTelevisionDto(updateTelevision);
        }
    }


}
