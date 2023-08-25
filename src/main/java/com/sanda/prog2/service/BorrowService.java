package com.sanda.prog2.service;

import com.sanda.prog2.model.Borrow;
import com.sanda.prog2.repository.BorrowRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class BorrowService {
    private BorrowRepository borrowRepository;

    private boolean testBadRequest(Borrow borrow,boolean testId){
        if(testId && borrow.getIdBorrow() == null)
            return true;
        return borrow.getIdBook() == null ||
                borrow.getIdMember() == null ||
                borrow.getStartDate() == null ||
                borrow.getEndDate() == null ||
                borrow.getIsReturned() == null;
    }
    public List<Borrow> getAllBorrows(HttpServletResponse response){
        try {
            return this.borrowRepository.getAllBorrows();
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }

    public Borrow getBorrowById(HttpServletResponse response,Integer idBorrow){
        try {
            Borrow borrow = this.borrowRepository.getBorrowById(idBorrow);
            if (borrow == null){
                SendError.notFound(
                        response,
                        "There is no borrow with id = " + idBorrow
                );
            }
            return borrow;
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }

    public Borrow deleteBorrow(HttpServletResponse response,Integer idBorrow){
        try {
            Borrow borrow = this.borrowRepository.deleteBorrow(idBorrow);
            if (borrow == null){
                SendError.notFound(
                        response,
                        "Cannot delete borrow with id = " + idBorrow + " because it doesn't exist"
                );
            }
            return borrow;
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }

    public Borrow updateBorrow(HttpServletResponse response,Borrow borrow){
        try {
            if(testBadRequest(borrow,true)){
                SendError.badRequest(
                        response,
                        "There is something missed of your request body"
                );
                return null;
            }

            Borrow oldBorrow= this.borrowRepository.updateBorrow(borrow);
            if (oldBorrow == null){
                SendError.notFound(
                        response,
                        "Cannot update borrow with id = " + borrow.getIdBorrow() + " because it doesn't exist"
                );
            }
            return borrow;
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }

    public Borrow updatePartialBorrow(HttpServletResponse response,Borrow borrow){
        try {
            if(
                    borrow.getIdBorrow() == null ||
                    (
                        borrow.getIdBook() == null &&
                        borrow.getIdMember() == null &&
                        borrow.getStartDate() == null &&
                        borrow.getEndDate() == null &&
                        borrow.getIsReturned()
                    )
            ){
                SendError.badRequest( response, "Verify you body request" );
                return null;
            }

            Borrow updateBorrow = this.borrowRepository.updatePartialBorrow(borrow);
            if (updateBorrow == null){
                SendError.notFound(
                        response,
                        "Cannot update borrow with id = " + borrow.getIdBorrow() + " because it doesn't exist"
                );
            }
            return updateBorrow;
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }

    public Borrow createBorrow(HttpServletResponse response,Borrow borrow){
        try {
            if(testBadRequest(borrow,false)){
                SendError.badRequest(
                        response,
                        "There is something missed of your request body"
                );
                return null;
            }
            return this.borrowRepository.createBorrow(borrow);
        }
        catch (SQLException error){
            System.out.println(error.getMessage());
            SendError.internalServerError(response);
        }
        return null;
    }
}