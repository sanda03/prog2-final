package com.sanda.prog2.model;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Borrow {
    private Integer idBorrow, idBook, idMember;
    private Timestamp startDate,endDate;
    private Boolean isReturned;
}