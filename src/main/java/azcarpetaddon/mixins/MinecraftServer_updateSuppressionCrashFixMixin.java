package carpet_extension.mixins;

import carpet.utils.Messenger;
import carpet_extension.ExampleSimpleSettings;
import carpet_extension.helpers.ThrowableSuppression;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.crash.CrashException;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public class MinecraftServer_updateSuppressionCrashFixMixin {
    @Redirect(method = "tickWorlds", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;tick(Ljava/util/function/BooleanSupplier;)V"))
    private void fixUpdateSuppressionCrashTick(ServerWorld serverWorld, BooleanSupplier shouldKeepTicking){
        if (!ExampleSimpleSettings.updateSuppressionCrashFix) {
            serverWorld.tick(shouldKeepTicking);
            return;
        }
        try {
            serverWorld.tick(shouldKeepTicking);
        } catch (CrashException e) {
            if (!(e.getCause() instanceof ThrowableSuppression)) throw e;
            logUpdateSuppression("world tick");
        } catch (ThrowableSuppression ignored) {
            logUpdateSuppression("world tick1");
        }
    }


    private void logUpdateSuppression(String tickPhase) {
        Messenger.print_server_message((MinecraftServer) (Object) this, "You just caused a server crash in " + tickPhase + ".");
    }
}
