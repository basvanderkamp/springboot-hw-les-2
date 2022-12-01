package nl.novi.TechItEasyController.Service;

import nl.novi.TechItEasyController.Dto.RemoteControllerDto;
import nl.novi.TechItEasyController.Dto.TelevisionDto;
import nl.novi.TechItEasyController.Exceptions.RecordNotFoundException;
import nl.novi.TechItEasyController.Models.RemoteController;
import nl.novi.TechItEasyController.Models.Television;
import nl.novi.TechItEasyController.Repositorys.RemoteControllerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RemoteControllerService {

    private final RemoteControllerRepository repos;

    public RemoteControllerService(RemoteControllerRepository repos) {
        this.repos = repos;
    }


    public RemoteControllerDto transferToRemoteControllerDto(RemoteController remoteController) {
        RemoteControllerDto dto = new RemoteControllerDto();

        dto.setBatteryType(remoteController.getBatteryType());
        dto.setBrand(remoteController.getBrand());
        dto.setCompatibleWith(remoteController.getCompatibleWith());
        dto.setPrice(remoteController.getPrice());
        dto.setOriginalStock(remoteController.getOriginalStock());
        return dto;
    }

    public Long createRemoteController(RemoteControllerDto remoteControllerDto) {
        RemoteController newRemoteController = new RemoteController();

        newRemoteController.setBatteryType(remoteControllerDto.getBatteryType());
        newRemoteController.setBrand(remoteControllerDto.getBrand());
        newRemoteController.setCompatibleWith(remoteControllerDto.getCompatibleWith());
        newRemoteController.setPrice(remoteControllerDto.getPrice());
        newRemoteController.setOriginalStock(newRemoteController.getOriginalStock());

        RemoteController savedRemoteController = repos.save(newRemoteController);
        return savedRemoteController.getId();
    }

    public Iterable<RemoteControllerDto> getRemoteControllers() {
        ArrayList<RemoteControllerDto> remoteControllerDtoList = new ArrayList<>();

        Iterable<RemoteController> allRemoteControllers = repos.findAll();
        for (RemoteController remoteController : allRemoteControllers) {
            remoteControllerDtoList.add(transferToRemoteControllerDto(remoteController));
        }
        return remoteControllerDtoList;
    }

    public RemoteControllerDto getOneRemoteController(Long id) {
        Optional<RemoteController> remoteController = repos.findById(id);

        if (remoteController.isEmpty()) {
            throw new RecordNotFoundException("No remote found with id: " + id);

        } else {
            return transferToRemoteControllerDto(remoteController.get());
        }
    }

    public String deleteRemoteController(Long id) {
        Optional<RemoteController> deleteRemoteController = repos.findById(id);

        if (deleteRemoteController.isEmpty()) {
            throw new RecordNotFoundException("No remote found with id: " + id);
        } else {
            repos.deleteById(id);
            return "Remote with id: " + id + " is deleted!";
        }
    }

    public RemoteControllerDto overrideRemoteController(Long id, RemoteControllerDto remoteControllerDto) {
        Optional<RemoteController> toOverrideRemoteController = repos.findById(id);

        if (toOverrideRemoteController.isEmpty()) {
            throw new RecordNotFoundException("No remote found with id: " + id);
        } else {

            RemoteController updateRemoteController = toOverrideRemoteController.get();

            updateRemoteController.setBatteryType(remoteControllerDto.getBatteryType());
            updateRemoteController.setBrand(remoteControllerDto.getBrand());
            updateRemoteController.setCompatibleWith(remoteControllerDto.getCompatibleWith());
            updateRemoteController.setPrice(remoteControllerDto.getPrice());
            updateRemoteController.setOriginalStock(remoteControllerDto.getOriginalStock());

            repos.save(updateRemoteController);
            return transferToRemoteControllerDto(updateRemoteController);
        }
    }
}
