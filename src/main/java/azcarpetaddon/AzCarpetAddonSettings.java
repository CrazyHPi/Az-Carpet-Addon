package azcarpetaddon;

import carpet.settings.ParsedRule;
import carpet.settings.Rule;
import carpet.settings.Validator;
import carpet.utils.Messenger;
import net.minecraft.server.command.ServerCommandSource;

import static carpet.settings.RuleCategory.*;

public class AzCarpetAddonSettings {

    public static final String AZ = "AZ";

    @Rule(
            desc = "Stop update suppression from crashing the server",
            category = {BUGFIX, AZ}
    )
    public static boolean updateSuppressionCrashFix = false;
}
