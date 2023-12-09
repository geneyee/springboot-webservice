package com.blog.project.springbootwebservice.web.dto;

import com.blog.project.springbootwebservice.domain.posts.Posts;
import com.blog.project.springbootwebservice.domain.user.User;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private User author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
    //Entity 필드의 일부만 사용하므로 생성자로 Entity를 받아 필드 값에 넣음
    //굳이 모든 필드를 가진 생성자가 필요하진 않으므로 Dto는 Entity를 받아 처리

}
