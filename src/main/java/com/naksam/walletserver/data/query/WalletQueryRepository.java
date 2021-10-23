package com.naksam.walletserver.data.query;

import com.naksam.walletserver.domain.entity.ClubWalletLog;
import com.naksam.walletserver.domain.objects.Detail;
import com.naksam.walletserver.dto.DepositHistory;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.naksam.walletserver.domain.entity.QClubWalletLog.clubWalletLog;
import static com.naksam.walletserver.domain.entity.QUserWalletLog.userWalletLog;

@Repository
@AllArgsConstructor
public class WalletQueryRepository {
    private final JPAQueryFactory query;

    public List<DepositHistory> findWalletHistoryOfUser(Long userId) {
        return query.select(Projections.constructor(DepositHistory.class,
                        userWalletLog.id,
                        userWalletLog.amount,
                        userWalletLog.createdTime,
                        userWalletLog.targetName,
                        userWalletLog.detail
                ))
                .from(userWalletLog)
                .where(userIdEq(userId))
                .fetch();
    }

    public List<DepositHistory> findWalletHistoryOfClub(Long clubId) {
        return query.select(Projections.constructor(DepositHistory.class,
                        clubWalletLog.id,
                        clubWalletLog.amount,
                        clubWalletLog.createdTime,
                        clubWalletLog.targetName,
                        clubWalletLog.detail
                ))
                .from(clubWalletLog)
                .where(clubIdEq(clubId))
                .fetch();
    }

    public List<ClubWalletLog> findUndistributedLogOfClub(Long clubId) {
        return query.selectFrom(clubWalletLog)
                .where(
                        notDistributed(),
                        deposited()
                )
                .fetch();
    }

    private BooleanExpression notDistributed() {
        return clubWalletLog.distributed.isFalse();
    }

    private BooleanExpression deposited() {
        return clubWalletLog.detail.eq(Detail.DEPOSIT);
    }

    private BooleanExpression clubIdEq(Long clubId) {
        return clubWalletLog.club.id.eq(clubId);
    }

    private BooleanExpression userIdEq(Long userId) {
        return userWalletLog.user.id.eq(userId);
    }
}
