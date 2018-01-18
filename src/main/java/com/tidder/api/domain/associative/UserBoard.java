package com.tidder.api.domain.associative;

import com.tidder.api.domain.Board;
import com.tidder.api.domain.User;
import com.tidder.api.domain.associative.key.UserBoardId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBoard {

    @EmbeddedId
    private UserBoardId id;

    private Boolean isUpvote;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("boardId")
    private Board board;

    public UserBoard(User user, Board board) {
        this.user = user;
        this.board = board;
        this.id = new UserBoardId(user.getId(), board.getId());
    }

    public static class UserBoardBuilder {

        private UserBoardId id;
        private User user;
        private Board board;
        private Boolean isUpvote;

        public UserBoardBuilder setId(UserBoardId id) {
            this.id = id;
            return this;
        }

        public UserBoardBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public UserBoardBuilder setBoard(Board board) {
            this.board = board;
            return this;
        }

        public UserBoardBuilder setUpvote(Boolean upvote) {
            isUpvote = upvote;
            return this;
        }

        public UserBoard build() {
            return new UserBoard(this.id, this.isUpvote, this.user, this.board);
        }

    }

}
