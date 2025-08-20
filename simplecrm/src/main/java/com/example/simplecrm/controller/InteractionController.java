package com.example.simplecrm.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.simplecrm.entity.Interaction;
import com.example.simplecrm.service.InteractionService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/interactions")
public class InteractionController {

    private InteractionService interactionService;

    public InteractionController(InteractionService interactionService){
        this.interactionService = interactionService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Interaction> saveInteraction(@RequestBody Interaction interaction) {
        Interaction newInteraction = interactionService.saveInteraction(interaction); // More explicit method name
        return new ResponseEntity<>(newInteraction, HttpStatus.OK);
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<Interaction> getInteraction(@PathVariable Long id) {
        return new ResponseEntity<>(interactionService.getInteraction(id), HttpStatus.OK);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Interaction>> getAllInteractions() {
        return new ResponseEntity<>(interactionService.getAllInteractions(), HttpStatus.OK);
    }

    // UPDATE
    @PutMapping("{id}")
    public ResponseEntity<Interaction> updateInteraction(@PathVariable Long id, @RequestBody Interaction interaction) {
        Interaction newInteraction = interactionService.updateInteraction(id, interaction);
        return new ResponseEntity<>(newInteraction, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInteraction(@PathVariable Long id) {
        interactionService.deleteInteraction(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
