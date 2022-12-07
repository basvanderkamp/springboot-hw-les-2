package nl.novi.TechItEasyController.Service;


import nl.novi.TechItEasyController.Dto.Output.TelevisionDto;
import nl.novi.TechItEasyController.Dto.Input.TelevisionInputDto;
import nl.novi.TechItEasyController.Exceptions.RecordNotFoundException;
import nl.novi.TechItEasyController.Models.CiModule;
import nl.novi.TechItEasyController.Models.RemoteController;
import nl.novi.TechItEasyController.Models.Television;
import nl.novi.TechItEasyController.Repositorys.CiModuleRepository;
import nl.novi.TechItEasyController.Repositorys.RemoteControllerRepository;
import nl.novi.TechItEasyController.Repositorys.TelevisionRepository;
import nl.novi.TechItEasyController.Repositorys.WallBracketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {

    private static TelevisionRepository televisionRepository = null;
    private static RemoteControllerRepository remoteControllerRepository = null;
    private static CiModuleRepository ciModuleRepository = null;
    private final WallBracketRepository wallBracketRepository;
    public TelevisionService(TelevisionRepository televisionRepository, RemoteControllerRepository remoteControllerRepository, CiModuleRepository ciModuleRepository, WallBracketRepository wallBracketRepository) {
        this.televisionRepository = televisionRepository;
        this.remoteControllerRepository = remoteControllerRepository;
        this.ciModuleRepository = ciModuleRepository;
        this.wallBracketRepository = wallBracketRepository;
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
        dto.setCiModules(television.getCiModules());
        dto.setRemoteController(television.getRemoteController());
        dto.setWallBrackets(television.getWallBrackets());
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

        Television savedTelevision = televisionRepository.save(newTelevision);
        return savedTelevision.getId();
    }

    public Iterable<TelevisionDto> getTelevisions() {
        ArrayList<TelevisionDto> televisionDtoList = new ArrayList<>();

        Iterable<Television> allTelevisions = televisionRepository.findAll();
        for (Television television : allTelevisions) {
            televisionDtoList.add(transferToTelevisionDto(television));
        }
        return televisionDtoList;
    }

    public TelevisionDto getOneTelevision(Long id) {
        Optional<Television> television = televisionRepository.findById(id);

        if (television.isEmpty()) {
            throw new RecordNotFoundException("No television found with id: " + id);

        } else {
            return transferToTelevisionDto(television.get());
        }
    }

    public String deleteTelevision(Long id) {
        Optional<Television> deleteTelevision = televisionRepository.findById(id);

        if (deleteTelevision.isEmpty()) {
            throw new RecordNotFoundException("No television found with id: " + id);
        } else {
            televisionRepository.deleteById(id);
            return "Tv with id: " + id + " is deleted!";
        }
    }

    public TelevisionDto overrideTelevision(Long id, TelevisionInputDto televisionInputDto) {
        Optional<Television> toOverrideTelevision = televisionRepository.findById(id);

        if (toOverrideTelevision.isEmpty()) {
            throw new RecordNotFoundException("No television found with id: " + id);
        } else {

            Television updateTelevision = toOverrideTelevision.get();

            updateTelevision.setType(televisionInputDto.getType());
            updateTelevision.setBrand(televisionInputDto.getBrand());
            updateTelevision.setName(televisionInputDto.getName());
            updateTelevision.setPrice(televisionInputDto.getPrice());
            updateTelevision.setAvailableSize(televisionInputDto.getAvailableSize());
            updateTelevision.setRefreshRate(televisionInputDto.getRefreshRate());
            updateTelevision.setScreenType(televisionInputDto.getScreenType());
            updateTelevision.setScreenQuality(televisionInputDto.getScreenQuality());
            updateTelevision.setSmartTv(televisionInputDto.isSmartTv());
            updateTelevision.setWifi(televisionInputDto.isWifi());
            updateTelevision.setVoiceControl(televisionInputDto.isVoiceControl());
            updateTelevision.setHdr(televisionInputDto.isHdr());
            updateTelevision.setBluetooth(televisionInputDto.isBluetooth());
            updateTelevision.setAmbiLight(televisionInputDto.isAmbiLight());
            updateTelevision.setOriginalStock(televisionInputDto.getOriginalStock());
            updateTelevision.setSold(televisionInputDto.getSold());

            televisionRepository.save(updateTelevision);
            return transferToTelevisionDto(updateTelevision);
        }
    }

    public static void assignRemoteControllerToTelevision(Long id, Long remoteControllerId) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        Optional<RemoteController> optionalRemoteController = remoteControllerRepository.findById(remoteControllerId);
        if (optionalTelevision.isPresent() && optionalRemoteController.isPresent()) {
            Television television = optionalTelevision.get();
            RemoteController remoteController = optionalRemoteController.get();
            television.setRemoteController(remoteController);
            televisionRepository.save(television);
        } else {
            throw new RecordNotFoundException();
        }
    }




}
