package pl.olejniczak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.olejniczak.model.Post;
import pl.olejniczak.service.PostService;
import reactor.core.publisher.Flux;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public Flux<Post> getAllProducts() {
        return postService.getAllProducts();
    }
}
