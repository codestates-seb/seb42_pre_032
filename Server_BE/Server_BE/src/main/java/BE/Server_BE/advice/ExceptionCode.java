package BE.Server_BE.advice;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    MEMBER_NOT_FOUND(402, "회원이 존재하지 않습니다."),
    MEMBER_ALREADY_EXIST(403,"이미 존재하는 회원입니다."),
    METHOD_NOT_ALLOWED(404, "잘못된 메서드 입니다."),
    DATA_IS_EMPTY(405,"저장된 데이터가 없습니다."),
    FIELD_MUST_BE_FULFILLED(406, "빈칸은 될 수 없습니다.")
    ;
    @Getter
    int status;
    @Getter
    String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
