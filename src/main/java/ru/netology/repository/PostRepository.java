package ru.netology.repository;
import org.springframework.stereotype.Repository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Repository
// Stub
public class PostRepository {

    private final ConcurrentHashMap<Long, Post> data = new ConcurrentHashMap<>();
    private final AtomicLong COUNT = new AtomicLong(0);

    public List<Post> all() {
        //TODO
        return new ArrayList<>(data.values());
    }

    public Optional<Post> getById(long id) {
        //TODO
        return Optional.ofNullable(data.get(id));
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            post.setId(COUNT.incrementAndGet());
            data.put(post.getId(), post);
        } else {
            if (data.containsKey(post.getId()))
                data.replace(post.getId(), post);
            else
                throw new NotFoundException("Failed to update post");
        }
        return post;
    }

    public void removeById(long id) {
        data.remove(id);
    }
}
