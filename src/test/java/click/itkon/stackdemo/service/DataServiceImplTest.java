package click.itkon.stackdemo.service;

import click.itkon.stackdemo.dto.DataDto;
import click.itkon.stackdemo.entity.Data;
import click.itkon.stackdemo.exception.StackEmptyException;
import click.itkon.stackdemo.mapper.DataMapper;
import click.itkon.stackdemo.repository.DataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataServiceImplTest {

    @Mock
    DataMapper dataMapper;
    @Mock
    DataRepository dataRepository;
    @InjectMocks
    DataServiceImpl dataServiceImpl;

    DataDto dataDto;
    Data data;

    @BeforeEach
    void setUp() {
        dataDto = new DataDto();
        data = new Data();
    }

    @Test
    void push() {
        when(dataMapper.entityToDto(data)).thenReturn(dataDto);
        when(dataRepository.save(data)).thenReturn(data);
        when(dataMapper.dtoToEntity(dataDto)).thenReturn(data);

        dataServiceImpl.push(dataDto);

        verify(dataMapper).entityToDto(data);
        verify(dataRepository).save(data);
        verify(dataMapper).dtoToEntity(dataDto);
    }

    @Test
    void getLastAndRemove_success() {
        Optional<Data> optionalData = Optional.of(data);

        when(dataRepository.findLast()).thenReturn(optionalData);
        doNothing().when(dataRepository).delete(data);
        when(dataMapper.entityToDto(data)).thenReturn(dataDto);

        dataServiceImpl.getLastAndRemove();

        verify(dataRepository).findLast();
        verify(dataRepository).delete(data);
        verify(dataMapper).entityToDto(data);
    }

    @Test
    void getLastAndRemove_stackEmpty() {
        when(dataRepository.findLast()).thenReturn(Optional.empty());

        StackEmptyException e = assertThrows(
                StackEmptyException.class,
                () -> dataServiceImpl.getLastAndRemove());

        assertEquals(e.getMessage(), StackEmptyException.ERROR_MESSAGE);
    }
}
