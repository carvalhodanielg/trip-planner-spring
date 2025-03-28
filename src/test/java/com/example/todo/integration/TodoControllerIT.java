package com.example.todo.integration;

import com.example.todo.entities.Todo;
import com.example.todo.repositories.TodoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.RequestBuilder.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        todoRepository.deleteAll();
    }

    @Test
    void givenTodo_whenCreateTodo_theReturnSavedTodo() throws Exception{
        Todo todo = new Todo("title", "description", false);

        ResultActions response = mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(todo)));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(todo.getTitle())))
                .andExpect(jsonPath("$.completed", is(todo.isCompleted())));
    }

    @Test
    void givenListOfTodos_whenGetAllTodos_thenReturnTodosList() throws Exception{
        List<Todo> todos = List.of(new Todo("title 1", "description 1", false), new Todo("title 2", "description 2", true));

        todoRepository.saveAll(todos);


        ResultActions response = mockMvc.perform(get("/todos"));

        response.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.size()", is(todos.size())));
    }

    @Test
    void givenTodoId_whenDeleteTodo_thenReturn200() throws Exception{
        Todo todo = new Todo("title", "description", false);

        Todo createdTodo = todoRepository.save(todo);

        ResultActions response = mockMvc.perform(delete("/todos/{id}", createdTodo.getId()));

        response.andDo(print()).andExpect(status().isOk());

        assertEquals(0, todoRepository.count());
    }

    @Test
    void givenUpdatedTodo_whenUpdateTodo_thenReturnUpdatedTodo() throws Exception{
        Todo savedTodo = todoRepository.save(new Todo("todo 1", "descr1", false));

        Todo updatedTodo = new Todo("todo 1", "descr1", true);

        ResultActions response = mockMvc.perform(put("/todos/{id}", savedTodo.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedTodo)));

        response.andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(updatedTodo.getTitle())))
                .andExpect(jsonPath("$.completed", is(updatedTodo.isCompleted())));
    }

    @Test
    void givenInvalidTodo_whenCreateTodo_theReturn400() throws Exception{
        Todo invalidTodo = new Todo(null, "decr", false);

        ResultActions response = mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidTodo)));

        response.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.details").exists())
                .andExpect(jsonPath("$.details").value(containsString("title")));
    }

    @Test
    void givenTodoId_whenCompleteTodo_thenReturnUpdatedTodo() throws Exception{
        Todo todo = new Todo("Create test", "Creating unit test for completing a todo", false);

        Todo createdTodo = todoRepository.save(todo);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("completed", "true");

        ResultActions response = mockMvc.perform(patch("/todos/{id}/complete", todo.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .params(params));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.completed").value(true));








    }
}
