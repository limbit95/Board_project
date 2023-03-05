package com.project.board.author.controller;

import com.project.board.author.domain.Author;
import com.project.board.author.domain.AuthorDto;
import com.project.board.author.service.AuthorService;
import com.project.board.post.domain.Post;
import com.project.board.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AuthorController {

    private final AuthorService authorService;
    private final PostService postService;

    @Autowired
    public AuthorController(AuthorService authorService, PostService postService) {
        this.authorService = authorService;
        this.postService = postService;
    }

    @GetMapping("/author/list")
    public String authorList(Model model) {
        model.addAttribute("authors", this.authorService.findAll());
        // 화면을 렌더링하기 전에 domain을 넣어서
        return "author/authorList";
    }

    @GetMapping("/author/createform")
    public String createForm() {

        return "/author/authorCreate";
    }

    @PostMapping("/author/create")
    public String create(AuthorDto authorDto) {
//        // 방법1 : setter, 단점은 setter 사용을 여기저기 객체에 변경을 가할 수 있어 유지보수가 힘들어짐
//        Author author = new Author();
//        author.setName(authorDto.getName());
//        author.setEmail(authorDto.getEmail());
//        author.setPassword(authorDto.getPassword());
//        author.setRole(authorDto.getRole());
//        author.setCreateDate(LocalDateTime.now());

//        // 방법2 : 생성자를 통한 객체 생성 방식
//        // 문제점 : 가독성이 떨어지다보니, 실수할 여지 있고, 각 변수 자리에 맞게 세팅해줘야 정확한 세팅이 되는 문제점
//        Author author = new Author(authorDto.getName(), authorDto.getEmail(), authorDto.getPassword(), authorDto.getRole());

        // 방법 3 : Builder를 통한 객체 생성 방식
        Author author = Author.builder().name(authorDto.getName())
                .email(authorDto.getEmail())
                .password(authorDto.getPassword())
                .role(authorDto.getRole())
                .build();

        authorService.save(author);

        return "redirect:/";

    }

    @GetMapping("/author/findById")
    public String authorDetail(@RequestParam(value = "id") Long id, Model model) {

//        // 방법 1
//        Author author =this.authorService.findById(id);
//        List<Post> posts = postService.findByAuthor_id(id);
//        author.setCount(posts.size());

        // 방법 2 : author 객체를 최초 조회할 때부터 post와 join을 걸어 가져온다
        Author author = authorService.findById(id);

        // 방법 3 :
//        Author author = authorService.findAllFetchJoin(id);

        
        model.addAttribute("details", author); // 어떤 방법을 사용하든 고정값

//        // 방법 1
//        model.addAttribute("details", this.authorService.findById(id));

        return "/author/authorDetail";
    }
    

}