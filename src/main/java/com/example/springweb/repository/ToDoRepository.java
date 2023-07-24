package com.example.springweb.repository;

import com.example.springweb.doamin.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> { //Long은 ID의 타입 적어주는것

}
