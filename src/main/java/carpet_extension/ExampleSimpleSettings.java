package carpet_extension;

import carpet.settings.ParsedRule;
import carpet.settings.Rule;
import carpet.settings.Validator;
import carpet.utils.Messenger;
import net.minecraft.server.command.ServerCommandSource;

import static carpet.settings.RuleCategory.BUGFIX;
import static carpet.settings.RuleCategory.CREATIVE;

/**
 * Here is your example Settings class you can plug to use carpetmod /carpet settings command
 */
public class ExampleSimpleSettings
{
    public static final String Az = "Az";
    /**
     *  Custom validator class for your setting. If validate returns null - settings is not changed.
     */
    private static class CheckValue extends Validator<Integer>
    {
        @Override
        public Integer validate(ServerCommandSource source, ParsedRule<Integer> currentRule, Integer newValue, String typedString)
        {
            Messenger.m(source, "rb Congrats, you just changed a setting to "+newValue);
            return newValue < 20000000 ? newValue : null;
        }
    }

    /**
     *  Simple numeric setting, no use otherwise
     */
    @Rule(
            desc = "Example numerical setting",
            options = {"32768", "250000", "1000000"},
            validate = {Validator.NONNEGATIVE_NUMBER.class, CheckValue.class},
            category = {CREATIVE, "eg"}
    )
    public static int uselessNumericalSetting = 32768;


    /**
     * You can define your own catergories. It makes sense to create new category for all settings in your mod.
     */
    @Rule(desc="makes mobs dance Makarena", category = {"fun", "eg"})
    public static boolean makarena = false;

    @Rule(
            desc = "Fixes updates suppression causing server crashes.",
            category = {BUGFIX, Az}
    )
    public static boolean updateSuppressionCrashFix = false;

}
