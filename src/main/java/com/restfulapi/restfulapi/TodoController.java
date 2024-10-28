package com.restfulapi.restfulapi;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService todoService;

    //建構式
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    //新增
    @PostMapping("/add")
    public Todo addTodo(@RequestBody Todo todo){
        return  todoService.addTodo(todo);
    }
    //查詢全部
    @GetMapping("/findAll")
    public List<Todo> getAllTodos(){
        return  todoService.findAll();
    }

    //查詢某筆
    @GetMapping("/find/{id}")
    public Todo getTodoById(@PathVariable("id") Long id){
        return  todoService.getById(id);
    }

    //刪除全部
    @DeleteMapping("/delete")
    public void deleteAll(){
        todoService.deleteAll();
    }

    //刪除某筆
    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Long id){
        todoService.deleteById(id);
        return ("已成功刪除");
    }

    //修改待辦事項內容
    @PutMapping("/update/{id}")
    public Todo updateTodo (@PathVariable("id") Long id,@RequestBody Todo todo){
        todo.setId(id);
        return todoService.updateTodo(todo);
    }


    //修改狀態為完成
    @PatchMapping("/{id}/completed")
    public Todo completeTodo (@PathVariable ("id") Long id){
        return todoService.setCompleteTodo(id);
    }

    //修改狀態為未完成
    @PatchMapping("/{id}/uncompleted")
    public Todo uncompleteTodo (@PathVariable ("id") Long id){
        return todoService.setUncompleteTodo(id);
    }

}
