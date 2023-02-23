package BE.Server_BE.comment.mapper;

import BE.Server_BE.comment.dto.CommentDto.Patch;
import BE.Server_BE.comment.dto.CommentDto.Post;
import BE.Server_BE.comment.entity.Comment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-23T12:15:56+0900",
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
