import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.Game;
import ru.netology.NotRegisteredException;
import ru.netology.Player;

class GameTest {

    @Test
    public void testRegisterNewPlayer() {
        Game game = new Game();
        Player player = new Player(1, "Ева", 10);

        game.register(player);

        Assertions.assertEquals(1, game.players.size());
        Assertions.assertEquals(true, game.players.contains(player));
    }

    @Test
    public void testRegisterDuplicatePlayer() {
        Game game = new Game();
        Player player = new Player(1, "Ева", 10);

        game.register(player);

        try {
            game.register(player);
            Assertions.fail("Expected an exception to be thrown");
        } catch (IllegalArgumentException e) {
            Assertions.assertEquals("Игрок уже зарегистрирован.", e.getMessage());
        }
    }

    @Test
    public void testRoundWinner2() throws NotRegisteredException {
        Game game = new Game();
        Player Eva = new Player(1, "Ева", 10);
        Player Cris = new Player(2, "Крис", 20);

        game.register(Eva);
        game.register(Cris);

        int result = game.round("Ева", "Крис");

        Assertions.assertEquals(2, result);
    }

    @Test
    public void testRoundWinner1() throws NotRegisteredException {
        Game game = new Game();
        Player Eva = new Player(1, "Ева", 10);
        Player Cris = new Player(2, "Крис", 9);

        game.register(Eva);
        game.register(Cris);

        int result = game.round("Ева", "Крис");

        Assertions.assertEquals(1, result);
    }

    @Test
    public void testRoundDraw() throws NotRegisteredException {
        Game game = new Game();
        Player Eva = new Player(1, "Ева", 10);
        Player Sofia = new Player(3, "Софья", 10);

        game.register(Eva);
        game.register(Sofia);

        int result = game.round("Ева", "Софья");

        Assertions.assertEquals(0, result);
    }

    @Test
    public void testRoundNotRegistered() {
        Game game = new Game();
        Player Eva = new Player(1, "Ева", 10);

        game.register(Eva);

        try {
            game.round("Ева", "Крис");
            Assertions.fail("Expected an exception to be thrown");
        } catch (NotRegisteredException e) {
            Assertions.assertEquals("Один из игроков не зарегистрирован.", e.getMessage());
        }
    }

    @Test
    public void testRoundBothNotRegistered() {
        Game game = new Game();

        try {
            game.round("Ева", "Крис");
            Assertions.fail("Expected an exception to be thrown");
        } catch (NotRegisteredException e) {
            Assertions.assertEquals("Один из игроков не зарегистрирован.", e.getMessage());
        }
    }

    @Test
    public void testGetId() {
        int expectedId = 123;
        Player player = new Player(expectedId, "Лея", 50);

        int actualId = player.getId();

        Assertions.assertEquals(expectedId, actualId, "ID должно совпадать.");
    }
}