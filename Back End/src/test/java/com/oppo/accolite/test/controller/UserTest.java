package com.oppo.accolite.test.controller;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import com.oppo.accolite.controller.UserController;
import com.oppo.accolite.dao.UserDaoImp;
import com.oppo.accolite.models.Audit;
import com.oppo.accolite.models.User;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {UserController.class})
public class UserTest {
	@Autowired
	public MockMvc mockMvc;
	
	@MockBean
	public UserDaoImp userDaoImp;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public List<User> userList;
	
	@Before
	public void setup(){
		this.userList = new ArrayList<>();
		this.userList.add(new User(1,"Rishipal Singh", "abc@gmail.com", "1234", "123456", "123456789"));
		this.userList.add(new User(2,"abc", "def@gmail.com", "6789", "34524", "12312312313"));
	}
	
	
	@Test
	public void shouldGetAllUsersDetails() throws Exception {
		Mockito.when(userDaoImp.getAllUsers()).thenReturn(userList);
		mockMvc.perform(get("/user/getall")).andExpect(status().isOk())
		.andExpect(jsonPath("$.size()", is(userList.size())));
	}
	
	@Test
	public void shouldLoginUser() throws Exception {
		Mockito.when(userDaoImp.updateToken(null, null)).thenReturn(1);
		Mockito.when(userDaoImp.insertUser(null, null)).thenReturn(2);
		User user = new User(1,"Rishipal Singh","95rishipal@gmail.com","","115640335689848772862","eyJhbGciOiJSUzI1NiIs");
		Mockito.when(userDaoImp.findByEmail("95rishipal@gmail.com")).thenReturn(user);
		String token = "012345678901234567890";
		String json = objectMapper.writeValueAsString(user);
		mockMvc.perform(post("/user/login")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(json)
				.header("Token", token)
				).andExpect(status().is(200));
	}
	
	@Test
	public void shouldLoginUserException() throws Exception {
		Mockito.when(userDaoImp.updateToken(null, null)).thenReturn(1);
		Mockito.when(userDaoImp.insertUser(null, null)).thenReturn(2);
		User user = new User(1,"Rishipal Singh","95rishipal@gmail.com","","1156403356898487862","eyJhbGciOiJSUzI1NiIs");
		Mockito.when(userDaoImp.findByEmail("95rishipal@gmail.com")).thenReturn(user);
		String token = "012345678901234567890";
		String json = objectMapper.writeValueAsString(new User(1,"Rishipal Singh","95rishipl@gmail.com","","1156403356898862","eyJhbGciOiJSUzI1NiIs"));
		mockMvc.perform(post("/user/login")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(json)
				.header("Token", token)
				).andExpect(status().is(200));
	}
	@Test
	public void shouldReturnCurrentUser() throws Exception {
		Mockito.when(userDaoImp.findByEmail("95rishipal@gmail.com")).thenReturn(userList.get(0));
		mockMvc.perform(get("/user/getCurrentUser")).andExpect(status().isOk());

	}
	@Test
	public void shouldCheckTheCredential() throws Exception {
		User user = new User(1,"Rishipal Singh","95rishipal@gmail.com","","115640335689848772862","eyJhbGciOiJSUzI1NiIs");
		Mockito.when(userDaoImp.findByEmail("95rishipal@gmail.com")).thenReturn(user);
		mockMvc.perform(post("/user/check").header("Email", "95rishipal@gmail.com").header("Gid", "115640335689848772862")).andExpect(status().isOk());

	}
	
	@Test
	public void shouldCheckTheCredentialException() throws Exception {
		User user = new User(1,"Rishipal Singh","95rishipal@gmail.com","","115640335689848772862","eyJhbGciOiJSUzI1NiIs");
		Mockito.when(userDaoImp.findByEmail("95rishipal@gmail.com")).thenReturn(new User(1,"Rishipal Singh","95rishipal@gmail.com","","115640335848772862","eyJhbGciOiJSUzI1NiIs"));
		mockMvc.perform(post("/user/check").header("Email", "95rishipal@gmail.com").header("Gid", "115640335689848772862")).andExpect(status().isNotFound());

	}
	
	
	
	
}
