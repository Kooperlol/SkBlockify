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
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Audience of Stage")
@Description("Gets the players in a stage.")
@Examples("set {_players::*} to audience of {_stage}")
@Since("1.0.0")
public class ExprStageAudience extends SimpleExpression<OfflinePlayer> {
    private Expression<Stage> stage;

    static {
        Skript.registerExpression(ExprStageAudience.class, OfflinePlayer.class, ExpressionType.SIMPLE, "[the] audience of %stage%");
    }

    @Override
    protected OfflinePlayer @NotNull [] get(@NotNull Event event) {
        Stage stage = this.stage.getSingle(event);
        if (stage == null) return new Player[0];
        return stage.getAudience().getPlayers().stream().map(Bukkit::getOfflinePlayer).toArray(OfflinePlayer[]::new);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NotNull Class<? extends OfflinePlayer> getReturnType() {
        return Player.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "Audience of stage expression with stage expression: " + stage.toString(event, debug);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        stage = (Expression<Stage>) expressions[0];
        return stage != null;
    }

}