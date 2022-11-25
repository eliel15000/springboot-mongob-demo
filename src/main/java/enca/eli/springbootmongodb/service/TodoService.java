package enca.eli.springbootmongodb.service;

import enca.eli.springbootmongodb.exception.TodoCollectionException;
import enca.eli.springbootmongodb.model.TodoDTO;

import javax.validation.ConstraintViolationException;
import java.util.List;

public interface TodoService {

    public void createTodo(TodoDTO todo) throws ConstraintViolationException, TodoCollectionException;

    public List<TodoDTO> getAllTodos();

    public TodoDTO getSingleTodo(String id) throws TodoCollectionException;

    public void updateTodo(String id, TodoDTO todo) throws TodoCollectionException;

    public void deleteTodoById(String id) throws TodoCollectionException;
}
