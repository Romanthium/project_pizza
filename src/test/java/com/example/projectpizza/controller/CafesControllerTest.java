package com.example.projectpizza.controller;

import com.example.projectpizza.config.security.SecurityConfig;
import com.example.projectpizza.model.Cafe;
import com.example.projectpizza.model.User;
import com.example.projectpizza.model.UserRole;
import com.example.projectpizza.service.CafeService;
import com.example.projectpizza.service.DishService;
import com.example.projectpizza.service.UserService;
import com.example.projectpizza.utils.validator.CafeValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import({SecurityConfig.class, CafeValidator.class})
@WebMvcTest(CafesController.class)
class CafesControllerTest {
    private Cafe cafe;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CafeService cafeService;

    @MockBean
    private DishService dishService;

    @MockBean
    private CafeValidator cafeValidator;
    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp() {
        User manager = User.builder()
                .id(1)
                .login("admin")
                .password("password")
                .userRole(UserRole.ADMIN)
                .build();

        cafe = Cafe.builder()
                .id(1)
                .name("Cafe")
                .phone("1111")
                .address("Cafe address")
                .manager(manager)
                .build();
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void index() throws Exception {
        this.mockMvc
                .perform(get("/cafes"))
                .andExpect(status().isOk())
                .andExpect(view().name("cafes/index"))
                .andExpect(model().attributeExists("cafes"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void show() throws Exception {
        when(cafeService.findOne(1)).thenReturn(cafe);

        this.mockMvc
                .perform(get("/cafes/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("cafes/show"))
                .andExpect(model().attributeExists("cafe"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void newCafe() throws Exception {
        this.mockMvc
                .perform(get("/cafes/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("cafes/new"))
                .andExpect(model().attributeExists("cafe"))
                .andExpect(model().attributeExists("dishes"))
                .andExpect(model().attributeExists("managers"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void create() throws Exception {
        BindingResult bindingResult = mock(BindingResult.class);
        Model model = mock(Model.class);

        doNothing().when(cafeValidator).validate(any(Cafe.class), any(BindingResult.class));

        when(bindingResult.hasErrors()).thenReturn(false);

        doNothing().when(cafeService).save(any(Cafe.class));


        this.mockMvc
                .perform(post("/cafes")
                        .param("cafe", "cafe")
                        .with(csrf())
                        .flashAttr("cafe", cafe)
                        .flashAttr("bindingResult", bindingResult)
                        .flashAttr("model", model))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/cafes"));
//                .andExpect(model().attributeExists("dishes"))
//                .andExpect(model().attributeExists("managers"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void edit() throws Exception {
        when(cafeService.findOne(1)).thenReturn(cafe);

        this.mockMvc
                .perform(get("/cafes/{id}/edit", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("cafes/edit"))
                .andExpect(model().attributeExists("cafe"))
                .andExpect(model().attributeExists("dishes"))
                .andExpect(model().attributeExists("managers"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void update() throws Exception {
        BindingResult bindingResult = mock(BindingResult.class);
        Model model = mock(Model.class);

        doNothing().when(cafeValidator).validate(any(Cafe.class), any(BindingResult.class));

        when(bindingResult.hasErrors()).thenReturn(false);

        doNothing().when(cafeService).save(any(Cafe.class));

        this.mockMvc
                .perform(patch("/cafes/{id}", cafe.getId())
                        .param("cafe", "cafe")
                        .with(csrf())
                        .flashAttr("cafe", cafe)
                        .flashAttr("bindingResult", bindingResult)
                        .flashAttr("model", model)
                        .flashAttr("id", cafe.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/cafes"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void delete() throws Exception {
        doNothing().when(cafeService).delete(cafe.getId());

        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/cafes/{id}", String.valueOf(cafe.getId()))
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/cafes"));

        Mockito.verify(cafeService, Mockito.times(1)).delete(cafe.getId());
    }
}