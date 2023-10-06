package no.hvl.dat250.rest.todos;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
/**
 * Rest-Endpoint for todos.
 */
@RestController
@CrossOrigin(origins = "*")
public class TodoController {

  private final ArrayList<Todo> todos = new ArrayList<>();
  public static final String TODO_WITH_THE_ID_X_NOT_FOUND = "Todo with the id %s not found!";
  @GetMapping("/todos")
  public ArrayList<Todo> getTodos() {
    System.out.println("getTodos " + todos);
    return todos;
  }

  @GetMapping("/todos/{id}")
  public ResponseEntity<?> getTodoById(@PathVariable Long id) {
    Todo todo = findTodoById(id);
    if (todo == null) {
      Map<String, String> error = new HashMap<>();
      error.put("message", String.format(TODO_WITH_THE_ID_X_NOT_FOUND, id));
      return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(todo, HttpStatus.OK);
  }


  @PostMapping("/todos")
  public ResponseEntity<?> addTodo(@RequestBody Todo todo) {
    long id = (long) (Math.random() * 2 * 10000000);
    if (todo.getId() == null)
      todo.setId(id);
    todos.add(todo);
    return new ResponseEntity<>(todo, HttpStatus.OK);
  }

  @PutMapping("/todos/{id}")
  public ResponseEntity<?> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
    todos.stream().filter(t -> t.getId().equals(id)).findFirst().ifPresent(t -> {
      t.setDescription(todo.getDescription());
      t.setSummary(todo.getSummary());
    });
    return new ResponseEntity<>(todo, HttpStatus.OK);
  }


  @DeleteMapping("/todos/{id}")
  public ResponseEntity<?> deleteTodoById(@PathVariable String id) {
    long idLong = Long.parseLong(id);
    Todo todo = findTodoById(idLong);
    if (todo == null) {
      Map<String, String> error = new HashMap<>();
      error.put("message", String.format(TODO_WITH_THE_ID_X_NOT_FOUND, id));
      return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    todos.remove(todo);
    return new ResponseEntity<>(todo, HttpStatus.OK);
  }

  private Todo findTodoById(long id) {
    return todos.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
  }
}
