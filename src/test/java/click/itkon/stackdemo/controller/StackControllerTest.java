package click.itkon.stackdemo.controller;

import click.itkon.stackdemo.dto.DataDto;
import click.itkon.stackdemo.exception.StackEmptyException;
import click.itkon.stackdemo.service.DataServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StackController.class)
class StackControllerTest {

    public static final String PUSH_URL = "/push";
    public static final String POP_URL = "/pop";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DataServiceImpl dataServiceMock;

    @Test
    void push_success() throws Exception {
        String data = "data";
        DataDto dataDto = DataDto.builder().value(data).build();

        when(dataServiceMock.push(any(DataDto.class))).thenReturn(dataDto);

        mockMvc.perform(post(PUSH_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dataDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.value").value(data));

        verify(dataServiceMock).push(any(DataDto.class));
    }

    @Test
    void whenPushInvalidRequestSizeValue_thenReturnBadRequestError() throws Exception {
        String data = "da";
        DataDto dataDto = DataDto.builder().value(data).build();

        mockMvc.perform(post(PUSH_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dataDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.apiPath").value(PUSH_URL))
                .andExpect(jsonPath("$.errorCode").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errorMessage").value("{value=The length of the Value should be between 3 and 30}"))
                .andExpect(jsonPath("$.errorTime").exists());
    }

    @Test
    void whenPushInvalidRequestNullValue_thenReturnBadRequestError() throws Exception {
        DataDto dataDto = DataDto.builder().build();

        mockMvc.perform(post(PUSH_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dataDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.apiPath").value(PUSH_URL))
                .andExpect(jsonPath("$.errorCode").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errorMessage").value("{value=Value can't be null or empty}"))
                .andExpect(jsonPath("$.errorTime").exists());
    }

    @Test
    void pop_success() throws Exception {
        String data = "data";
        DataDto dataDto = DataDto.builder().value(data).build();
        when(dataServiceMock.getLastAndRemove()).thenReturn(dataDto);

        mockMvc.perform(get(POP_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(data));
    }

    @Test
    void whenPopAndStackEmpty_thenReturnStackEmptyError() throws Exception {
        when(dataServiceMock.getLastAndRemove()).thenThrow(new StackEmptyException());

        mockMvc.perform(get(POP_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.apiPath").value(POP_URL))
                .andExpect(jsonPath("$.errorCode").value("NOT_FOUND"))
                .andExpect(jsonPath("$.errorMessage").value(StackEmptyException.ERROR_MESSAGE))
                .andExpect(jsonPath("$.errorTime").exists());
    }
}
