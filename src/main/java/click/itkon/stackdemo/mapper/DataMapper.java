package click.itkon.stackdemo.mapper;

import click.itkon.stackdemo.dto.DataDto;
import click.itkon.stackdemo.entity.Data;
import org.springframework.stereotype.Component;

@Component
public class DataMapper {

    public Data dtoToEntity(DataDto dataDto) {
        return Data.builder()
                .value(dataDto.getValue())
                .build();
    }

    public DataDto entityToDto(Data data) {
        return DataDto.builder()
                .value(data.getValue())
                .build();
    }
}
