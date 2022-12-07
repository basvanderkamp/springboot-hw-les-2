package nl.novi.TechItEasyController.Service;

import nl.novi.TechItEasyController.Dto.Output.RemoteControllerDto;
import nl.novi.TechItEasyController.Dto.Input.RemoteControllerInputDto;
import nl.novi.TechItEasyController.Exceptions.RecordNotFoundException;
import nl.novi.TechItEasyController.Models.RemoteController;
import nl.novi.TechItEasyController.Repositorys.RemoteControllerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RemoteControllerService {

    private final RemoteControllerRepository remoteControllerRepository;

    public RemoteControllerService(RemoteControllerRepository remoteControllerRepository) {
        this.remoteControllerRepository = remoteControllerRepository;
    }


    public RemoteControllerDto transferToRemoteControllerDto(RemoteController remoteController) {
        RemoteControllerDto dto = new RemoteControllerDto();

        dto.setBatteryType(remoteController.getBatteryType());
        dto.setBrand(remoteController.getBrand());
        dto.setCompatibleWith(remoteController.getCompatibleWith());
        dto.setPrice(remoteController.getPrice());
        dto.setOriginalStock(remoteController.getOriginalStock());
        dto.setTelevision(remoteController.getTelevision());
        return dto;
    }

    public Long createRemoteController(RemoteControllerDto remoteControllerDto) {
        RemoteController newRemoteController = new RemoteController();

        newRemoteController.setBatteryType(remoteControllerDto.getBatteryType());
        newRemoteController.setBrand(remoteControllerDto.getBrand());
        newRemoteController.setCompatibleWith(remoteControllerDto.getCompatibleWith());
        newRemoteController.setPrice(remoteControllerDto.getPrice());
        newRemoteController.setOriginalStock(newRemoteController.getOriginalStock());

        RemoteController savedRemoteController = remoteControllerRepository.save(newRemoteController);
        return savedRemoteController.getId();
    }

    public Iterable<RemoteControllerDto> getRemoteControllers() {
        ArrayList<RemoteControllerDto> remoteControllerDtoList = new ArrayList<>();

        Iterable<RemoteController> allRemoteControllers = remoteControllerRepository.findAll();
        for (RemoteController remoteController : allRemoteControllers) {
            remoteControllerDtoList.add(transferToRemoteControllerDto(remoteController));
        }
        return remoteControllerDtoList;
    }

    public RemoteControllerDto getOneRemoteController(Long id) {
        Optional<RemoteController> remoteController = remoteControllerRepository.findById(id);

        if (remoteController.isEmpty()) {
            throw new RecordNotFoundException("No remote found with id: " + id);

        } else {
            return transferToRemoteControllerDto(remoteController.get());
        }
    }

    public String deleteRemoteController(Long id) {
        Optional<RemoteController> deleteRemoteController = remoteControllerRepository.findById(id);

        if (deleteRemoteController.isEmpty()) {
            throw new RecordNotFoundException("No remote found with id: " + id);
        } else {
            remoteControllerRepository.deleteById(id);
            return "Remote with id: " + id + " is deleted!";
        }
    }

    public RemoteControllerDto overrideRemoteController(Long id, RemoteControllerInputDto remoteControllerInputDto) {
        Optional<RemoteController> toOverrideRemoteController = remoteControllerRepository.findById(id);

        if (toOverrideRemoteController.isEmpty()) {
            throw new RecordNotFoundException("No remote found with id: " + id);
        } else {

            RemoteController updateRemoteController = toOverrideRemoteController.get();

            updateRemoteController.setBatteryType(remoteControllerInputDto.getBatteryType());
            updateRemoteController.setBrand(remoteControllerInputDto.getBrand());
            updateRemoteController.setCompatibleWith(remoteControllerInputDto.getCompatibleWith());
            updateRemoteController.setPrice(remoteControllerInputDto.getPrice());
            updateRemoteController.setOriginalStock(remoteControllerInputDto.getOriginalStock());

            remoteControllerRepository.save(updateRemoteController);
            return transferToRemoteControllerDto(updateRemoteController);
        }
    }
}
