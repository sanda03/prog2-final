package com.sanda.prog2.repository;

import com.sanda.prog2.model.Borrow;
import com.sanda.prog2.model.Member;

import java.sql.SQLException;
import java.util.List;

public interface MemberRepositoryInterface {
    List<Member> getAllMembers() throws SQLException;
    Member getMemberById(Integer id) throws SQLException;
    Member deleteMember(Integer id) throws SQLException;
    Member updateMember(Member member) throws SQLException;
    Member updatePartialMember(Member member) throws SQLException;
    Member createMember(Member member) throws SQLException;
}
