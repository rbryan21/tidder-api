package com.tidder.api.domain.associative;

import com.tidder.api.domain.Post;
import com.tidder.api.domain.User;
import com.tidder.api.domain.associative.key.UserPostId;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserPost {

    @EmbeddedId
    private UserPostId id;

    private Boolean isUpvote;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("postId")
    private Post post;

    public UserPost(User user, Post post) {
        this.user = user;
        this.post = post;
        this.id = new UserPostId(user.getId(), post.getId());
    }

}
