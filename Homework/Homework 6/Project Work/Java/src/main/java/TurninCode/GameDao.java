import java.util.Collection;

public interface GameDao {

	Collection<DBGame> findGames(Integer playerId);
}
