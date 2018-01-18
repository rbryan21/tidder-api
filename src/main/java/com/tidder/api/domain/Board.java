package com.tidder.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(min = 1, max = 255, message = "Title must be between 1 and 255 characters in length.")
    private String title;

    @NotNull
    private Date createdDate;

    private Date updatedDate;

    // many threads belong to one user
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    // many boards belong to one subtidder
    @ManyToOne(fetch = FetchType.EAGER)
    private Subtidder subtidder;

    public static class BoardBuilder {

        private Long id;
        private String title;
        private Date createdDate;
        private Date updatedDate;
        private User user;
        private Subtidder subtidder;

        public BoardBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public BoardBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public BoardBuilder setCreatedDate(Date createdDate) {
            this.createdDate = createdDate;
            return this;

        }

        public BoardBuilder setUpdatedDate(Date updatedDate) {
            this.updatedDate = updatedDate;
            return this;

        }

        public BoardBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public BoardBuilder setSubtidder(Subtidder subtidder) {
            this.subtidder = subtidder;
            return this;
        }

        public Board build() {
            return new Board(this.id, this.title, this.createdDate, this.updatedDate, this.user, this.subtidder);
        }

    }

}
