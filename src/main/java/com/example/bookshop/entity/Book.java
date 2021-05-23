package com.example.bookshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    private double price;

    @NotEmpty
    @NotNull
    @Column(unique = true)
    private String name;
    private String img;

    @JsonIgnore
    private boolean bought = false;

    @JsonIgnore
    private Date dateOfBought;

    @GeneratedValue
    @JsonIgnore
    private UUID licenseCode;
}
