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

    public TelevisionDto transferToDto(Television television) {
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

        newTelevision.setType(televisionDto.type);
        newTelevision.setBrand(televisionDto.brand);
        newTelevision.setName(televisionDto.name);
        newTelevision.setPrice(televisionDto.price);
        newTelevision.setAvailableSize(televisionDto.availableSize);
        newTelevision.setRefreshRate(televisionDto.refreshRate);
        newTelevision.setScreenType(televisionDto.screenType);
        newTelevision.setScreenQuality(televisionDto.screenQuality);
        newTelevision.setSmartTv(televisionDto.smartTv);
        newTelevision.setWifi(televisionDto.wifi);
        newTelevision.setVoiceControl(televisionDto.voiceControl);
        newTelevision.setHdr(televisionDto.hdr);
        newTelevision.setBluetooth(televisionDto.bluetooth);
        newTelevision.setAmbiLight(televisionDto.ambiLight);
        newTelevision.setOriginalStock(televisionDto.originalStock);
        newTelevision.setSold(televisionDto.sold);

        Television savedTelevision = repos.save(newTelevision);
        return savedTelevision.getId();
    }

    public Iterable<TelevisionDto> getTelevisions() {
        ArrayList<TelevisionDto> televisionDtoList = new ArrayList<>();

        Iterable<Television> allTelevisions = repos.findAll();
        for (Television television : allTelevisions) {
            televisionDtoList.add(transferToDto(television));
        }
        return televisionDtoList;
    }

    public TelevisionDto getOneTelevision(Long id) {
        Optional<Television> television = repos.findById(id);

        if (television.isEmpty()) {
            throw new RecordNotFoundException("No television found with id: " + id);

        } else {
            return transferToDto(television.get());
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

            updateTelevision.setType(televisionDto.type);
            updateTelevision.setBrand(televisionDto.brand);
            updateTelevision.setName(televisionDto.name);
            updateTelevision.setPrice(televisionDto.price);
            updateTelevision.setAvailableSize(televisionDto.availableSize);
            updateTelevision.setRefreshRate(televisionDto.refreshRate);
            updateTelevision.setScreenType(televisionDto.screenType);
            updateTelevision.setScreenQuality(televisionDto.screenQuality);
            updateTelevision.setSmartTv(televisionDto.smartTv);
            updateTelevision.setWifi(televisionDto.wifi);
            updateTelevision.setVoiceControl(televisionDto.voiceControl);
            updateTelevision.setHdr(televisionDto.hdr);
            updateTelevision.setBluetooth(televisionDto.bluetooth);
            updateTelevision.setAmbiLight(televisionDto.ambiLight);
            updateTelevision.setOriginalStock(televisionDto.originalStock);
            updateTelevision.setSold(televisionDto.sold);

            repos.save(updateTelevision);
            return transferToDto(updateTelevision);


        }


    }

}
