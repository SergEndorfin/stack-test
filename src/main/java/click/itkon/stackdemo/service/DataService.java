package click.itkon.stackdemo.service;

import click.itkon.stackdemo.dto.DataDto;

public interface DataService {

    DataDto push(DataDto dataDto);

    DataDto getLastAndRemove();
}
