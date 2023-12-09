package com.blog.project.springbootwebservice.web.dto;

import com.blog.project.springbootwebservice.domain.posts.Posts;
import com.blog.project.springbootwebservice.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto { //insert
    private String title;
    private String content;
    private String authorEmail;

    //setter 대신 생성자 사용 - dto 안전하게, 계층간 데이터 변조 위험x
    @Builder
    public PostsSaveRequestDto(String title, String content, String authorEmail) {
        this.title = title;
        this.content = content;
        this.authorEmail = authorEmail;
    }

    //dto -> entity(DB등록)
    /*
    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }*/

    // entity -> dto
    public static  PostsSaveRequestDto of(Posts entity) {
        return new PostsSaveRequestDto(
                entity.getTitle(),
                entity.getContent(),
                entity.getAuthor().getEmail()
        );
    }
}
