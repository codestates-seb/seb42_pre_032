package BE.Server_BE.springsecurity;

import BE.Server_BE.member.entity.Member;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class MemberRegistrationApplicationEvent extends ApplicationEvent {
    private Member member;

    public MemberRegistrationApplicationEvent(Object source, Member member){
        super(source);
        this.member = member;
    }
}
