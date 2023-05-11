package selfish;

import java.io.Serializable;
/**
 * The Astronaut class represents an astronaut in the game.
 */
public class Astronaut implements Serializable{
    private String name;
    private GameEngine game;
    private static final long serialVersionUID = 1234567890L;

    /**
     * Constructs an Astronaut with the specified name and game.
     * @param name the name of the astronaut
     * @param game the game engine
     */
    public Astronaut(String name, GameEngine game) {
        this.name = name;
        this.game = game;
    }

    /**
     * Returns a string representation of the astronaut.
     * @return a string representation of the astronaut
     */
    @Override
    public String toString() {
        return name;
    }
}
