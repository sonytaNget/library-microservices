package edu.mum.cs544.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.mum.cs544.dao.CheckoutDao;
import edu.mum.cs544.domain.Checkout;
import edu.mum.cs544.exception.NotFoundException;

@Service
@Transactional
public class CheckoutService implements ICheckoutService {
    @Resource
    private CheckoutDao checkoutDao;

    @Override
    public List<Checkout> getAll() {
        return checkoutDao.findAll();
    }

    @Override
    public void add(Checkout checkout) {
        checkoutDao.save(checkout);
    }

    @Override
    public Checkout get(int id) throws NotFoundException {
        Checkout checkout = checkoutDao.findById(id).orElse(null);
        if (checkout == null) {
            throw new NotFoundException("Checkout with Id " + id + " is not found.");
        }
        return checkout;
    }

    @Override
    public Checkout update(Checkout checkout) throws NotFoundException {
        Checkout c = checkoutDao.findById(checkout.getId()).orElse(null);
        if (c == null) {
            throw new NotFoundException("Checkout with Id " + checkout.getId() + " doesn't exist.");
        }
        return checkoutDao.save(checkout);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        try {
            checkoutDao.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("Checkout with Id " + id + " doesn't exist");
        }
    }

    @Override
    public List<Checkout> findByMemberId(Integer memberId) throws NotFoundException {

        try {
            return checkoutDao.findByMemberId(memberId);
        } catch (Exception e) {
            throw new NotFoundException("This member has no checkout.");
        }
    }

}
