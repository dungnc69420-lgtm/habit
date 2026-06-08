package com.habit.service.impl;

import com.habit.model.Todo;
import com.habit.model.TodoRequest;
import com.habit.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {
    
    private final TodoRepository todoRepository;
    
    public List<Todo> getAllTodos() {
        return todoRepository.findAllByOrderByCreatedAtDesc();
    }
    
    public List<Todo> getTodosByStatus(boolean completed) {
        return todoRepository.findByCompletedOrderByCreatedAtDesc(completed);
    }
    
    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }
    
    public Todo createTodo(TodoRequest request) {
        Todo todo = Todo.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .completed(request.isCompleted())
                .priority(request.getPriority() != null ? request.getPriority() : Todo.Priority.MEDIUM)
                .build();
        return todoRepository.save(todo);
    }
    
    public Optional<Todo> updateTodo(Long id, TodoRequest request) {
        return todoRepository.findById(id).map(todo -> {
            todo.setTitle(request.getTitle());
            todo.setDescription(request.getDescription());
            todo.setCompleted(request.isCompleted());
            todo.setPriority(request.getPriority() != null ? request.getPriority() : todo.getPriority());
            return todoRepository.save(todo);
        });
    }
    
    public Optional<Todo> toggleTodo(Long id) {
        return todoRepository.findById(id).map(todo -> {
            todo.setCompleted(!todo.isCompleted());
            return todoRepository.save(todo);
        });
    }
    
    public boolean deleteTodo(Long id) {
        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public void deleteCompleted() {
        List<Todo> completed = todoRepository.findByCompletedOrderByCreatedAtDesc(true);
        todoRepository.deleteAll(completed);
    }
    
    public Stats getStats() {
        long total = todoRepository.count();
        long completed = todoRepository.findByCompletedOrderByCreatedAtDesc(true).size();
        long pending = total - completed;
        return new Stats(total, completed, pending);
    }
    
    public record Stats(long total, long completed, long pending) {
    }
}
