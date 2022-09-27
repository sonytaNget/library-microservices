package edu.mum.cs544.service;

import edu.mum.cs544.domain.Member;

import java.util.List;

public interface IMemberService {

    public List<Member> getAll();

    public void add(Member member);

    public Member get(int id);

    public void update(Member member);

    public void delete(int id);
}
