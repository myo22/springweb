package com.example.springweb.service;

import com.example.springweb.doamin.ToDo;
import com.example.springweb.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
@RequiredArgsConstructor // final이 붙은 필드만 초기화하는 생성자가 자동으로 만들어진다.
public class ToDoService {
    private final ToDoRepository toDoRepository; // final은 생성자에서 초기화 해야지 사용가능.

//    public ToDoService(ToDoRepository toDoRepository) {
//        this.toDoRepository = toDoRepository;
//    }

    @Transactional // 트랜잭션 단위로 동작한다.
    public void addToDo(){
        ToDo todo = new ToDo();
        todo.setTodo("hello" + new Date().toString());
        toDoRepository.save(todo);
    }
}
