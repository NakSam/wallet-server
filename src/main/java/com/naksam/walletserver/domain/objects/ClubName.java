package com.naksam.walletserver.domain.objects;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ClubName implements Serializable {
    private String name;

    private final static int MIN_LENGTH = 3;

    private final static int MAX_LENGTH = 30;

    protected ClubName() {

    }

    public ClubName(String name) {
        validate(name);
        this.name = name;
    }

    public void validate(String name) {
        if (clubNameIsEmpty(name)) {
            throw new RuntimeException("모임 이름은 필수값입니다");
        }

        if (clubNameLengthNotInRange(name)) {
            throw new RuntimeException("모임 이름은 5자에서 30자 사이여야 합니다");
        }
    }

    private boolean clubNameIsEmpty(String name) {
        return name == null || name.trim()
                .isEmpty();
    }

    private boolean clubNameLengthNotInRange(String name) {
        return name.length() < MIN_LENGTH || name.length() > MAX_LENGTH;
    }

    public String content() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClubName clubName = (ClubName) o;
        return Objects.equals(name, clubName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
