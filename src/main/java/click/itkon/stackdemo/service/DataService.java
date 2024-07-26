package click.itkon.stackdemo.service;

import click.itkon.stackdemo.dto.DataDto;
import click.itkon.stackdemo.entity.Data;
import click.itkon.stackdemo.mapper.DataMapper;
import click.itkon.stackdemo.repository.DataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataService {

    private final DataMapper dataMapper;
    private final DataRepository dataRepository;

    public DataDto push(DataDto dataDto) {
        return dataMapper.entityToDto(
                dataRepository.save(
                        dataMapper.dtoToEntity(dataDto)));
    }

    public DataDto getLastAndRemove() {
        Data foundData = dataRepository
                .findLastEntity()
                .orElseThrow(() -> new RuntimeException("Data not found"));
        dataRepository.delete(foundData);
        return dataMapper.entityToDto(foundData);
    }
}
