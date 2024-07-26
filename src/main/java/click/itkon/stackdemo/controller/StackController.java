package click.itkon.stackdemo.controller;

import click.itkon.stackdemo.dto.DataDto;
import click.itkon.stackdemo.dto.ErrorResponseDto;
import click.itkon.stackdemo.service.DataServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Stack Spring Boot REST API",
        description = "Stack Spring Boot API with H2 embedded DB"
)
@RestController
@RequiredArgsConstructor
public class StackController {

    private final DataServiceImpl dataServiceImpl;

    @Operation(
            summary = "Add data to the Stack endpoint",
            description = "REST API to add new Data into Stack"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Data added successfully",
                    content = @Content(
                            schema = @Schema(implementation = DataDto.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request. Invalid input data",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    name = "Invalid Input Example",
                                    value = """
                                             {
                                               "apiPath": "/push",
                                               "errorCode": "BAD_REQUEST",
                                               "errorMessage": "{value=The length of the Value should be between 3 and 30}",
                                               "errorTime": "2024-07-26T18:53:06.597Z"
                                             }
                                            """))),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    name = "Server error occurred",
                                    value = """
                                             {
                                               "apiPath": "/push",
                                               "errorCode": "INTERNAL_SERVER_ERROR",
                                               "errorMessage": "Something went wrong. Check the logs for more details",
                                               "errorTime": "2024-07-26T18:53:06.597Z"
                                             }
                                            """)))
    })
    @PostMapping("push")
    public ResponseEntity<DataDto> push(@Valid @RequestBody DataDto dataDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dataServiceImpl.push(dataDto));
    }

    @Operation(
            summary = "Fetch and remove the data out of Stack endpoint",
            description = "REST API fetch and remove the Data from Stack (LIFO)"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success response",
                    content = @Content(
                            schema = @Schema(implementation = DataDto.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(
                    responseCode = "404",
                    description = "Response in case the Stack is empty",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    name = "Invalid Input Example",
                                    value = """
                                             {
                                               "apiPath": "/pop",
                                               "errorCode": "NOT_FOUND",
                                               "errorMessage": "Stack is empty",
                                               "errorTime": "2024-07-26T18:53:06.597Z"
                                             }
                                            """))),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = @ExampleObject(
                                    name = "Server error occurred",
                                    value = """
                                             {
                                               "apiPath": "/pop",
                                               "errorCode": "INTERNAL_SERVER_ERROR",
                                               "errorMessage": "Something went wrong. Check the logs for more details",
                                               "errorTime": "2024-07-26T18:53:06.597Z"
                                             }
                                            """)))
    })
    @GetMapping("pop")
    public ResponseEntity<DataDto> pop() {
        return ResponseEntity.ok(dataServiceImpl.getLastAndRemove());
    }
}
