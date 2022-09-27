package edu.mum.cs544.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.cs544.domain.Checkout;

public interface CheckoutDao extends JpaRepository<Checkout, Integer> {
    List<Checkout> findByMemberId(Integer memberId);
}
