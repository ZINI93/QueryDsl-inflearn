package study.querydsl.controller.domain;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.domain.Member;
import study.querydsl.domain.Team;

import java.util.List;

@SpringBootTest
@Transactional
class MemberTest {

    @Autowired
    EntityManager em;


    @Test
    public void testEntity(){
        Team teamA = new Team("TeamA");
        Team teamB = new Team("TeamB");
        em.persist(teamA);
        em.persist(teamB);

        Member zini = new Member("zini1", 10, teamA);
        Member zin2 = new Member("zini2", 20, teamA);

        Member zin3 = new Member("zini3", 30, teamB);
        Member zin4 = new Member("zini4", 40, teamB);

        em.persist(zini);
        em.persist(zin2);
        em.persist(zin3);
        em.persist(zin4);

        //초기화
        em.flush();
        em.clear();

        List<Member> members = em.createQuery("select m from Member m", Member.class)
                .getResultList();

        for (Member member : members) {
            System.out.println("member = " + member);
            System.out.println("--> member.team = " + member.getTeam());
        }

        }
}