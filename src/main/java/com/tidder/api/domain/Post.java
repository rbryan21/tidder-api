package com.tidder.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String content;

    @NotNull
    private Date createdDate;

    private Date updatedDate;

    // many posts belong to one thread
    @ManyToOne(fetch = FetchType.EAGER)
    private Board board;

    // many posts belong to one user
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public static class PostBuilder {

        private Long id;
        private String content;
        private Date createdDate;
        private Date updatedDate;
        private Board board;
        private User user;

        public PostBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public PostBuilder setContent(String content) {
            this.content = content;
            return this;
        }

        public PostBuilder setCreatedDate(Date createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public PostBuilder setUpdatedDate(Date updatedDate) {
            this.updatedDate = updatedDate;
            return this;
        }

        public PostBuilder setBoard(Board board) {
            this.board = board;
            return this;
        }

        public PostBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public Post build() {
            return new Post(this.id, this.content, this.createdDate, this.updatedDate, this.board, this.user);
        }

    }

}