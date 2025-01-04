package ru.netology;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Game {
    public final List<Player> players;

    public Game() {
        this.players = new ArrayList<>();
    }

    public void register(Player player) {
        if (!players.contains(player)) {
            players.add(player);
        } else {
            throw new IllegalArgumentException("Игрок уже зарегистрирован.");
        }
    }

    public int round(String playerName1, String playerName2) throws NotRegisteredException {
        Player player1 = findPlayerByName(playerName1);
        Player player2 = findPlayerByName(playerName2);

        if (player1 == null || player2 == null) {
            throw new NotRegisteredException("Один из игроков не зарегистрирован.");
        }

        if (player1.getStrength() > player2.getStrength()) {
            return 1; // Победа первого игрока
        } else if (player1.getStrength() < player2.getStrength()) {
            return 2; // Победа второго игрока
        } else {
            return 0; // Ничья
        }
    }

    private Player findPlayerByName(String name) {
        Optional<Player> foundPlayer = players.stream().filter(p -> p.getName().equals(name)).findFirst();
        return foundPlayer.orElse(null);
    }
}