package com.tidder.api.domain.associative;

import com.tidder.api.domain.Subtidder;
import com.tidder.api.domain.User;
import com.tidder.api.domain.associative.key.UserSubtidderId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSubtidder {

    @EmbeddedId
    private UserSubtidderId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("subtidderName")
    private Subtidder subtidder;

    public UserSubtidder(User user, Subtidder subtidder) {
        this.user = user;
        this.subtidder = subtidder;
        this.id = new UserSubtidderId(user.getId(), subtidder.getName());
    }

    public static class UserSubtidderBuilder {

        private UserSubtidderId id;
        private User user;
        private Subtidder subtidder;

        public UserSubtidderBuilder setId(UserSubtidderId id) {
            this.id = id;
            return this;
        }

        public UserSubtidderBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public UserSubtidderBuilder setSubtidder(Subtidder subtidder) {
            this.subtidder = subtidder;
            return this;
        }

        public UserSubtidder build() {
            return new UserSubtidder(this.id, this.user, this.subtidder);
        }

    }

}
