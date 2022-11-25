package enca.eli.springbootmongodb.controller;

import enca.eli.springbootmongodb.exception.TodoCollectionException;
import enca.eli.springbootmongodb.model.TodoDTO;
import enca.eli.springbootmongodb.repository.TodoRepository;
import enca.eli.springbootmongodb.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepo;

    @Autowired
    private TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<?> getAllTodos() {

//        List<TodoDTO> todos = todoRepo.findAll();
//
//        if (todos.size() > 0) {
//            return new ResponseEntity<List<TodoDTO>>(todos, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("No todos available", HttpStatus.NOT_FOUND);
//        }

        // Refactoring
        List<TodoDTO> todos = todoService.getAllTodos();

        return new ResponseEntity<>(todos, todos.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/todos")
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todo) {

//        try {
//            todoRepo.save(todo);
//            return new ResponseEntity<TodoDTO>(todo, HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }

        // Refactoring
        try {
            todoService.createTodo(todo);
            return new ResponseEntity<TodoDTO>(todo, HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (TodoCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getSingleTodo(@PathVariable("id") String id) {
//        Optional<TodoDTO> todoOptional = todoRepo.findById(id);
//
//        if (todoOptional.isPresent()) {
//            return new ResponseEntity<>(todoOptional.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Todo not found with id: " + id, HttpStatus.NOT_FOUND);
//        }

        // Refactoring the retrieving single document
        try {
            return new ResponseEntity<>(todoService.getSingleTodo(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody TodoDTO todo) {
//        Optional<TodoDTO> todoOptional = todoRepo.findById(id);
//
//        if (todoOptional.isPresent()) {
//            TodoDTO todoToSave = todoOptional.get();
//            todoToSave.setCompleted(todo.getCompleted() != null ? todo.getCompleted() : todoToSave.getCompleted());
//            todoToSave.setTodo(todo.getTodo() != null ? todo.getTodo() : todoToSave.getTodo());
//            todoToSave.setDescription(todo.getDescription() != null ? todo.getDescription() : todoToSave.getDescription());
//            todoToSave.setUpdatedAt(new Date(System.currentTimeMillis()));
//            todoRepo.save(todoToSave);
//            return new ResponseEntity<>(todoToSave, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Todo not found with id: " + id, HttpStatus.NOT_FOUND);
//        }

        // Refactoring
        try {
            todoService.updateTodo(id, todo);
            return new ResponseEntity<>("Update Todo with id: " + id, HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (TodoCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) {

//        try {
//            todoRepo.deleteById(id);
//            return new ResponseEntity<>("Successfully deleted with id: " + id, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }

        // Refactoring
        try {
            todoService.deleteTodoById(id);
            return new ResponseEntity<>("Successfully deleted with id: " + id, HttpStatus.OK);
        } catch (TodoCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
