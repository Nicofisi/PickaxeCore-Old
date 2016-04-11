package pl.pickaxe.pickaxecore.entity;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import pl.pickaxe.pickaxecore.PickaxeCore;
import pl.pickaxe.pickaxecore.enums.PickaxeLevel;
import pl.pickaxe.pickaxecore.util.Metrics;
import pl.pickaxe.pickaxecore.util.Txt;

public abstract class PickaxePlugin extends JavaPlugin {

  // -------------------------------------------- //
  // FIELDS
  // -------------------------------------------- //

  private static PickaxePlugin pp;
  public boolean debug;
  public long enableStart;
  public Metrics metrics;

  public static PickaxePlugin get() {
    return pp;
  }

  // -------------------------------------------- //
  // ENABLING & DISABLING PLUGINS
  // -------------------------------------------- //

  @Override
  public void onEnable() {

    // Stop enabling if the preEnable method failed
    if (!this.preEnable(this)) {
      return;
    }

    // Register a plugin that uses the Pickaxe api
    PickaxeCore.registerPlugin(this);

    // Schedule enabling Metrics
    Bukkit.getScheduler().runTaskLaterAsynchronously(this, enableMetrics(), 200L);

    // Call onEnableInner() method
    this.onEnableInner();

    // Call postEnable() method
    this.postEnable();
  }

  public boolean preEnable(PickaxePlugin pp) {

    // Cool colored info that the plugin has started enabling itself
    log("<yellow>=== ENABLE <green>START<yellow> ===");

    // Start enable time counter
    this.enableStart = System.nanoTime();

    // Temporary
    this.debug = true;
    
    // Set the variable used to PluginName.get()
    PickaxePlugin.pp = pp;
    
    // Create an instance of Metrics
    try {
      this.metrics = new Metrics(pp, 8);
    } catch (IOException e) {}
    return true;
  }

  public void onEnableInner() {
    // Custom overriden stuff for certain plugin
  }

  public void postEnable() {
    long took = System.nanoTime() - this.enableStart;
    double tookMs = ((double) took) / 1000000;
    java.text.DecimalFormat df = new java.text.DecimalFormat();
    df.setMinimumFractionDigits(1);
    df.setMaximumFractionDigits(1);
    log(ChatColor.YELLOW + "=== ENABLE " + ChatColor.GREEN + "COMPLETE" + ChatColor.YELLOW
        + " (Took " + ChatColor.LIGHT_PURPLE + df.format(tookMs) + "ms" + ChatColor.YELLOW
        + ") ===");
  }

  @Override
  public void onDisable() {
    onDisableInner();
    this.setEnabled(false);
    log("Disabled");
  }

  public void onDisableInner() {
    // Custom overriden stuff for certain plugin
  }

  // -------------------------------------------- //
  // METRICS
  // -------------------------------------------- //

  public Metrics getMetrics() {
    return this.metrics;
  }

  private Runnable enableMetrics() {
    return new Runnable() {
      @Override
      public void run() {
        metrics.start();
      }
    };
  }

  // -------------------------------------------- //
  // LOGGING
  // -------------------------------------------- //

  // Methods to make the logging easier
  
  public void log(Object... msg) {
    log(PickaxeLevel.INFO, msg);
  }

  public void logInfo(Object... msg) {
    log(PickaxeLevel.INFO, msg);
  }

  public void logWarning(Object... msg) {
    log(PickaxeLevel.WARNING, msg);
  }

  public void logError(Object... msg) {
    log(PickaxeLevel.ERROR, msg);
  }

  public void logUpdater(Object... msg) {
    log(PickaxeLevel.UPDATER, msg);
  }

  public void logNotice(Object... msg) {
    log(PickaxeLevel.NOTICE, msg);
  }

  public void logRaw(Object... msg) {
    String m = "";
    for (Object l : msg) {
      m = m + (String) l + " ";
    }
    m.substring(0, m.length() - 1);
    Bukkit.getConsoleSender().sendMessage(m);
  }

  public void logDefault(Object... msg) {
    log(PickaxeLevel.DEFAULT, msg);
  }

  // Actual log method
  
  public void log(PickaxeLevel lvl, Object... msg) {
    String[] coloured = Txt.format(msg);
    if (coloured == null) {
      return;
    }
    String col = "";
    StringBuffer strB = new StringBuffer(col);
    if (coloured.length > 1) {

      for (String l : coloured) {
        strB.append(l);
      }
      col = strB.toString();
    } else if (coloured.length == 1) {
      col = coloured[0];
    } else {
      return;
    }
    ConsoleCommandSender c = Bukkit.getConsoleSender();
    if (lvl == PickaxeLevel.RAW) {
      c.sendMessage(col);
    }
    String name = this.getDescription().getName();
    String ver = this.getDescription().getVersion();
//    if (this.debug == true) {}
    if (lvl == PickaxeLevel.INFO) {
      c.sendMessage(Txt.format("<teal>[<aqua>" + name + " " + ver + "<teal>]<reset> " + col));
    } else if (lvl == PickaxeLevel.WARNING) {
      c.sendMessage(Txt
          .format("<purple>[<pink>" + name + " " + ver + " WARNING" + "<purple>]<reset> " + col));
    } else if (lvl == PickaxeLevel.ERROR) {
      c.sendMessage(ChatColor.DARK_RED + "[" + ChatColor.RED + name + " " + ver + "  ERROR "
          + ChatColor.DARK_RED + "] " + ChatColor.RESET + col);
    } else if (lvl == PickaxeLevel.UPDATER) {
      c.sendMessage(ChatColor.DARK_GREEN + "[" + ChatColor.GREEN + name + " " + ver + " UPDATER"
          + ChatColor.DARK_GREEN + "] " + ChatColor.RESET + col);
    } else if (lvl == PickaxeLevel.NOTICE) {
      c.sendMessage(Txt.format(
          "<orange>[<yellow>" + name + " " + ver + "  NOTICE " + "<orange>]<reset> " + col));
    } else if (lvl == PickaxeLevel.DEFAULT) {
      c.sendMessage("[" + name + "] " + col);
    }
  }
}
