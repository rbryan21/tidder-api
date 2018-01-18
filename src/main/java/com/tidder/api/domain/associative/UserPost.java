package com.tidder.api.domain.associative;

import com.tidder.api.domain.Post;
import com.tidder.api.domain.User;
import com.tidder.api.domain.associative.key.UserPostId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public static class UserPostBuilder {

        private UserPostId id;
        private Boolean isUpvote;
        private User user;
        private Post post;

        public UserPostBuilder setId(UserPostId id) {
            this.id = id;
            return this;
        }

        public UserPostBuilder setUpvote(Boolean upvote) {
            isUpvote = upvote;
            return this;
        }

        public UserPostBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public UserPostBuilder setPost(Post post) {
            this.post = post;
            return this;
        }

        public UserPost build() {
            return new UserPost(this.id, this.isUpvote, this.user, this.post);
        }

    }

}
