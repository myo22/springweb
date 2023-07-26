package com.example.springweb.service;

import com.example.springweb.doamin.ToDo;
import com.example.springweb.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor // final이 붙은 필드만 초기화하는 생성자가 자동으로 만들어진다.
public class ToDoService {
    private final ToDoRepository toDoRepository; // final은 생성자에서 초기화 해야지 사용가능.

    @Transactional(readOnly = true) // 해당 메소드에서는 조회만 한다.
    public List<ToDo> getToDoList(){
        return toDoRepository.findAll();
    }

//    public ToDoService(ToDoRepository toDoRepository) {
//        this.toDoRepository = toDoRepository;
//    }

    @Transactional // 트랜잭션 단위로 동작한다.
    public void addToDo(String todo){
        ToDo toDo = new ToDo();
        toDo.setTodo(todo);
        toDoRepository.save(toDo);
    }
}
