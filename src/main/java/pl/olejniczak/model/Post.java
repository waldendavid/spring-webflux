package pl.olejniczak.model;

public class Post {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;

    public static final class Builder {
        private Integer userId;
        private Integer id;
        private String title;
        private String body;

        public Builder userId(Integer userdId) {
            this.userId = userdId;
            return this;
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Post build() {
            Post post = new Post();

            post.userId = this.userId;
            post.id = this.id;
            post.title = this.title;
            post.body = this.body;
            return post;
        }
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Post{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
