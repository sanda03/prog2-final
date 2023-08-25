package com.sanda.prog2.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class Author extends Person{
    private Integer idAuthor;
    public Author(Integer idAuthor,String name, String firstName) {
        super(name, firstName);
        this.idAuthor = idAuthor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author author)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getIdAuthor(), author.getIdAuthor());
    }

    @Override
    public String toString() {
        return super.toString()+"Author{" +
                "idAuthor=" + idAuthor +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getIdAuthor());
    }
}
