package pl.olejniczak.springwebflux.service;

import org.junit.jupiter.api.Test;
import pl.olejniczak.model.Post;
import pl.olejniczak.service.CustomFileWriter;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomFileWriterTest {

    CustomFileWriter customFileWriter = new CustomFileWriter();

    @Test
    public void shouldWriteCorrectFile() {
        int tempId = 2021;
        Post post = new Post.Builder().id(tempId).build();
        customFileWriter.writeFile(post);

        File file = new File(CustomFileWriter.OUTPUT_PATH + tempId + ".json");
        assertTrue(file.exists());
        assertTrue(file.delete());
    }

    @Test
    public void shouldThrowExceptionWhenNullIsWriting() {
        assertThrows(NullPointerException.class, () -> {
            customFileWriter.writeFile(null);
        });

    }

}
