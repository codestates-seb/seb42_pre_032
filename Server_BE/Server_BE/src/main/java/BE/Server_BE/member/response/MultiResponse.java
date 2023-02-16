package BE.Server_BE.member.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MultiResponse <T, P>{
    T data;
    P pageinfo;
}
