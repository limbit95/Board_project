package com.project.board.post.controller;

import com.project.board.post.domain.Post;
import com.project.board.post.domain.PostDto;
import com.project.board.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/list")
    public String postList(Model model){
        model.addAttribute("posts", this.postService.findAll());

        return "/post/postList";
    }

    @GetMapping("/post/postCreateform")
    public String postCreateform(){
        return "/post/postCreate";
    }

    @PostMapping("/post/create")
    public String create(PostDto postDto) {
        Post post = Post.builder().title(postDto.getTitle())
                .contents(postDto.getContents())
                .build();

        postService.save(post);

        return "redirect:/post/list";
    }

    @GetMapping("/post/findById")
    public String postDetail(@RequestParam(value = "id") Long id, Model model) {
        model.addAttribute("post", this.postService.findById(id));

        return "/post/postDetail";
    }

    @GetMapping("/post/Delete")
    public String postDelete(@RequestParam(value = "id")Long id){
        this.postService.delete(id);
        return "redirect:/";
    }

}
