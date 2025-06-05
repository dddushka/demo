package com.example.demo;

import com.example.demo.controller.SchoolClassController;
import com.example.demo.dto.mapper.SchoolClassMapper;
import com.example.demo.service.SchoolClassService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = SchoolClassController.class)
public class SchoolClassControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SchoolClassService schoolClassService;

    @MockBean
    private SchoolClassMapper schoolClassMapper;

    @Test
    @WithMockUser
    public void testGetCreateSchoolClassForm() throws Exception {
        mockMvc.perform(get("/schoolclass/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("create-schoolclass"))
                .andExpect(model().attributeExists("schoolClass"));
    }
}
