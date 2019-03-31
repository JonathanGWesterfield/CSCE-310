import java.util.Collection;

public class GameDAO
{
	public interface GameDao {

		Collection<DBGame> findGames(Integer gameId);
	}

}
