package com.example.bookshop.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue
    @Column(unique=true)
    private Long id;

    @NotNull
    private double price;

    @NotEmpty
    @NotNull
    @Column(unique = true)
    private String name;
    private String img;
}
