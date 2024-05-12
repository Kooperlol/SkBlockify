package codes.kooper.skblockify.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import codes.kooper.blockify.models.Stage;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Mining Speed of Player in Stage")
@Description("Returns the mining speed of a player in a stage.")
@Examples("set {_speed} to the mining speed of player in stage {_stage}")
@Since("1.0.0")
public class ExprPlayerMiningSpeed extends SimpleExpression<Float> {
    private Expression<Player> player;
    private Expression<Stage> stage;

    static {
        Skript.registerExpression(ExprPlayerMiningSpeed.class, Float.class, ExpressionType.SIMPLE, "[the] mining speed of %player% in stage %stage%");
    }

    @Override
    protected Float @NotNull [] get(@NotNull Event event) {
        Player player = this.player.getSingle(event);
        Stage stage = this.stage.getSingle(event);
        if (player == null || stage == null) {
            return new Float[0];
        }
        return new Float[] {stage.getAudience().getMiningSpeeds().get(player)};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Float> getReturnType() {
        return Float.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "Mining speed of player in stage with player expression: " + player.toString(event, debug) + " and stage expression: " + stage.toString(event, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        player = (Expression<Player>) expressions[0];
        stage = (Expression<Stage>) expressions[1];
        return (player != null && stage != null);
    }

}
