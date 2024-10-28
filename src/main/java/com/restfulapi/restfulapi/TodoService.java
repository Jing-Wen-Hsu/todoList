package com.restfulapi.restfulapi;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public List<Todo> findAll() {
        return  todoRepository.findAll();
    }

    public Todo getById(Long id) {
        //return  todoRepository.findById(id);
        return findOrElseThrow(id);
    }

    public void deleteById(Long id) {
        Todo todo = this.findOrElseThrow(id);
        todoRepository.deleteById(id);
    }

    public void deleteAll() {
       todoRepository.deleteAll();
    }

    public Todo updateTodo(Todo todo){
        Todo oldTodo = this.findOrElseThrow(todo.getId());
        oldTodo.setTitle(todo.getTitle() == null ? oldTodo.getTitle() : todo.getTitle());
        oldTodo.setCompleted(todo.isCompleted());
        return (Todo) this.todoRepository.save(oldTodo);
    }

    public Todo findOrElseThrow(Long id) {
        return (Todo)this.todoRepository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Todo not found with id: " + id);
        });
    }

    //變更狀態為完成
    public Todo setCompleteTodo(Long id){
        Todo oldTodo = this.findOrElseThrow(id);
        oldTodo.setCompleted(Boolean.TRUE);
        return (Todo)this.todoRepository.save(oldTodo);

    }

//    public Todo setCompleteTodo(Long id) {
//        Todo oldTodo = todoRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Todo not found with id: " + id));
//        oldTodo.setCompleted(Boolean.TRUE);
//        return todoRepository.save(oldTodo);
//    }


    //變更狀態為未完成
    public Todo setUncompleteTodo(Long id){
        Todo oldTodo = this.findOrElseThrow(id);
        oldTodo.setCompleted(Boolean.FALSE);
        return (Todo)this.todoRepository.save(oldTodo);

    }
}
