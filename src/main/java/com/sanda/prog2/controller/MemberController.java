package com.sanda.prog2.controller;

import com.sanda.prog2.model.Member;
import com.sanda.prog2.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("members")
@AllArgsConstructor
public class MemberController {
    private MemberService memberService;

    @GetMapping
    public List<Member> getAllMembers(HttpServletResponse response){
        return this.memberService.getAllMembers(response);
    }

    @GetMapping("/{idMember}")
    public Member getMemberById(HttpServletResponse response, @PathVariable Integer idMember){
        return this.memberService.getMemberById(response,idMember);
    }

    @DeleteMapping("/{idMember}")
    public Member deleteMember(HttpServletResponse response, @PathVariable Integer idMember){
        return this.memberService.deleteMember(response,idMember);
    }

    @PutMapping
    public Member deleteMember(HttpServletResponse response, @RequestBody Member member){
        return this.memberService.updateMember(response,member);
    }

    @PatchMapping
    public Member updatePartialMember(HttpServletResponse response, @RequestBody Member member){
        return this.memberService.updatePartialMember(response,member);
    }

    @PostMapping
    public Member createMember(HttpServletResponse response, @RequestBody Member member){
        return this.memberService.createMember(response,member);
    }
}
