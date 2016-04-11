package pl.pickaxe.pickaxecore.util;

public class Txt {
  public static String[] format(Object... text) {
    String[] out = new String[text.length];
    int i = 0;
    for (Object txt : text) {
      String str = (String) txt;
      if (str == null) {
        continue;
      }
      str = str.replaceAll("<empty>", "");
      str = str.replaceAll("<black>", "\u00A70");
      str = str.replaceAll("<navy>", "\u00A71");
      str = str.replaceAll("<green>", "\u00A72");
      str = str.replaceAll("<teal>", "\u00A73");
      str = str.replaceAll("<red>", "\u00A74");
      str = str.replaceAll("<purple>", "\u00A75");
      str = str.replaceAll("<gold>", "\u00A76");
      str = str.replaceAll("<orange>", "\u00A76");
      str = str.replaceAll("<silver>", "\u00A77");
      str = str.replaceAll("<gray>", "\u00A78");
      str = str.replaceAll("<grey>", "\u00A78");
      str = str.replaceAll("<blue>", "\u00A79");
      str = str.replaceAll("<lime>", "\u00A7a");
      str = str.replaceAll("<aqua>", "\u00A7b");
      str = str.replaceAll("<rose>", "\u00A7c");
      str = str.replaceAll("<pink>", "\u00A7d");
      str = str.replaceAll("<yellow>", "\u00A7e");
      str = str.replaceAll("<white>", "\u00A7f");
      str = str.replaceAll("<magic>", "\u00A7k");
      str = str.replaceAll("<bold>", "\u00A7l");
      str = str.replaceAll("<strong>", "\u00A7l");
      str = str.replaceAll("<strike>", "\u00A7m");
      str = str.replaceAll("<strikethrough>", "\u00A7m");
      str = str.replaceAll("<under>", "\u00A7n");
      str = str.replaceAll("<underline>", "\u00A7n");
      str = str.replaceAll("<italic>", "\u00A7o");
      str = str.replaceAll("<em>", "\u00A7o");
      str = str.replaceAll("<reset>", "\u00A7r");

      // Color by semantic functionality
      str = str.replaceAll("<l>", "\u00A72");
      str = str.replaceAll("<logo>", "\u00A72");
      str = str.replaceAll("<a>", "\u00A76");
      str = str.replaceAll("<art>", "\u00A76");
      str = str.replaceAll("<n>", "\u00A77");
      str = str.replaceAll("<notice>", "\u00A77");
      str = str.replaceAll("<i>", "\u00A7e");
      str = str.replaceAll("<info>", "\u00A7e");
      str = str.replaceAll("<g>", "\u00A7a");
      str = str.replaceAll("<good>", "\u00A7a");
      str = str.replaceAll("<b>", "\u00A7c");
      str = str.replaceAll("<bad>", "\u00A7c");

      str = str.replaceAll("<k>", "\u00A7b");
      str = str.replaceAll("<key>", "\u00A7b");

      str = str.replaceAll("<v>", "\u00A7d");
      str = str.replaceAll("<value>", "\u00A7d");
      str = str.replaceAll("<h>", "\u00A7d");
      str = str.replaceAll("<highlight>", "\u00A7d");

      str = str.replaceAll("<c>", "\u00A7b");
      str = str.replaceAll("<command>", "\u00A7b");
      str = str.replaceAll("<p>", "\u00A73");
      str = str.replaceAll("<parameter>", "\u00A73");

      // str = ChatColor.translateAlternateColorCodes('&', str);
      // str = ChatColor.translateAlternateColorCodes('§', str);

      str = str.replaceAll("&&", "&");
      str = str.replaceAll("§§", "§");
      out[i] = str;
      i++;
    }
    return out;
  }
}
