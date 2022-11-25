package enca.eli.springbootmongodb.repository;

import enca.eli.springbootmongodb.model.TodoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends MongoRepository<TodoDTO, String> {

    // A query method for finding the todo
    // the parameter index start from 0 (?0)
    // So, this query method is responsible for finding the todo from the database
    Optional<TodoDTO> findByTodo(String todo);
}
