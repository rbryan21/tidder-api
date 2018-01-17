package com.tidder.api.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Subtidder {

    @Id
    @Column(unique = true)
    @Length(min = 1, max = 255, message = "Name must be between 1 and 255 characters in length.")
    @NotNull
    private String name;

    // many subtidders belong to one user
    @ManyToOne
    private User user;

}
