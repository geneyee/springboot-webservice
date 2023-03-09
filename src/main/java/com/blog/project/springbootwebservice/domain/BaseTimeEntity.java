package com.blog.project.springbootwebservice.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //createdDate, modifiedDate
@EntityListeners(AuditingEntityListener.class) //Auditing 기능 포함
public class BaseTimeEntity {//모든 Entity의 상위 클래스로 Entity들의 createdDate, modifiedDate 관리

    @CreatedDate //Entity가 생성되어 저장될 때 시간 자동저장
    private LocalDateTime createdDate;

    @LastModifiedDate //조회한 Entity의 값을 변경할 때 시간 자동저장
    private LocalDateTime modifiedDate;

}
