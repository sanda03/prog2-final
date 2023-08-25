package com.sanda.prog2.repository;

import com.sanda.prog2.model.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class MemberRepository {
    private Connection connection;
    private Member newMember(ResultSet resultSet) throws SQLException {
        return new Member(
                resultSet.getInt("id_member"),
                resultSet.getString("name"),
                resultSet.getString("first_name")
        );
    }
    private String createQuery(Member member){
        StringBuilder query = new StringBuilder("SET ");
        boolean status = false;
        if(member.getName() != null){
            query.append("\"name\" = ? ");
            status = true;
        }
        if(member.getFirstName() != null){
            if(status)
                query.append(", ");
            query.append("\"first_name\" = ? ");
        }
        return query.toString();
    }
    public List<Member> getALlMembers() throws SQLException{
        String query = "SELECT * FROM \"member\"";
        ResultSet resultSet = this.connection.createStatement().executeQuery(query);
        List<Member> listMembers = new ArrayList<>();
        while(resultSet.next()){
            listMembers.add(this.newMember(resultSet));
        }
        return listMembers;
    }

    public Member getMemberById(Integer id) throws SQLException {
        String query = "SELECT * FROM \"member\" WHERE \"id_member\" = ? ";
        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return this.newMember(resultSet);
        }
        return null;
    }

    public Member deleteMember(Integer idMember) throws SQLException {
        Member member = this.getMemberById(idMember);
        if( member != null){
            String query = "DELETE FROM \"member\" WHERE \"id_member\" = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1,idMember);
            statement.executeUpdate();
        }
        return member;
    }

    public Member updateMember(Member member) throws SQLException {
        if(this.getMemberById(member.getIdMember()) != null){
            String query = "UPDATE \"member\" SET \"name\" = ? , \"first_name\" = ? WHERE \"id_member\" = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1,member.getName());
            statement.setString(2,member.getFirstName());
            statement.setInt(3,member.getIdMember());
            statement.executeUpdate();
            return member;
        }
        return null;
    }

    public Member updatePartialMember(Member member) throws SQLException {
        if(this.getMemberById(member.getIdMember()) != null) {
            int valueIndex = 0;
            String query = "UPDATE \"member\" " + createQuery(member) + " WHERE \"id_member\" = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            if(member.getName() != null)
                statement.setString(++valueIndex, member.getName());
            if(member.getFirstName() != null)
                statement.setString(++valueIndex, member.getFirstName());
            statement.setInt(++valueIndex, member.getIdMember());
            statement.executeUpdate();
            return this.getMemberById(member.getIdMember());
        }
        return null;
    }

    public Member createMember(Member member) throws SQLException {
        String query = "INSERT INTO \"member\"(\"name\",\"first_name\") VALUES (?,?)";
        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setString(1,member.getName());
        statement.setString(2,member.getFirstName());
        statement.executeUpdate();
        String queryNewMember = "SELECT * FROM \"member\" ORDER BY \"id_member\" DESC LIMIT 1";
        ResultSet resultSet = this.connection.createStatement().executeQuery(queryNewMember);
        resultSet.next();
        return this.newMember(resultSet);
    }
}
