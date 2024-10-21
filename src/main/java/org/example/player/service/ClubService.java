package org.example.player.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import org.example.player.entity.Club;
import org.example.player.repository.ClubRepository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class ClubService {
    private final ClubRepository clubRepository;

    @Inject
    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public Optional<Club> findClubById(UUID id) {
        return clubRepository.find(id);
    }
    public List<Club> findAllClubs() {
        return clubRepository.findAll();
    }
    public void createClub(Club club) {
        clubRepository.create(club);
    }
    public void deleteClub(Club club) {
        clubRepository.delete(club);
    }
    public void updateClub(Club club) {
        clubRepository.update(club);
    }
    
    
}
