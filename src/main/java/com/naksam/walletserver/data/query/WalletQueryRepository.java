package com.naksam.walletserver.data.query;

import com.naksam.walletserver.domain.entity.DepositLog;
import com.naksam.walletserver.dto.DepositHistory;
import com.naksam.walletserver.dto.WalletHistory;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.naksam.walletserver.domain.entity.QClub.club;
import static com.naksam.walletserver.domain.entity.QDepositLog.depositLog;
import static com.naksam.walletserver.domain.entity.QUser.user;

@Repository
@AllArgsConstructor
public class WalletQueryRepository {
    private final JPAQueryFactory query;

    public WalletHistory findMyWalletHistory(Long userId) {
        return query.select(Projections.constructor(WalletHistory.class,
                        user.id,
                        user.wallet.amount,
                        Projections.list(Projections.constructor(DepositHistory.class,
                                depositLog.id,
                                depositLog.amount,
                                depositLog.createdTime,
                                depositLog.club.name,
                                new CaseBuilder()
                                        .when(depositLog.detail.eq(DepositLog.Detail.USER_TO_CLUB))
                                        .then("출금")
                                        .otherwise("입금")
                        ))
                ))
                .from(user)
                .where(user.id.eq(userId))
                .leftJoin(depositLog)
                .on(depositLog.user.eq(user))
                .leftJoin(club)
                .on(depositLog.club.eq(club))
                .fetchFirst();
    }
}
