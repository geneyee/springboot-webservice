package com.blog.project.springbootwebservice.web;

import com.blog.project.springbootwebservice.config.auth.LoginUser;
import com.blog.project.springbootwebservice.config.auth.dto.SessionUser;
import com.blog.project.springbootwebservice.service.posts.PostsService;
import com.blog.project.springbootwebservice.web.dto.PostsResponseDto;
import com.blog.project.springbootwebservice.web.dto.PostsSaveRequestDto;
import com.blog.project.springbootwebservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor //final or notnull
@RestController //response + request
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public ResponseEntity<PostsSaveRequestDto> save(@RequestBody PostsSaveRequestDto requestDto, @LoginUser SessionUser user) {

       PostsSaveRequestDto dto = postsService.save(requestDto, user);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
