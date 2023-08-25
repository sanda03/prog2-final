package com.sanda.prog2.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class Member extends Person{
    private Integer idMember;
    public Member( Integer idMember,String name, String firstName) {
        super(name, firstName);
        this.idMember = idMember;
    }

    @Override
    public String toString() {
        return super.toString() + "Member{" +
                "idMember=" + idMember +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member member)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getIdMember(), member.getIdMember());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getIdMember());
    }
}
