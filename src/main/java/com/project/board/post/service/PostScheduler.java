package com.project.board.post.service;

import com.project.board.post.repository.PostRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// 스프링빈으로 등록하는 것
@Component
public class PostScheduler {

    private final PostRepository postRepository;

    public PostScheduler(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Scheduled(cron = "0 0/1 * * * *") // 초 분 시 일 월 요일
    public void postSchedule(){
        // 현재 시간보다 시간상 뒤처진 것들의 scheduled 값을 null 변경
        


        // repository.save 에서 기존에 데이터가 있으면 update, 없으면 알아서 insert가 됨

    }

}
