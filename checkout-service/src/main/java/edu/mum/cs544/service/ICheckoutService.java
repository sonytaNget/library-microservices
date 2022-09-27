package edu.mum.cs544.service;

import java.util.List;

import edu.mum.cs544.domain.Checkout;

public interface ICheckoutService {
    public List<Checkout> getAll();

    public void add(Checkout checkout);

    public Checkout get(int id);

    public Checkout update(Checkout checkout);

    public void delete(int id);

    public List<Checkout> findByMemberId(Integer memberId);
}
