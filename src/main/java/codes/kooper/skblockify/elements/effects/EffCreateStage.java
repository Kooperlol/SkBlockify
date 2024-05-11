package codes.kooper.skblockify.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import codes.kooper.blockify.Blockify;
import codes.kooper.blockify.models.Audience;
import codes.kooper.blockify.models.Stage;
import codes.kooper.blockify.types.BlockifyPosition;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Name("Create Stage")
@Description("Creates a stage with a name, two locations, a world, and a set of players")
@Examples("create stage with name \"lobby\" between {_loc1} and {_loc2} in world(\"world\") for all players")
@Since("1.0.0")
public class EffCreateStage extends Effect {
    private Expression<String> name;
    private Expression<Location> loc1, loc2;
    private Expression<World> world;
    private Expression<Player> players;

    static {
        Skript.registerEffect(EffCreateStage.class, "create [a blockify] stage (with name|named) %string% between %location% and %location% in %world% for %players%");
    }

    @Override
    protected void execute(@NotNull Event event) {
        Set<Player> playersSet = new HashSet<>(List.of(this.players.getAll(event)));
        Blockify.getInstance().getStageManager().createStage(new Stage(name.getSingle(event), world.getSingle(event), BlockifyPosition.fromLocation(Objects.requireNonNull(loc1.getSingle(event))), BlockifyPosition.fromLocation(Objects.requireNonNull(loc2.getSingle(event))), new Audience(playersSet)));
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean b) {
        return "Create stage with name: " + name.toString(event, b) + " between locations: " + loc1.toString(event, b) + " and " + loc2.toString(event, b) + " in world: " + world.toString(event, b) + " for players: " + players.toString(event, b);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, @NotNull Kleenean kleenean, SkriptParser.@NotNull ParseResult parseResult) {
        name = (Expression<String>) expressions[0];
        loc1 = (Expression<Location>) expressions[1];
        loc2 = (Expression<Location>) expressions[2];
        world = (Expression<World>) expressions[3];
        players = (Expression<Player>) expressions[4];
        return (name != null && loc1 != null && loc2 != null && world != null && players != null);
    }

}
