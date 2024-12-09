package org.example.player.service;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.example.club.Club;
import org.example.player.repository.api.ClubRepository;
import org.example.user.entity.UserRoles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@LocalBean
@Stateless
@NoArgsConstructor(force = true)
@Log
public class ClubService {
    private final ClubRepository clubRepository;

    @Inject
    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @RolesAllowed(UserRoles.USER)
    public Optional<Club> findClubById(UUID id) {
        Optional<Club> club = clubRepository.find(id);
        return club;
    }

    @PermitAll
    public List<Club> findAllClubs() {
        return clubRepository.findAll();
    }

    @RolesAllowed(UserRoles.ADMIN)
    public void createClub(Club club) {
        clubRepository.create(club);
    }

    @RolesAllowed(UserRoles.ADMIN)
    public void deleteClub(UUID uuid) {
        clubRepository.delete(clubRepository.find(uuid).orElseThrow());
    }

    @RolesAllowed(UserRoles.USER)
    public void updateClub(Club club) {
        clubRepository.update(club);
    }
}
