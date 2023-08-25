package com.sanda.prog2.service;

import com.sanda.prog2.model.Member;
import com.sanda.prog2.repository.MemberRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class MemberService {
    private MemberRepository memberRepository;

    private boolean testBadRequest(Member member,boolean testId){
        if(testId && member.getIdMember() == null)
            return true;
        return member.getName() == null ||
                member.getFirstName() == null;
    }
    public List<Member> getAllMembers(HttpServletResponse response){
        try {
            return this.memberRepository.getALlMembers();
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }

    public Member getMemberById(HttpServletResponse response,Integer idMember){
        try {
            Member member = this.memberRepository.getMemberById(idMember);
            if (member == null){
                SendError.notFound(
                        response,
                        "There is no member with id = " + idMember
                );
            }
            return member;
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }

    public Member deleteMember(HttpServletResponse response,Integer idMember){
        try {
            Member member = this.memberRepository.deleteMember(idMember);
            if (member == null){
                SendError.notFound(
                        response,
                        "Cannot delete member with id = " + idMember + " because it doesn't exist"
                );
            }
            return member;
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }

    public Member updateMember(HttpServletResponse response,Member member){
        try {
            if(testBadRequest(member,true)){
                SendError.badRequest(
                        response,
                        "There is something missed of your request body"
                );
                return null;
            }

            Member oldMember= this.memberRepository.updateMember(member);
            if (oldMember == null){
                SendError.notFound(
                        response,
                        "Cannot update member with id = " + member.getIdMember() + " because it doesn't exist"
                );
            }
            return member;
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }

    public Member updatePartialMember(HttpServletResponse response,Member member){
        try {
            if(
                    member.getIdMember() == null ||
                            ( member.getFirstName() == null && member.getName() == null)
            ){
                SendError.badRequest( response, "Verify you body request" );
                return null;
            }

            Member updateMember = this.memberRepository.updatePartialMember(member);
            if (updateMember == null){
                SendError.notFound(
                        response,
                        "Cannot update member with id = " + member.getIdMember() + " because it doesn't exist"
                );
            }
            return updateMember;
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }

    public Member createMember(HttpServletResponse response,Member member){
        try {
            if(testBadRequest(member,false)){
                SendError.badRequest(
                        response,
                        "There is something missed of your request body"
                );
                return null;
            }
            return this.memberRepository.createMember(member);
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }
}
