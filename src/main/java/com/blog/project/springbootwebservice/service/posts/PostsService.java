package com.blog.project.springbootwebservice.service.posts;

import com.blog.project.springbootwebservice.config.auth.LoginUser;
import com.blog.project.springbootwebservice.config.auth.dto.SessionUser;
import com.blog.project.springbootwebservice.domain.posts.Posts;
import com.blog.project.springbootwebservice.domain.posts.PostsRepository;
import com.blog.project.springbootwebservice.domain.user.User;
import com.blog.project.springbootwebservice.domain.user.UserRepository;
import com.blog.project.springbootwebservice.web.dto.PostsListResponseDto;
import com.blog.project.springbootwebservice.web.dto.PostsResponseDto;
import com.blog.project.springbootwebservice.web.dto.PostsSaveRequestDto;
import com.blog.project.springbootwebservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor //final이나 @notnull이 붙은 필드 생성자 자동생성
@Service
public class PostsService {

    private final PostsRepository postsRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostsSaveRequestDto save(PostsSaveRequestDto requestDto, @LoginUser SessionUser user) {

        User currentUser = userRepository.findByEmail(user.getEmail()).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

        Posts target = Posts.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .author(currentUser)
                .build();

       Posts entity = postsRepository.save(target);

        return PostsSaveRequestDto.of(entity);
    }

   @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다, id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        postsRepository.delete(posts);//JpaRepository에서 이미 delete 메서드 제공
    }
}
