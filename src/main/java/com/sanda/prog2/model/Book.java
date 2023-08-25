package com.sanda.prog2.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Book {
    private Integer idBook;
    private String title, description;
}