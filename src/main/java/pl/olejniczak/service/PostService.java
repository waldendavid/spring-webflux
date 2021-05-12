package pl.olejniczak.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.olejniczak.model.Post;
import pl.olejniczak.repository.PostRepository;
import reactor.core.publisher.Flux;


@Service
public class PostService {

    Logger logger = LoggerFactory.getLogger(CustomFileWriter.class);

    private CustomFileWriter customFileWriter = new CustomFileWriter();

    @Autowired
    private PostRepository postRepository;

    public Flux<Post> getAllProducts() {
        postRepository.findAll().subscribe(customFileWriter::writeFile);
        return postRepository.findAll();
    }

    @EventListener(ApplicationReadyEvent.class)
    public Flux<Post> getPosts() {
        Flux<Post> studentFlux = WebClient.create()
                .get()
                .uri("http://localhost:8080/posts")
                .retrieve()
                .bodyToFlux(Post.class);

        studentFlux.subscribe(post -> logger.info("{} uploaded.", post));
        return studentFlux;
    }

}

