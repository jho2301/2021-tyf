package com.example.tyfserver.member.repository;

import com.example.tyfserver.member.dto.CurationsResponse;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberQueryRepository{

    private final EntityManager em;

    @Override
    public List<CurationsResponse> findCurations() {
        return em.createQuery(
                "select new com.example.tyfserver.member.dto.CurationsResponse(" +
                        "           d.member.nickname, sum(d.amount), d.member.pageName) " +
                        "from Donation d " +
                        "join d.member " +
                        "group by d.member " +
                        "order by sum(d.amount) desc ",
                CurationsResponse.class
        )
                .setFirstResult(0)
                .setMaxResults(5)
                .getResultList();
    }
}