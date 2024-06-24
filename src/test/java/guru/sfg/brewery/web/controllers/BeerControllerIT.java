package guru.sfg.brewery.web.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
public class BeerControllerIT extends BaseIT{


    @Test
    void findBeerSecurityTest() throws Exception {
        this.mockMvc.perform(get("/beers/find")).andExpect(status().isOk());
    }


    @Test
    void findCustomers() throws Exception{
        mockMvc.perform(get("/customers/find").with(httpBasic("user","password"))).andExpect(status().isOk());
    }
}
