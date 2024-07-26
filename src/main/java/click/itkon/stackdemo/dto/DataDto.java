package click.itkon.stackdemo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(
        name = "Data",
        description = "Data to hold input information"
)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataDto {

    @Schema(description = "Data holder", example = "important data")
    @NotEmpty(message = "Value can't be null or empty")
    @Size(min = 3, max = 30, message = "The length of the Value should be between 3 and 30")
    private String value;
}
