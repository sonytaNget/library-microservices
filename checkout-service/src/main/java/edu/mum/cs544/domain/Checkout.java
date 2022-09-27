package edu.mum.cs544.domain;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Checkout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.DATE)
    private Date checkoutDate;
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    private boolean isReturned;
    @Temporal(TemporalType.DATE)
    private Date returnedDate;

    private Integer bookId;

    private Integer memberId;
    private static final int BORROW_MAX_LENGTH = 7;

    public Checkout() {

    }

    public Checkout(Integer bookId, Integer memberId) {
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        this.checkoutDate = today;

        calendar.add(Calendar.DATE, BORROW_MAX_LENGTH);
        Date due = calendar.getTime();
        this.dueDate = due;
        this.bookId = bookId;
        this.memberId = memberId;
        this.isReturned = false;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean isReturned) {
        this.isReturned = isReturned;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getId() {
        return id;
    }

}
