package com.example.simplecrm.service;

import java.util.List;

import com.example.simplecrm.entity.Interaction;

public interface InteractionService {
    Interaction saveInteraction(Interaction interaction);
    Interaction getInteraction(Long id);
    List<Interaction> getAllInteractions();
    Interaction updateInteraction(Long id, Interaction interaction);
    void deleteInteraction(Long id);
}
