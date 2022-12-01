package nl.novi.TechItEasyController.Service;

import nl.novi.TechItEasyController.Dto.TelevisionDto;
import nl.novi.TechItEasyController.Dto.WallBracketDto;
import nl.novi.TechItEasyController.Exceptions.RecordNotFoundException;
import nl.novi.TechItEasyController.Models.Television;
import nl.novi.TechItEasyController.Models.WallBracket;
import nl.novi.TechItEasyController.Repositorys.WallBracketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class WallBracketService {

    private final WallBracketRepository repos;

    public WallBracketService(WallBracketRepository repos) {
        this.repos = repos;
    }


    public WallBracketDto transferToWallBracketDto(WallBracket wallBracket) {
        WallBracketDto dto = new WallBracketDto();

        dto.setType(wallBracket.getType());
        dto.setSize(wallBracket.getSize());
        dto.setName(wallBracket.getName());
        dto.setPrice(wallBracket.getPrice());
        dto.setAdjustable(wallBracket.isAdjustable());
        return dto;
    }


    public Long createWallBracket(WallBracketDto wallBracketDto) {
        WallBracket newWallBracket = new WallBracket();

        newWallBracket.setType(wallBracketDto.getType());
        newWallBracket.setSize(wallBracketDto.getSize());
        newWallBracket.setName(wallBracketDto.getName());
        newWallBracket.setPrice(wallBracketDto.getPrice());
        newWallBracket.setAdjustable(wallBracketDto.isAdjustable());


        WallBracket savedWallBracket = repos.save(newWallBracket);
        return savedWallBracket.getId();
    }

    public Iterable<WallBracketDto> getWallBrackets() {
        ArrayList<WallBracketDto> wallBracketDtoList = new ArrayList<>();

        Iterable<WallBracket> allWallBrackets = repos.findAll();
        for (WallBracket wallBracket : allWallBrackets) {
            wallBracketDtoList.add(transferToWallBracketDto(wallBracket));
        }
        return wallBracketDtoList;
    }

    public WallBracketDto getOneWallBracket(Long id) {
        Optional<WallBracket> wallBracket = repos.findById(id);

        if (wallBracket.isEmpty()) {
            throw new RecordNotFoundException("No wall bracket found with id: " + id);

        } else {
            return transferToWallBracketDto(wallBracket.get());
        }
    }

    public String deleteWallBracket(Long id) {
        Optional<WallBracket> deleteWallBracket = repos.findById(id);

        if (deleteWallBracket.isEmpty()) {
            throw new RecordNotFoundException("No wall bracket found with id: " + id);
        } else {
            repos.deleteById(id);
            return "wall bracket with id: " + id + " is deleted!";
        }
    }

    public WallBracketDto overrideWallBracket(Long id, WallBracketDto wallBracketDto) {
        Optional<WallBracket> toOverrideWallBracket = repos.findById(id);

        if (toOverrideWallBracket.isEmpty()) {
            throw new RecordNotFoundException("No wall bracket found with id: " + id);
        } else {

            WallBracket updateWallBracket = toOverrideWallBracket.get();

            updateWallBracket.setType(wallBracketDto.getType());
            updateWallBracket.setSize(wallBracketDto.getSize());
            updateWallBracket.setName(wallBracketDto.getName());
            updateWallBracket.setPrice(wallBracketDto.getPrice());
            updateWallBracket.setAdjustable(wallBracketDto.isAdjustable());


            repos.save(updateWallBracket);
            return transferToWallBracketDto(updateWallBracket);
        }
    }
}
