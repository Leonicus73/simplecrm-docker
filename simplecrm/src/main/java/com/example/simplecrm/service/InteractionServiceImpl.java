package com.example.simplecrm.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.simplecrm.entity.Interaction;
import com.example.simplecrm.exception.InteractionNotFoundException;
import com.example.simplecrm.repository.InteractionRepository;

@Primary
@Service
public class InteractionServiceImpl implements InteractionService {

    private final InteractionRepository interactionRepository;

    public InteractionServiceImpl(InteractionRepository interactionRepository) {
        this.interactionRepository = interactionRepository;
    }

    // CREATE
    @Override
    public Interaction saveInteraction(Interaction interaction) {
        return interactionRepository.save(interaction);
    }

    // READ ONE
    @Override
    public Interaction getInteraction(Long id) {
        Interaction interaction = interactionRepository.findById(id)
                .orElseThrow(() -> new InteractionNotFoundException(id));
        return interaction;
    }

    // READ ALL
    @Override
    public List<Interaction> getAllInteractions() {
        return interactionRepository.findAll();
    }

    // UPDATE
    @Override
    public Interaction updateInteraction(Long id, Interaction interaction) {
        Interaction interactionToUpdate = interactionRepository.findById(id).get();
        interactionToUpdate.setRemarks(interaction.getRemarks());
        interactionToUpdate.setInteractionDate(interaction.getInteractionDate());
        return interactionRepository.save(interactionToUpdate);
    }
    // public Interaction updateInteraction(Long id, Interaction interaction) {
    // if (interactionRepository.existsById(id)) {
    // interaction.setId(id);
    // return interactionRepository.save(interaction);
    // }
    // return null;
    // }

    // DELETE
    @Override
    public void deleteInteraction(Long id) {
        interactionRepository.deleteById(id);
    }

}
