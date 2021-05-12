package pl.olejniczak.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import pl.olejniczak.model.Post;
import reactor.core.publisher.Flux;

@Repository
public class PostRepositoryImpl implements PostRepository{

    public Flux<Post> findAll() {
        return WebClient.create()
                .get()
                .uri(SOURCE_URL)
                .retrieve()
                .bodyToFlux(Post.class);
    }
}
