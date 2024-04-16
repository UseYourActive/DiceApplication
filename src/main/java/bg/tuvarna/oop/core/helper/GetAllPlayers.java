package bg.tuvarna.oop.core.helper;

import bg.tuvarna.oop.persistence.entities.Player;
import bg.tuvarna.oop.persistence.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GetAllPlayers {
    private final PlayerRepository playerRepository;

    public List<String> getPlayers(){
        return this.playerRepository.findAll().stream().map(player -> player.getId().toString()).toList();
    }

    public Map<String, String> getPlayerInfo(Long playerId){
        Player player = playerRepository.findById(playerId).orElseThrow();
        Map<String, String> playerInfo = new HashMap<>();
        playerInfo.put("firstName", player.getFirstName());
        playerInfo.put("lastName", player.getLastName());
        playerInfo.put("egn", player.getEgn());
        return playerInfo;
    }
}
