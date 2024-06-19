package com.ori.taskcanvaspro;

import com.ori.taskcanvaspro.dto.TaskCreateDTO;
import com.ori.taskcanvaspro.dto.UserCreateDTO;
import com.ori.taskcanvaspro.dto.UserResponseDTO;
import com.ori.taskcanvaspro.model.entity.Task;
import com.ori.taskcanvaspro.model.entity.User;
import com.ori.taskcanvaspro.model.repository.TaskRepository;
import com.ori.taskcanvaspro.model.repository.UserRepository;
import com.ori.taskcanvaspro.service.TaskService;
import com.ori.taskcanvaspro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.HashSet;

@SpringBootApplication
public class TaskCanvasProApplication  {	//implements ApplicationRunner

	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(TaskCanvasProApplication.class, args);
	}


	//@Override
	public void run(ApplicationArguments args) throws Exception {

		UserCreateDTO u = new UserCreateDTO();
		u.setFirstName("John");
		u.setLastName("Greenfield");
		u.setEmail("avi.g@mail.com");
		u.setPassword("1q2w3e");
		u.setTeam("aaa");
		u.setAdmin(false);
		u.setTasks(new HashSet<Task>());

		UserResponseDTO u1 = userService.register(u);;

		// TODO
		TaskCreateDTO t = new TaskCreateDTO();
		t.setTitle("Task");
		t.setDescription("Task bla bla");
		t.setDeadline(LocalDate.now());
		t.setCompleted(false);
		t.setUser(u1);

		taskService.createTask(t);

		for( int i = 0; i < 10; i++){
			TaskCreateDTO t1 = new TaskCreateDTO();
			t1.setTitle("Task " + i);
			t1.setDescription("Task bla bla number " + i);
			t1.setDeadline(LocalDate.ofEpochDay(LocalDate.now().toEpochDay()+i));
			t1.setCompleted(false);
			t1.setUser(u1);
			taskService.createTask(t);

		}

	}
}
