package pl.olejniczak.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.olejniczak.model.Post;
import java.io.File;
import java.io.IOException;


@Component
public class CustomFileWriter {

    public static final String OUTPUT_PATH = "target/";
    Logger logger = LoggerFactory.getLogger(CustomFileWriter.class);

    public void writeFile(Post post) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(OUTPUT_PATH + post.getId() + ".json"), post);
            logger.info("Post {}, \"{}\" saved.", post.getId(), post.getTitle());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
