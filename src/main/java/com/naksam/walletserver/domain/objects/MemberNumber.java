package com.naksam.walletserver.domain.objects;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MemberNumber implements Serializable {
    private int count;

    private final static int MIN = 1;

    protected MemberNumber() {
        this(1);
    }

    public MemberNumber(int count) {
        validate(count);
        this.count = count;
    }

    private void validate(int count) {
        if (countIsLowerThanMin(count)) {
            throw new RuntimeException("모임원은 최소 1명이 필요합니다");
        }
    }

    private boolean countIsLowerThanMin(int count) {
        return count  < MIN;
    }

    public int count() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberNumber that = (MemberNumber) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
