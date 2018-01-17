package com.tidder.api.domain.associative;

import com.tidder.api.domain.Subtidder;
import com.tidder.api.domain.User;
import com.tidder.api.domain.associative.key.UserSubtidderId;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
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

}
