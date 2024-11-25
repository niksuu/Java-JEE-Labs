package org.example.player.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.example.player.entity.Club;
import org.example.player.controller.api.ClubRepository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
@Log
public class ClubService {
    private final ClubRepository clubRepository;

    @Inject
    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public Optional<Club> findClubById(UUID id) {
        Optional<Club> club = clubRepository.find(id);
        return club;
    }

    public List<Club> findAllClubs() {
        return clubRepository.findAll();
    }
    @Transactional
    public void createClub(Club club) {
        clubRepository.create(club);
    }
    @Transactional
    public void deleteClub(Club club) {
        clubRepository.delete(club);
    }

    public void updateClub(Club club) {
        clubRepository.update(club);
    }
}
