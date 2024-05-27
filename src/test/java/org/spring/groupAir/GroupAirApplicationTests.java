package org.spring.groupAir;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.spring.groupAir.member.entity.MemberEntity;
import org.spring.groupAir.member.entity.QMemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@SpringBootTest
@Configuration
class GroupAirApplicationTests {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Test
    public void test() {

        QMemberEntity member = QMemberEntity.memberEntity;

        List<MemberEntity> memberEntityList = queryFactory.selectFrom(member).fetch();

        System.out.println("member: >>>" + member);
        System.out.println("memberEntityList: >>>" + memberEntityList);

    }


}
