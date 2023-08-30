package com.sanda.prog2.controller;

import com.sanda.prog2.model.Borrow;
import com.sanda.prog2.service.BorrowService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("borrows")
@AllArgsConstructor
public class BorrowController {
    private BorrowService borrowService;

    @GetMapping
    public List<Borrow> getAllBorrows(HttpServletResponse response){
        return this.borrowService.getAllBorrows(response);
    }

    @GetMapping("/{idBorrow}")
    public Borrow getBorrowById(HttpServletResponse response, @PathVariable Integer idBorrow){
        return this.borrowService.getBorrowById(response,idBorrow);
    }

    @DeleteMapping("/{idBorrow}")
    public Borrow deleteBorrow(HttpServletResponse response, @PathVariable Integer idBorrow){
        return this.borrowService.deleteBorrow(response,idBorrow);
    }

    @PutMapping
    public Borrow updateBorrow(HttpServletResponse response, @RequestBody Borrow borrow){
        return this.borrowService.updateBorrow(response,borrow);
    }

    @PatchMapping
    public Borrow updatePartialBorrow(HttpServletResponse response, @RequestBody Borrow borrow){
        return this.borrowService.updatePartialBorrow(response,borrow);
    }

    @PostMapping
    public Borrow createBorrow(HttpServletResponse response, @RequestBody Borrow borrow){
        return this.borrowService.createBorrow(response,borrow);
    }
}
