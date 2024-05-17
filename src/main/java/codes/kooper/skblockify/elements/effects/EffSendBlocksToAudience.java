package codes.kooper.skblockify.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.util.AsyncEffect;
import ch.njol.util.Kleenean;
import codes.kooper.blockify.models.Stage;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Send Blocks To Audience")
@Description("Send blocks to an audience in a stage.")
@Examples("send blocks to audience in stage \"stage\"")
@Since("1.0.0")
public class EffSendBlocksToAudience extends AsyncEffect {
    private Expression<Stage> stage;

    static {
        Skript.registerEffect(EffSendBlocksToAudience.class, "send blocks to audience in stage %stage%");
    }

    @Override
    protected void execute(@NotNull Event event) {
        Stage stageSingle = stage.getSingle(event);
        if (stageSingle == null) return;
        stageSingle.sendBlocksToAudience();
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean b) {
        return "send blocks to audience in stage " + stage;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int i, @NotNull Kleenean kleenean, SkriptParser.@NotNull ParseResult parseResult) {
        stage = (Expression<Stage>) expressions[0];
        return stage != null;
    }

}
