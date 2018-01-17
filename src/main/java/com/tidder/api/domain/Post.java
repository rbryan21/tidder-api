package com.tidder.api.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String content;

    @NotNull
    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    // many posts belong to one thread
    @ManyToOne(fetch = FetchType.EAGER)
    private Thread thread;

    // many posts belong to one user
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

}
