package codes.kooper.skblockify.elements;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import codes.kooper.blockify.Blockify;
import codes.kooper.blockify.models.Stage;
import codes.kooper.blockify.models.View;

import javax.annotation.Nullable;

public class Types {

    static {
        Classes.registerClass(new ClassInfo<>(Stage.class, "stage")
                .user("stages?")
                .name("Stage")
                .description("Represents a raid from a pillager raid on a village.")
                .examples("on raid start A raid has started at level %omen level of event-raid%\"")
                .defaultExpression(new EventValueExpression<>(Stage.class))
                .parser(new Parser<>() {
                    @Override
                    @Nullable
                    public Stage parse(String input, ParseContext context) {
                        if (!Blockify.instance.getStageManager().hasStage(input)) return null;
                        return Blockify.instance.getStageManager().getStage(input);
                    }

                    @Override
                    public boolean canParse(ParseContext context) {
                        return true;
                    }

                    @Override
                    public String toString(Stage o, int flags) {
                        return o.getName();
                    }

                    @Override
                    public String toVariableNameString(Stage o) {
                        return toString(o, 0);
                    }
                }));

        Classes.registerClass(new ClassInfo<>(View.class, "view")
                .user("views?")
                .name("View")
                .description("Represents a view in a stage.")
                .examples("on blockify break: if event-view is \"main\": broadcast \"You broke a block in the main view!\"")
                .defaultExpression(new EventValueExpression<>(View.class))
                .parser(new Parser<>() {
                    @Override
                    public boolean canParse(ParseContext context) {
                        return false;
                    }

                    @Override
                    public String toString(View o, int flags) {
                        return o.getName();
                    }

                    @Override
                    public String toVariableNameString(View o) {
                        return toString(o, 0);
                    }
                }));
    }

}
