package click.itkon.stackdemo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(name = "ErrorResponse",
        description = "Schema to hold error response information")
@Getter
@Builder
@AllArgsConstructor
public class ErrorResponseDto {

    @Schema(description = "API path invoked by client")
    private String apiPath;

    @Schema(description = "Error code representing the error happened")
    private HttpStatus errorCode;

    @Schema(description = "Error message representing the error happened")
    private String errorMessage;

    @Schema(description = "Time representing when the error happened",
            example = "2024-07-26T18:53:06.597Z")
    private LocalDateTime errorTime;
}
