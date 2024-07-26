package click.itkon.stackdemo.service;

import click.itkon.stackdemo.dto.DataDto;
import click.itkon.stackdemo.entity.Data;
import click.itkon.stackdemo.exception.StackEmptyException;
import click.itkon.stackdemo.mapper.DataMapper;
import click.itkon.stackdemo.repository.DataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataServiceImpl implements DataService {

    private final DataMapper dataMapper;
    private final DataRepository dataRepository;

    @Override
    public DataDto push(DataDto dataDto) {
        log.info("pushing data: {}", dataDto);

        return dataMapper.entityToDto(
                dataRepository.save(
                        dataMapper.dtoToEntity(dataDto)));
    }

    @Override
    public DataDto getLastAndRemove() {
        log.info("getting last item and remove it");

        Data foundData = dataRepository
                .findLast()
                .orElseThrow(StackEmptyException::new);
        dataRepository.delete(foundData);
        return dataMapper.entityToDto(foundData);
    }
}
