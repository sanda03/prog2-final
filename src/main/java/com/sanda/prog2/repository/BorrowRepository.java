package com.sanda.prog2.repository;

import com.sanda.prog2.model.Borrow;
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
public class BorrowRepository{
    private Connection connection;
    private Borrow newBorrow(ResultSet resultSet) throws SQLException {
        return new Borrow(
                resultSet.getInt("id_borrow"),
                resultSet.getInt("id_book"),
                resultSet.getInt("id_member"),
                resultSet.getTimestamp("start_date,"),
                resultSet.getTimestamp("end_date"),
                resultSet.getBoolean("is_returned")
        );
    }
    private String createQuery(Borrow borrow){
        StringBuilder query = new StringBuilder("SET ");
        boolean status = false;
        if(borrow.getIdBook() != null){
            query.append("\"id_book\" = ? ");
            status = true;
        }
        if(borrow.getIdMember() != null){
            if(status)
                query.append(", ");
            query.append("\"id_member\" = ? ");
            status = true;
        }
        if(borrow.getStartDate() != null){
            if(status)
                query.append(", ");
            query.append("\"start_date\" = ? ");
            status = true;
        }
        if(borrow.getEndDate() != null){
            if(status)
                query.append(", ");
            query.append("\"end_date\" = ? ");
            status = true;
        }
        if(borrow.getIsReturned() != null){
            if(status)
                query.append(", ");
            query.append("\"is_returned\" = ? ");
        }
        return query.toString();
    }
    public List<Borrow> getAllBorrows() throws SQLException{
        String query = "SELECT * FROM \"borrow\"";
        ResultSet resultSet = this.connection.createStatement().executeQuery(query);
        List<Borrow> listBorrows = new ArrayList<>();
        while(resultSet.next()){
            listBorrows.add(this.newBorrow(resultSet));
        }
        return listBorrows;
    }

    public Borrow getBorrowById(Integer id) throws SQLException {
        String query = "SELECT * FROM \"borrow\" WHERE \"id_borrow\" = ? ";
        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return this.newBorrow(resultSet);
        }
        return null;
    }

    public Borrow deleteBorrow(Integer idBorrow) throws SQLException {
        Borrow borrow = this.getBorrowById(idBorrow);
        if( borrow != null){
            String query = "DELETE FROM \"borrow\" WHERE \"id_borrow\" = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1,idBorrow);
            statement.executeUpdate();
        }
        return borrow;
    }

    public Borrow updateBorrow(Borrow borrow) throws SQLException {
        if(this.getBorrowById(borrow.getIdBorrow()) != null){
            String query = "UPDATE \"borrow\" SET \"id_book\" = ? , \"id_member\" = ?" +
                "\"start_date\"= ?, \"end_date\" = ? , \"is_returned\" = ? WHERE id_borrow = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1,borrow.getIdBook());
            statement.setInt(2,borrow.getIdMember());
            statement.setTimestamp(3,borrow.getStartDate());
            statement.setTimestamp(4,borrow.getEndDate());
            statement.setBoolean(5,borrow.getIsReturned());
            statement.setInt(6,borrow.getIdBorrow());
            statement.executeUpdate();
            return borrow;
        }
        return null;
    }

    public Borrow updatePartialBorrow(Borrow borrow) throws SQLException {
        if(this.getBorrowById(borrow.getIdBorrow()) != null) {
            int valueIndex = 0;
            String query = "UPDATE \"borrow\" " + createQuery(borrow) + " WHERE \"id_borrow\" = ?";
            PreparedStatement statement = this.connection.prepareStatement(query);
            if(borrow.getIdBook() != null)
                statement.setInt(++valueIndex, borrow.getIdBook());
            if(borrow.getIdMember() != null)
                statement.setInt(++valueIndex, borrow.getIdMember());
            if(borrow.getStartDate() != null)
                statement.setTimestamp(++valueIndex, borrow.getStartDate());
            if(borrow.getEndDate() != null)
                statement.setTimestamp(++valueIndex, borrow.getEndDate());
            if(borrow.getIsReturned() != null)
                statement.setBoolean(++valueIndex, borrow.getIsReturned());
            statement.setInt(++valueIndex, borrow.getIdBorrow());
            statement.executeUpdate();
            return this.getBorrowById(borrow.getIdBorrow());
        }
        return null;
    }

    public Borrow createBorrow(Borrow borrow) throws SQLException {
        String query = "INSERT INTO \"borrow\"(\"id_book\",\"id_member\",\"start_date\",\"end_date\",\"is_returned\")" +
                " VALUES (?,?,?,?,?)";
        PreparedStatement statement = this.connection.prepareStatement(query);
        statement.setInt(1,borrow.getIdBook());
        statement.setInt(2,borrow.getIdMember());
        statement.setTimestamp(3,borrow.getStartDate());
        statement.setTimestamp(4,borrow.getEndDate());
        statement.setBoolean(5,borrow.getIsReturned());
        statement.executeUpdate();
        String queryNewBorrow = "SELECT * FROM \"borrow\" ORDER BY \"id_borrow\" DESC LIMIT 1";
        ResultSet resultSet = this.connection.createStatement().executeQuery(queryNewBorrow);
        resultSet.next();
        return this.newBorrow(resultSet);
    }
}