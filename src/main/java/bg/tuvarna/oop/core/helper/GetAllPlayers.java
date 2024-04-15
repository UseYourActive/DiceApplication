package bg.tuvarna.oop.core.helper;

import bg.tuvarna.oop.persistence.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllPlayers {
    private final PlayerRepository playerRepository;

    public List<String> getPlayers(){
        return this.playerRepository.findAll().stream().map(player -> player.getId().toString()).toList();
    }
}
