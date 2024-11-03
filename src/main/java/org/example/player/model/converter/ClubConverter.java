package org.example.player.model.converter;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import org.example.factories.ModelFunctionFactory;
import org.example.player.entity.Club;
import org.example.player.model.ClubModel;
import org.example.player.model.ClubsModel;
import org.example.player.service.ClubService;
import jakarta.inject.Inject;

import java.util.Optional;
import java.util.UUID;

@FacesConverter(value = "clubConverter", managed = true)
@ApplicationScoped
public class ClubConverter implements Converter<ClubModel> {


    private final ClubService clubService;
    private final ModelFunctionFactory factory;

    @Inject
    public ClubConverter(ClubService service, ModelFunctionFactory factory) {
        this.clubService = service;
        this.factory = factory;
    }


    @Override
    public ClubModel getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        Optional<Club> club = clubService.findClubById(UUID.fromString(value));
        return club.map(factory.clubToModelFunction()).orElse(null);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, ClubModel club) {
        return club == null ? "" : club.getId().toString();
    }
}