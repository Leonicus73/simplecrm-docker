package com.example.simplecrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.simplecrm.entity.Interaction;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long> {
}
