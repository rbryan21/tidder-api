package com.tidder.api.domain.associative;

import com.tidder.api.domain.Thread;
import com.tidder.api.domain.User;
import com.tidder.api.domain.associative.key.UserThreadId;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserThread {

    @EmbeddedId
    private UserThreadId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    private Boolean isUpvote;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("threadId")
    private Thread thread;

    UserThread(User user, Thread thread) {
        this.user = user;
        this.thread = thread;
        this.id = new UserThreadId(user.getId(), thread.getId());
    }

}
