package nl.novi.TechItEasyController.Service;

import nl.novi.TechItEasyController.Dto.Output.WallBracketDto;
import nl.novi.TechItEasyController.Dto.Input.WallBracketInputDto;
import nl.novi.TechItEasyController.Exceptions.RecordNotFoundException;
import nl.novi.TechItEasyController.Models.WallBracket;
import nl.novi.TechItEasyController.Repositorys.WallBracketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class WallBracketService {

    private final WallBracketRepository wallBracketRepository;

    public WallBracketService(WallBracketRepository wallBracketRepository) {
        this.wallBracketRepository = wallBracketRepository;
    }


    public WallBracketDto transferToWallBracketDto(WallBracket wallBracket) {
        WallBracketDto dto = new WallBracketDto();

        dto.setType(wallBracket.getType());
        dto.setSize(wallBracket.getSize());
        dto.setName(wallBracket.getName());
        dto.setPrice(wallBracket.getPrice());
        dto.setAdjustable(wallBracket.isAdjustable());
        dto.setTelevisions(wallBracket.getTelevisions());
        return dto;
    }


    public Long createWallBracket(WallBracketDto wallBracketDto) {
        WallBracket newWallBracket = new WallBracket();

        newWallBracket.setType(wallBracketDto.getType());
        newWallBracket.setSize(wallBracketDto.getSize());
        newWallBracket.setName(wallBracketDto.getName());
        newWallBracket.setPrice(wallBracketDto.getPrice());
        newWallBracket.setAdjustable(wallBracketDto.isAdjustable());


        WallBracket savedWallBracket = wallBracketRepository.save(newWallBracket);
        return savedWallBracket.getId();
    }

    public Iterable<WallBracketDto> getWallBrackets() {
        ArrayList<WallBracketDto> wallBracketDtoList = new ArrayList<>();

        Iterable<WallBracket> allWallBrackets = wallBracketRepository.findAll();
        for (WallBracket wallBracket : allWallBrackets) {
            wallBracketDtoList.add(transferToWallBracketDto(wallBracket));
        }
        return wallBracketDtoList;
    }

    public WallBracketDto getOneWallBracket(Long id) {
        Optional<WallBracket> wallBracket = wallBracketRepository.findById(id);

        if (wallBracket.isEmpty()) {
            throw new RecordNotFoundException("No wall bracket found with id: " + id);

        } else {
            return transferToWallBracketDto(wallBracket.get());
        }
    }

    public String deleteWallBracket(Long id) {
        Optional<WallBracket> deleteWallBracket = wallBracketRepository.findById(id);

        if (deleteWallBracket.isEmpty()) {
            throw new RecordNotFoundException("No wall bracket found with id: " + id);
        } else {
            wallBracketRepository.deleteById(id);
            return "wall bracket with id: " + id + " is deleted!";
        }
    }

    public WallBracketDto overrideWallBracket(Long id, WallBracketInputDto wallBracketInputDto) {
        Optional<WallBracket> toOverrideWallBracket = wallBracketRepository.findById(id);

        if (toOverrideWallBracket.isEmpty()) {
            throw new RecordNotFoundException("No wall bracket found with id: " + id);
        } else {

            WallBracket updateWallBracket = toOverrideWallBracket.get();

            updateWallBracket.setType(wallBracketInputDto.getType());
            updateWallBracket.setSize(wallBracketInputDto.getSize());
            updateWallBracket.setName(wallBracketInputDto.getName());
            updateWallBracket.setPrice(wallBracketInputDto.getPrice());
            updateWallBracket.setAdjustable(wallBracketInputDto.isAdjustable());


            wallBracketRepository.save(updateWallBracket);
            return transferToWallBracketDto(updateWallBracket);
        }
    }
}
