package aaagt.moneytransferservice.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BackendApplicationTests {

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
        this.mockMvc.perform(post("/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operationId").value(1));
    }

    @Test
    void contextLoads() {
    }

}
