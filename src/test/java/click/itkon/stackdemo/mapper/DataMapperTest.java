package click.itkon.stackdemo.mapper;

import click.itkon.stackdemo.dto.DataDto;
import click.itkon.stackdemo.entity.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataMapperTest {

    DataMapper dataMapper = new DataMapper();

    @Test
    void dtoToEntity() {
        DataDto dataDto = DataDto.builder().value("data").build();
        Data data = dataMapper.dtoToEntity(dataDto);
        assertEquals(dataDto.getValue(), data.getValue());
    }

    @Test
    void entityToDto() {
        Data data = Data.builder().id(12L).value("data").build();
        DataDto dataDto = dataMapper.entityToDto(data);
        assertEquals(data.getValue(), dataDto.getValue());
    }
}
