package edu.mum.cs544.controller;

import java.awt.print.Book;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.cs544.domain.Checkout;
import edu.mum.cs544.service.ICheckoutService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/checkout")
public class CheckoutRestController {

    @Autowired
    private ICheckoutService checkoutService;

    @GetMapping(produces = "application/json")
    public List<Checkout> getAll() {

        return checkoutService.getAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> get(@PathVariable int id) throws RuntimeException {

        try {
            Checkout checkout = checkoutService.get(id);

            return new ResponseEntity<>(checkout, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Map<String, Object> body) throws RuntimeException {

        try {
            int bookId = (int) body.get("bookId");
            int memberId = (int) body.get("memberId");
            Checkout checkout = new Checkout(bookId, memberId);
            checkoutService.add(checkout);
            return new ResponseEntity<>(checkout, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @Valid @RequestBody Checkout checkout)
            throws RuntimeException {

        if (id != checkout.getId())
            return new ResponseEntity<>("Illegal request", HttpStatus.BAD_REQUEST);
        try {
            checkoutService.update(checkout);
            return new ResponseEntity<>(checkout, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) throws RuntimeException {

        try {
            checkoutService.delete(id);
            return new ResponseEntity<>("Checkout has been deleted.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/member/{id}")
    public ResponseEntity<Object> getAllByMember(@PathVariable Integer memberId) throws RuntimeException {
        try {
            return new ResponseEntity<>(checkoutService.findByMemberId(memberId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
