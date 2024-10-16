package guru.sfg.brewery.web.controllers.api;

import guru.sfg.brewery.web.controllers.BaseIT;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
public class BeerRestControllerIT extends BaseIT {



    @Test
    public void deleteBeers() throws Exception {
        this.mockMvc.perform(delete("api/v1/beer/delete")).andExpect(status().isOk());
    }

    @Test
    public void getBeers() throws Exception {
         this.mockMvc.perform(get("/api/v1/beer")

         ).andExpect(status().isOk());
    }

    @Test
    public void getBeerById() throws Exception {
        this.mockMvc.perform(get("/api/v1/beer/97df0c39-90c4-4ae0-b663-453e8e19c311")).andExpect(status().isOk());

    }

    @Test
    public void getBeerByUpc() throws Exception {
        mockMvc.perform(get("api/v1/beerUpc/0631234200036")).andExpect(status().isOk());

    }



}