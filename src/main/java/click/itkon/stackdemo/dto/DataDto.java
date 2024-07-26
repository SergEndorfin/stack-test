package click.itkon.stackdemo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataDto {
    private String key;
    private String value;
}
