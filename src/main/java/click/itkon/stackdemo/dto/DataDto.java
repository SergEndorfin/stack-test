package click.itkon.stackdemo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataDto {

    @NotEmpty(message = "Value can't be null or empty")
    @Size(min = 2, max = 30, message = "The length of the Value  should be between 2 and 30")
    private String value;
}
