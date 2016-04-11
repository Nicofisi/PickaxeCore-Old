package pl.pickaxe.pickaxecore;

import java.util.ArrayList;

import pl.pickaxe.pickaxecore.entity.PickaxePlugin;

public class PickaxeCore extends PickaxePlugin {

  // -------------------------------------------- //
  // INSTANCE & CONSTRUCT
  // -------------------------------------------- //

  private static PickaxeCore i;

  public static PickaxeCore get() {
    return i;
  }

  public PickaxeCore() {
    PickaxeCore.i = this;
  }

  public static ArrayList<PickaxePlugin> ppls = new ArrayList<PickaxePlugin>();

  // -------------------------------------------- //
  // OVERRIDE
  // -------------------------------------------- //

  @Override
  public void onEnableInner() {
    String test = "Dawno dawno temu try";
    log(test);
    logWarning(test);
    logError(test);
    logUpdater(test);
    logRaw(test);
    logDefault(test);
    logNotice(test);
  }

  @Override
  public void onDisableInner() {
    
  }
  
  // -------------------------------------------- //
  // API FOR PLUGINS
  // -------------------------------------------- //
  
  public static void registerPlugin(PickaxePlugin pp) {
    ppls.add(pp);
  }

  public PickaxePlugin getPlugin(String name) {
    for (int i = 0; i < ppls.size(); i++) {
      PickaxePlugin pl = ppls.get(i);
      if (pl.toString() == name) {
        return pl;
      }
    }
    return null;
  }
  

}
