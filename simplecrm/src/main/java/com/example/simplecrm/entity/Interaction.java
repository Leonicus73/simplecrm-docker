package com.example.simplecrm.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
// @AllArgsConstructor

@Entity
@Table(name = "interactions")
public class Interaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(min = 3, max = 30, message = "Remarks must be between 1 and 30 characters")
    @Column(name = "remarks")
    private String remarks;
    
    @PastOrPresent(message = "Interaction date cannot be in the future")
    @Column(name = "interaction_date")
    private LocalDate interactionDate;

    // Interaction table is owner of this relationship
    // because it has the foreign key
    // Child table references parent table
    // Assuming Interaction is linked to a Customer
    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    // Getters and Setters
    // (You can use Lombok to reduce boilerplate if preferred)
}
