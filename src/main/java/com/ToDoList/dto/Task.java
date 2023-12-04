package com.ToDoList.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Table (name="TasksList")
public class Task {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime creationDate = LocalDateTime.now();

}



