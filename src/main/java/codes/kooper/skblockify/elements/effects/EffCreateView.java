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
import codes.kooper.blockify.models.Pattern;
import codes.kooper.blockify.models.Stage;
import codes.kooper.blockify.models.View;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Name("Create View")
@Description("Creates a view with a name, a stage, a pattern, and a breakable flag")
@Examples("create view for stage {_stage} with name \"view1\" with pattern \"stone:0.5|dirt:0.3|coal_ore:0.2\" that is breakable")
@Since("1.0.0")
public class EffCreateView extends Effect {
    private Expression<Stage> stage;
    private Expression<String> name, pattern;
    private Expression<Boolean> breakable;

    static {
        Skript.registerEffect(EffCreateView.class, "create [a blockify] view (for|in) %stage% (with name|named) %string% with pattern %string% that (1¦is|2¦is(n't| not)) breakable");
    }

    @Override
    protected void execute(@NotNull Event event) {
        Stage stage = this.stage.getSingle(event);
        String name = this.name.getSingle(event);
        String pattern = this.pattern.getSingle(event);
        boolean breakable = Boolean.TRUE.equals(this.breakable.getSingle(event));
        if (stage != null && name != null && pattern != null) {
            stage.addView(new View(name, stage, new Pattern(parseMaterialValues(pattern)), breakable));
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean b) {
        return "Create view with expression stage " + stage.toString(event, b) + " with name " + name.toString(event, b) + " with pattern " + pattern.toString(event, b) + " and breakable " + breakable.toString(event, b);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int i, @NotNull Kleenean kleenean, SkriptParser.@NotNull ParseResult parseResult) {
        stage = (Expression<Stage>) expressions[0];
        name = (Expression<String>) expressions[1];
        pattern = (Expression<String>) expressions[2];
        breakable = (Expression<Boolean>) expressions[3];
        return (stage != null && name != null && pattern != null && breakable != null);
    }

    private Map<BlockData, Double> parseMaterialValues(String input) {
        Map<BlockData, Double> materialValueMap = new HashMap<>();
        String[] entries = input.split("\\|");
        for (String entry : entries) {
            String[] parts = entry.split(":");
            if (parts.length != 2) {
                continue;
            }
            String materialName = parts[0];
            String valueStr = parts[1];
            if (Material.getMaterial(materialName) == null) {
                continue;
            }
            BlockData blockData = Objects.requireNonNull(Material.getMaterial(materialName)).createBlockData();
            Double value = Double.parseDouble(valueStr);
            materialValueMap.put(blockData, value);
        }
        return materialValueMap;
    }

}
