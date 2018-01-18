package com.tidder.api.bootstrap;

import com.tidder.api.domain.Board;
import com.tidder.api.domain.Post;
import com.tidder.api.domain.Subtidder;
import com.tidder.api.domain.User;
import com.tidder.api.domain.associative.UserBoard;
import com.tidder.api.domain.associative.UserPost;
import com.tidder.api.domain.associative.UserSubtidder;
import com.tidder.api.domain.associative.key.UserBoardId;
import com.tidder.api.domain.associative.key.UserPostId;
import com.tidder.api.domain.associative.key.UserSubtidderId;
import com.tidder.api.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class Bootstrap implements CommandLineRunner {

    private final PostRepository postRepository;
    private final SubtidderRepository subtidderRepository;
    private final BoardRepository boardRepository;
    private final UserPostRepository userPostRepository;
    private final UserRepository userRepository;
    private final UserSubtidderRepository userSubtidderRepository;
    private final UserBoardRepository userBoardRepository;

    public Bootstrap(PostRepository postRepository, SubtidderRepository subtidderRepository, BoardRepository boardRepository, UserPostRepository userPostRepository, UserRepository userRepository, UserSubtidderRepository userSubtidderRepository, UserBoardRepository userBoardRepository) {
        this.postRepository = postRepository;
        this.subtidderRepository = subtidderRepository;
        this.boardRepository = boardRepository;
        this.userPostRepository = userPostRepository;
        this.userRepository = userRepository;
        this.userSubtidderRepository = userSubtidderRepository;
        this.userBoardRepository = userBoardRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadUser();
        loadSubtidder();
        loadUserSubtidder();
        loadBoard();
        loadUserBoard();
        loadPost();
        loadUserPost();
    }

    private void loadUser() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String hashedPass = encoder.encode("password");
        User user = new User.UserBuilder()
                .setId(1L)
                .setUsername("rbryan21")
                .setEmail("rbryan21@gmail.com")
                .setPassword(hashedPass)
                .build();
        userRepository.save(user);
    }

    private void loadBoard() {
        User user = userRepository.findOne(1L);
        Subtidder subtidder = subtidderRepository.findByName("Braves");
        Board board = new Board.BoardBuilder()
                .setId(1L)
                .setTitle("Board Title")
                .setCreatedDate(new Date(System.currentTimeMillis()))
                .setUser(user)
                .setSubtidder(subtidder)
                .build();
        boardRepository.save(board);
    }

    private void loadPost() {
        User user = userRepository.findOne(1L);
        Board board = boardRepository.findOne(1L);
        Post post = new Post.PostBuilder()
                .setId(1L)
                .setContent("Example Content")
                .setCreatedDate(new Date(System.currentTimeMillis()))
                .setUser(user)
                .setBoard(board)
                .build();
        postRepository.save(post);
    }

    private void loadUserPost() {
        User user = userRepository.findOne(1L);
        Post post = postRepository.findOne(1L);
        UserPost userPost = new UserPost.UserPostBuilder()
                .setId(new UserPostId(user.getId(), post.getId()))
                .setUser(user)
                .setPost(post)
                .setUpvote(true)
                .build();
        userPostRepository.save(userPost);
    }

    private void loadUserBoard() {
        User user = userRepository.findOne(1L);
        Board board = boardRepository.findOne(1L);
        UserBoard userBoard = new UserBoard.UserBoardBuilder()
                .setId(new UserBoardId(user.getId(), board.getId()))
                .setUser(user)
                .setBoard(board)
                .setUpvote(false)
                .build();
        userBoardRepository.save(userBoard);
    }

    private void loadSubtidder() {
        User user = userRepository.findOne(1L);
        Subtidder subtidder = new Subtidder.SubtidderBuilder()
                .setName("Braves")
                .setUser(user)
                .build();
        subtidderRepository.save(subtidder);
    }

    private void loadUserSubtidder() {
        User user = userRepository.findOne(1L);
        Subtidder subtidder = subtidderRepository.findByName("Braves");
        UserSubtidder userSubtidder = new UserSubtidder.UserSubtidderBuilder()
                .setId(new UserSubtidderId(user.getId(), subtidder.getName()))
                .setSubtidder(subtidder)
                .setUser(user)
                .build();
        userSubtidderRepository.save(userSubtidder);
    }

}
