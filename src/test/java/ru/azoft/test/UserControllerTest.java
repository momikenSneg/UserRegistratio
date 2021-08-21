package ru.azoft.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;
import ru.azoft.test.controller.UserController;
import ru.azoft.test.models.User;
import ru.azoft.test.service.receiver.UserReceiver;
import ru.azoft.test.service.registrar.UserRegistrar;

import java.sql.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {UserController.class, Main.class})
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    @Qualifier("userRegistrar")
    private UserRegistrar registrar;
    @MockBean
    @Qualifier("userReceiver")
    private UserReceiver receiver;
    @Autowired
    private MockMvc mockMvc;
    private User user;

    @BeforeEach
    public void init(){
        user = new User("id",
                "login",
                "password",
                "Name",
                "Surname",
                "email@email.com",
                new Date(System.currentTimeMillis()));
    }

    @Test
    void testRightUser() throws Exception {
        Mockito.when(registrar.register(user)).thenReturn("id");
        mockMvc.perform(
                post("/user/register")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testNullUser() throws Exception {
        mockMvc.perform(
                post("/user/register")
                        .content(objectMapper.writeValueAsString(null))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testNullDataUser() throws Exception {
        user.setFirstName(null);
        user.setLastName(null);
        user.setEmail(null);
        user.setLogin(null);
        user.setPassword(null);
        MvcResult result = mockMvc.perform(
                post("/user/register")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        Assert.isTrue(content.equals("Wrong format of user"), "");
    }

    @Test
    void testWrongEmailUser() throws Exception {
        user.setEmail("email");
        MvcResult result = mockMvc.perform(
                post("/user/register")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        Assert.isTrue(content.equals("Wrong email format"), "");
    }

    @Test
    void testGetNullUser() throws Exception {
        Mockito.when(receiver.getUser("id")).thenReturn(null);
        mockMvc.perform(
                get("/user/get/id"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetUser() throws Exception {
        Mockito.when(receiver.getUser("id")).thenReturn(user);
        mockMvc.perform(
                get("/user/get/id"))
                .andExpect(status().isOk());
    }

}
