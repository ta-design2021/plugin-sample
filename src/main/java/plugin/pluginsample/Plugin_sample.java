package plugin.pluginsample;
//
//import org.bukkit.Bukkit;
//import org.bukkit.FireworkEffect;
//import org.bukkit.FireworkEffect.Type;
//import org.bukkit.World;
//import org.bukkit.entity.Firework;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.player.PlayerToggleSneakEvent;
//import org.bukkit.inventory.meta.FireworkMeta;
//import org.bukkit.plugin.java.JavaPlugin;

//　public final class Plugin_sample extends JavaPlugin {
//
//    @Override
//    public void onEnable() {
//        // Plugin startup logic
//
//    }
//
//    @Override
//    public void onDisable() {
//        // Plugin shutdown logic
//    }
//}

//package plugin.sample;

    import java.io.IOException;
    import java.math.BigInteger;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.util.Arrays;
    import java.util.List;
    import java.util.Objects;
    import org.bukkit.Bukkit;
    import org.bukkit.Color;
    import org.bukkit.ChatColor;
    import org.bukkit.FireworkEffect;
    import org.bukkit.FireworkEffect.Type;
    import org.bukkit.World;
    import org.bukkit.entity.Firework;
    import org.bukkit.entity.Player;
    import org.bukkit.event.EventHandler;
    import org.bukkit.event.Listener;
    import org.bukkit.event.player.PlayerBedEnterEvent;
    import org.bukkit.event.player.PlayerJoinEvent;
    import org.bukkit.event.player.PlayerToggleSneakEvent;
    import org.bukkit.inventory.ItemStack;
    import org.bukkit.inventory.meta.FireworkMeta;
    import org.bukkit.plugin.java.JavaPlugin;

public final class Plugin_sample extends JavaPlugin implements Listener {

    private int count;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);

//        List<Integer> numberList = List.of(1,2,3,4,5,6,7,8,9,10);
//        numberList.stream()
//            .filter(number -> number <= 5)
//            .forEach(System.out::println);

        getCommand("levelup").setExecutor(new LevelUpCommand());
    }

    /**
     * プレイヤーがスニークを開始/終了する際に起動されるイベントハンドラ。
     *
     * @param e イベント
     */
    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent e) throws IOException {
        // イベント発生時のプレイヤーやワールドなどの情報を変数に持つ。
        Player player = e.getPlayer();
        World world = player.getWorld();

        // BigInteger型の val を定義
        //  BigInteger val = new BigInteger(Integer.toString(count));

        List<Color> colorList = List.of(Color.RED, Color.BLUE, Color.YELLOW,Color.GREEN);
//        if(val.isProbablePrime(1)) {
            if(count % 2 == 0) {
                for(Color color : colorList) {

                    // System.out.println(val + "は素数です");

                    // 花火オブジェクトをプレイヤーのロケーション地点に対して出現させる。
                    Firework firework = world.spawn(player.getLocation(), Firework.class);

                    // 花火オブジェクトが持つメタ情報を取得。
                    FireworkMeta fireworkMeta = firework.getFireworkMeta();

                    // メタ情報に対して設定を追加したり、値の上書きを行う。
                    // 今回は青色で星型の花火を打ち上げる。
                    fireworkMeta.addEffect(
                        FireworkEffect.builder()
//                        .withColor(Color.BLUE)
                            .withColor(color)
                            .with(Type.BALL_LARGE)
                            .withFlicker()
                            .build());
//                    fireworkMeta.setPower(0);
                    fireworkMeta.setPower(1 * 3);

                    // 追加した情報で再設定する。
                    firework.setFireworkMeta(fireworkMeta);
                } //for
//                levelupコマンドでエラーが怒るのでコメントアウト
//                Path path = Path.of("fireworks.txt");
//                Files.writeString(path, "たまや〜");
//                player.sendMessage(Files.readString(path));
            } //if

      count ++;

    }

    @EventHandler
    public void  onPlayerBedEnter(PlayerBedEnterEvent e) {
        Player player = e.getPlayer();
        ItemStack[] itemStacks = player.getInventory().getContents();
      Arrays.stream(itemStacks)
          .filter(item -> !Objects.isNull(item) && item.getMaxStackSize() == 64 && item.getAmount() < 64)
          .forEach(item -> item.setAmount(64));

        player.getInventory().setContents(itemStacks);
    }

//    joinイベント
//    @EventHandler
//    public void  onPlayerJoin(PlayerJoinEvent e) {
//        String playerName = e.getPlayer().getName();
//        e.setJoinMessage(ChatColor.BLUE + "Welcomeback," + playerName + "!!!");
//    }
}

