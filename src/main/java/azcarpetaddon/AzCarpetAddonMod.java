package azcarpetaddon;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class AzCarpetAddonMod implements  ModInitializer{
    private static final String MOD_ID = "az-carpet-addon";
    private static String version;

    @Override
    public void onInitialize(){
        version = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(RuntimeException::new).getMetadata().getVersion().getFriendlyString();
        AzCarpetAddonServer.loadExtension();
    }

    public static String getVersion(){
        return version;
    }

    public static String getModId(){
        return MOD_ID;
    }
}
