package com.tidder.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subtidder {

    @Id
    @Column(unique = true)
    @Length(min = 1, max = 255, message = "Name must be between 1 and 255 characters in length.")
    @NotNull
    private String name;

    // many subtidders belong to one user
    @ManyToOne
    private User user;

    public static class SubtidderBuilder {

        private String name;
        private User user;

        public SubtidderBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public SubtidderBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public Subtidder build() {
            return new Subtidder(this.name, this.user);
        }

    }

}
