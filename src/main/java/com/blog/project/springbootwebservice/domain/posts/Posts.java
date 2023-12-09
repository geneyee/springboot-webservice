package com.blog.project.springbootwebservice.domain.posts;

import com.blog.project.springbootwebservice.domain.BaseTimeEntity;
import com.blog.project.springbootwebservice.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor //파라미터 없는 기본 생성자
@Entity //JPA Annotation, 테이블과 링크될 클래스
public class Posts extends BaseTimeEntity {//post table

    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto_increment
    private Long id;

    @Column(length = 500, nullable = false)//nullable = false -> not null
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)//not null
    private String content;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Builder
    public Posts(String title, String content, User author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
