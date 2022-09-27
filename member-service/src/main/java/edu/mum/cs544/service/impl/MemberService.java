package edu.mum.cs544.service.impl;

import edu.mum.cs544.dao.MemberDao;
import edu.mum.cs544.domain.Member;
import edu.mum.cs544.exception.NotFoundException;
import edu.mum.cs544.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@Transactional
public class MemberService implements IMemberService {
    @Autowired
    private MemberDao memberDao;

    @Override
    public List<Member> getAll() {

        return memberDao.findAll();
    }

    @Override
    public void add(Member member) {

        memberDao.save(member);
    }

    @Override
    public Member get(int id) throws NotFoundException {

        Member member = memberDao.findById(id).orElse(null);
        if (member == null) {
            throw new NotFoundException("Member with Id " + id + " is not found.");
        }
        return member;
    }

    @Override
    public void update(Member member) {

        Member m = memberDao.findById(member.getId()).orElse(null);
        if (m == null) {
            throw new NotFoundException("Member with Id " + member.getId() + " doesn't exist.");
        }
        memberDao.save(member);
    }

    @Override
    public void delete(int id) throws NotFoundException {

        try {
            memberDao.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("Member with Id " + id + " doesn't exist");
        }
    }

}
