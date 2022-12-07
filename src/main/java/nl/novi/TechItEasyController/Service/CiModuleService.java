package nl.novi.TechItEasyController.Service;

import nl.novi.TechItEasyController.Dto.Output.CiModuleDto;
import nl.novi.TechItEasyController.Dto.Input.CiModuleInputDto;
import nl.novi.TechItEasyController.Exceptions.RecordNotFoundException;
import nl.novi.TechItEasyController.Models.CiModule;
import nl.novi.TechItEasyController.Models.RemoteController;
import nl.novi.TechItEasyController.Models.Television;
import nl.novi.TechItEasyController.Repositorys.CiModuleRepository;
import nl.novi.TechItEasyController.Repositorys.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CiModuleService {

    private static CiModuleRepository ciModuleRepository;
    private static TelevisionRepository televisionRepository;

    public CiModuleService(CiModuleRepository ciModuleRepository, TelevisionRepository televisionRepository) {
        this.ciModuleRepository = ciModuleRepository;
        this.televisionRepository = televisionRepository;
    }


    public CiModuleDto transferToCiModuleDto(CiModule ciModule) {
        CiModuleDto dto = new CiModuleDto();

        dto.setType(ciModule.getType());
        dto.setName(ciModule.getName());
        dto.setPrice(ciModule.getPrice());
        dto.setTelevision(ciModule.getTelevision());
        return dto;
    }


    public Long createCiModule(CiModuleDto ciModuleDto) {
        CiModule newCiModule = new CiModule();

        newCiModule.setType(ciModuleDto.getType());
        newCiModule.setName(ciModuleDto.getName());
        newCiModule.setPrice(ciModuleDto.getPrice());


        CiModule savedCiModule = ciModuleRepository.save(newCiModule);
        return savedCiModule.getId();
    }

    public Iterable<CiModuleDto> getCiModules() {
        ArrayList<CiModuleDto> ciModuleDtoList = new ArrayList<>();

        Iterable<CiModule> allCiModules = ciModuleRepository.findAll();
        for (CiModule ciModule : allCiModules) {
            ciModuleDtoList.add(transferToCiModuleDto(ciModule));
        }
        return ciModuleDtoList;
    }

    public CiModuleDto getOneCiModule(Long id) {
        Optional<CiModule> ciModule = ciModuleRepository.findById(id);

        if (ciModule.isEmpty()) {
            throw new RecordNotFoundException("No ci-module found with id: " + id);

        } else {
            return transferToCiModuleDto(ciModule.get());
        }
    }

    public String deleteCiModule(Long id) {
        Optional<CiModule> deleteCiModule = ciModuleRepository.findById(id);

        if (deleteCiModule.isEmpty()) {
            throw new RecordNotFoundException("No ci-module found with id: " + id);
        } else {
            ciModuleRepository.deleteById(id);
            return "Ci-module with id: " + id + " is deleted!";
        }
    }

    public CiModuleDto overrideCiModule(Long id, CiModuleInputDto ciModuleInputDto) {
        Optional<CiModule> toOverrideCiModule = ciModuleRepository.findById(id);

        if (toOverrideCiModule.isEmpty()) {
            throw new RecordNotFoundException("No ci-module found with id: " + id);
        } else {

            CiModule updateCiModule = toOverrideCiModule.get();

            updateCiModule.setType(ciModuleInputDto.getType());
            updateCiModule.setName(ciModuleInputDto.getName());
            updateCiModule.setPrice(ciModuleInputDto.getPrice());

            ciModuleRepository.save(updateCiModule);
            return transferToCiModuleDto(updateCiModule);
        }
    }

    public static void assignTelevisionToCiModule(Long id, Long televisionId) {
        Optional<CiModule> optionalCiModule = ciModuleRepository.findById(id);
        Optional<Television> optionalTelevision = televisionRepository.findById(televisionId);
        if (optionalTelevision.isPresent() && optionalCiModule.isPresent()) {
            CiModule ciModule = optionalCiModule.get();
            Television television = optionalTelevision.get();
            ciModule.setTelevision(television);
            ciModuleRepository.save(ciModule);
        } else {
            throw new RecordNotFoundException();
        }
    }


}
