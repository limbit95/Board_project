package com.project.board.post.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20)
    private String title;
    private String contents;
    private LocalDateTime createDate;

    @Builder
    public Post(String title, String contents){
        this.title = title;
        this.contents = contents;
        this.createDate = createDate.now();
    }


}
