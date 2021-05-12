package pl.olejniczak.springwebflux.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.olejniczak.model.Post;
import pl.olejniczak.repository.PostRepository;
import pl.olejniczak.repository.PostRepositoryImpl;
import pl.olejniczak.service.CustomFileWriter;
import reactor.core.publisher.Flux;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class PostRepositoryTest {

    PostRepository postRepository = new PostRepositoryImpl();

    @Test
    public void shouldCheckIfFilesExists() {
        File f;
        for (int range = PostRepository.RANGE; range > 0; range--) {
            f = new File(CustomFileWriter.OUTPUT_PATH + range + ".json");
            assertTrue(f.exists());
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {101, -52, 0})
    public void shouldCheckIfFilesDoesNotExists(int badNumber) {
        File file = new File(CustomFileWriter.OUTPUT_PATH + badNumber + ".json");
        assertFalse(file.exists());
    }

    @Test
    public void shouldTestIfNumberOdFilesIsCorrect() {
        File f;
        int counter = 0;
        int expectedAmount = PostRepository.RANGE;

        for (int i = 0; i <= expectedAmount; i++) {
            f = new File(CustomFileWriter.OUTPUT_PATH + i + ".json");
            if (f.exists()){
                counter++;
            }
        }
        assertEquals(expectedAmount, counter);
    }

    @Test
    public void shouldTestIfIdIsNotInRange() {
        //given
        Post post = new Post.Builder().userId(1).id(1).title("sunt aut facere repellat provident occaecati excepturi optio reprehenderit").build();

        //when
        boolean isPostByIdGreaterThanRange = isPostById(105);
        boolean isPostByIdLessThanRange = isPostById(-33);
        boolean isPostByIdEqualsZero = isPostById(-33);
        boolean expectedResult = false;

        //then
        assertEquals(expectedResult, isPostByIdGreaterThanRange);
        assertEquals(expectedResult, isPostByIdLessThanRange);
        assertEquals(expectedResult, isPostByIdEqualsZero);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    public void shouldTestIfRightPostIsPresent(int userId, int id, String title) {

        Post postTemp = new Post.Builder().userId(userId).id(id).title(title).build();

        Boolean isIdOk = postRepository.findAll().any(post -> (post.getId().equals(postTemp.getId()))).block();
        Boolean isTitleOk = postRepository.findAll().any(post -> (post.getTitle().equals(postTemp.getTitle()))).block();
        Boolean isUserIdOk = postRepository.findAll().any(post -> (post.getUserId().equals(postTemp.getUserId()))).block();

        boolean expectedResult = true;
        assertEquals(expectedResult, isIdOk);
        assertEquals(expectedResult, isTitleOk);
        assertEquals(expectedResult, isUserIdOk);

    }

    private boolean isPostById(Integer id) {
        int range = PostRepository.RANGE;
        Flux<Post> posts = postRepository.findAll();
        if (id > range || posts.any(p -> p.getId().equals(id)).block() == null)
            return false;
        return posts.any(p -> (p.getId().equals(id))).block();
    }

}
