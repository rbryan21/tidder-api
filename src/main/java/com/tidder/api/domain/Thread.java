package com.tidder.api.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(min = 1, max = 255, message = "Title must be between 1 and 255 characters in length.")
    private String title;

    @NotNull
    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    // many threads belong to one user
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

}
