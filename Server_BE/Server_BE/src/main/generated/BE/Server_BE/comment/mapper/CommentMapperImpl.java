package BE.Server_BE.comment.mapper;

import BE.Server_BE.comment.dto.CommentDto.Patch;
import BE.Server_BE.comment.dto.CommentDto.Post;
import BE.Server_BE.comment.entity.Comment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< Updated upstream
    date = "2023-02-22T17:17:43+0900",
=======
    date = "2023-02-22T17:12:23+0900",
>>>>>>> Stashed changes
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment commentDtoPostToComment(Post post) {
        if ( post == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setBody( post.getBody() );

        return comment;
    }

    @Override
    public Comment commentDtoPatchToComment(Patch patch) {
        if ( patch == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setBody( patch.getBody() );

        return comment;
    }
}
