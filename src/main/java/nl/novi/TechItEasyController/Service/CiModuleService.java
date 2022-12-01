package nl.novi.TechItEasyController.Service;

import nl.novi.TechItEasyController.Dto.CiModuleDto;
import nl.novi.TechItEasyController.Dto.TelevisionDto;
import nl.novi.TechItEasyController.Exceptions.RecordNotFoundException;
import nl.novi.TechItEasyController.Models.CiModule;
import nl.novi.TechItEasyController.Models.Television;
import nl.novi.TechItEasyController.Repositorys.CiModuleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CiModuleService {

    private final CiModuleRepository repos;

    public CiModuleService(CiModuleRepository repos) {
        this.repos = repos;
    }


    public CiModuleDto transferToCiModuleDto(CiModule ciModule) {
        CiModuleDto dto = new CiModuleDto();

        dto.setType(ciModule.getType());
        dto.setName(ciModule.getName());
        dto.setPrice(ciModule.getPrice());
        return dto;
    }


    public Long createCiModule(CiModuleDto ciModuleDto) {
        CiModule newCiModule = new CiModule();

        newCiModule.setType(ciModuleDto.getType());
        newCiModule.setName(ciModuleDto.getName());
        newCiModule.setPrice(ciModuleDto.getPrice());


        CiModule savedCiModule = repos.save(newCiModule);
        return savedCiModule.getId();
    }

    public Iterable<CiModuleDto> getCiModules() {
        ArrayList<CiModuleDto> ciModuleDtoList = new ArrayList<>();

        Iterable<CiModule> allCiModules = repos.findAll();
        for (CiModule ciModule : allCiModules) {
            ciModuleDtoList.add(transferToCiModuleDto(ciModule));
        }
        return ciModuleDtoList;
    }

    public CiModuleDto getOneCiModule(Long id) {
        Optional<CiModule> ciModule = repos.findById(id);

        if (ciModule.isEmpty()) {
            throw new RecordNotFoundException("No ci-module found with id: " + id);

        } else {
            return transferToCiModuleDto(ciModule.get());
        }
    }

    public String deleteCiModule(Long id) {
        Optional<CiModule> deleteCiModule = repos.findById(id);

        if (deleteCiModule.isEmpty()) {
            throw new RecordNotFoundException("No ci-module found with id: " + id);
        } else {
            repos.deleteById(id);
            return "Ci-module with id: " + id + " is deleted!";
        }
    }

    public CiModuleDto overrideCiModule(Long id, CiModuleDto ciModuleDto) {
        Optional<CiModule> toOverrideCiModule = repos.findById(id);

        if (toOverrideCiModule.isEmpty()) {
            throw new RecordNotFoundException("No ci-module found with id: " + id);
        } else {

            CiModule updateCiModule = toOverrideCiModule.get();

            updateCiModule.setType(ciModuleDto.getType());
            updateCiModule.setName(ciModuleDto.getName());
            updateCiModule.setPrice(ciModuleDto.getPrice());

            repos.save(updateCiModule);
            return transferToCiModuleDto(updateCiModule);
        }
    }
}
