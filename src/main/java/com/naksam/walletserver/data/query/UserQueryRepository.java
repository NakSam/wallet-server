package com.naksam.walletserver.data.query;

import com.naksam.walletserver.domain.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.naksam.walletserver.domain.entity.QClubUser.clubUser;
import static com.naksam.walletserver.domain.entity.QUser.user;

@Repository
@AllArgsConstructor
public class UserQueryRepository {
    private final JPAQueryFactory query;

    public List<User> findUsersOfClub(Long clubId) {
        return query.selectFrom(user)
                .join(clubUser)
                .on(
                        user.eq(clubUser.user),
                        clubUser.id.eq(clubId)
                )
                .fetch();
    }
}
