package pl.olejniczak.repository;
import pl.olejniczak.model.Post;
import reactor.core.publisher.Flux;

public interface PostRepository {

    int RANGE = 100;
    String SOURCE_URL = "https://jsonplaceholder.typicode.com/posts/";

    Flux<Post> findAll();
}
