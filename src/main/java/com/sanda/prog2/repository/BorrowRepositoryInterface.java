package com.sanda.prog2.repository;

import com.sanda.prog2.model.Borrow;

import java.sql.SQLException;
import java.util.List;

public interface BorrowRepositoryInterface {
    List<Borrow> getAllBorrows() throws SQLException;
    Borrow getBorrowById(Integer id) throws SQLException;
    Borrow deleteBorrow(Integer id) throws SQLException;
    Borrow updateBorrow(Borrow borrow) throws SQLException;
    Borrow updatePartialBorrow(Borrow borrow) throws SQLException;
    Borrow createBorrow(Borrow borrow) throws SQLException;
}
