package aaagt.moneytransferservice.backend;

import aaagt.moneytransferservice.backend.repository.LogOperationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BackendApplicationTests {

    @MockBean
    private LogOperationRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void transferSuccessShouldReturnOperationId() throws Exception {
        String json = """
                {
                    "cardFromNumber":"123",
                    "cardFromValidTill":"456",
                    "cardFromCVV":"789",
                    "cardToNumber":"1011",
                    "amount":{
                        "value":1000,
                        "currency":"RUB"
                    }
                }
                """;
        String uuidResponse = "bbb3301f-95db-49d3-890d-31d3229448c5";

        when(repository.transfer(any())).thenReturn(uuidResponse);

        this.mockMvc.perform(post("/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operationId").value(uuidResponse));
    }

    @Test
    void transferBadRequestShouldReturnErrorResponse() throws Exception {
        String json = """
                {
                    "cardFromNumber":"gdfg",
                    "cardFromValidTill":"456",
                    "cardFromCVV":"789",
                    "cardToNumber":"1011",
                    "amount":{
                        "value":1000,
                        "currency":"RUB"
                    }
                }
                """;
        this.mockMvc.perform(post("/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("cardFromNumber contains not ony numbers"))
                .andExpect(jsonPath("$.id").value("0"));
    }

    @Test
    void contextLoads() {
    }

}
