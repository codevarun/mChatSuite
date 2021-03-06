package in.mDev.MiracleM4n.mChatSuite;

import com.herocraftonline.heroes.Heroes;

import com.massivecraft.factions.Conf;

import in.mDev.MiracleM4n.mChatSuite.api.*;
import in.mDev.MiracleM4n.mChatSuite.commands.*;
import in.mDev.MiracleM4n.mChatSuite.configs.*;
import in.mDev.MiracleM4n.mChatSuite.events.*;
import in.mDev.MiracleM4n.mChatSuite.external.*;
import in.mDev.MiracleM4n.mChatSuite.util.Messanger;

import in.mDev.MiracleM4n.mChatSuite.channel.ChannelManager;
import net.milkbowl.vault.permission.Permission;

import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.dataholder.worlds.WorldsHolder;

import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import org.getspout.spoutapi.player.SpoutPlayer;

import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.io.File;
import java.net.Socket;
import java.util.*;

public class mChatSuite extends JavaPlugin {
    // Default Plugin Data
    public PluginManager pm;
    public PluginDescriptionFile pdfFile;

    // Configs
    public YamlConfiguration censor;
    public YamlConfiguration channels;
    public YamlConfiguration config;
    public YamlConfiguration info;
    public YamlConfiguration locale;

    // Files
    public File censorF;
    public File channelsF;
    public File configF;
    public File infoF;
    public File localeF;

    // External Class Relays
    API api;
    Parser parser;
    Writer writer;
    Reader reader;

    // External Messaging
    public BroadcastMessage bMessage;

    // GroupManager
    public WorldsHolder gmPermissionsWH;
    public Boolean gmPermissionsB = false;

    // PermissionsEX
    public PermissionManager pexPermissions;
    public Boolean PEXB = false;

    // PermissionsBukkit
    public Boolean PermissionBuB = false;

    // bPermissions
    public Boolean bPermB = false;

    // MobDisguise
    public Boolean mobD = false;

    // Factions
    public Boolean factionsB = false;

    // Heroes
    public Heroes heroes;
    public Boolean heroesB = false;

    // Vault
    public Permission vPerm;
    public Boolean vaultB = false;

    // ChannelManager
    public ChannelManager channelManager;

    // Optional mChatSuite only Info Support
    public Boolean useNewInfo = false;

    // Optional Old Nodular Style Formatting
    public Boolean useOldNodes = false;

    // Optional Leveled Nodes
    public Boolean useLeveledNodes = false;

    // API Only Boolean
    public Boolean mAPIOnly = false;

    // Alter Event Messages Boolean
    public Boolean alterEvents = true;
    public Boolean alterDMessages = true;

    // Add New Players Boolean
    public Boolean useAddDefault = false;

    // Info Related Variables
    public String mIDefaultGroup = "default";

    // Formatting
    public String varIndicator = "+";
    public String cusVarIndicator = "-";
    public String tabbedListFormat = "+p+dn+s";
    public String listCmdFormat = "+p+dn+s";
    public String chatFormat = "+p+dn+s&f: +m";
    public String nameFormat = "+p+dn+s&e";
    public String eventFormat = "+p+dn+s&e";
    public String meFormat = "* +p+dn+s&e +m";
    public String dateFormat = "HH:mm:ss";

    // Messages
    public String joinMessage = "has joined the game.";
    public String leaveMessage = "has left the game.";
    public String kickMessage = "has been kicked from the game. [ +r ]";
    public String deathInFire = "went up in flames.";
    public String deathOnFire = "burned to death.";
    public String deathLava = "tried to swim in lava.";
    public String deathInWall = "suffocated in a wall.";
    public String deathDrown = "drowned.";
    public String deathStarve = "starved to death.";
    public String deathCactus = "was pricked to death.";
    public String deathFall = "hit the ground too hard.";
    public String deathOutOfWorld = "fell out of the world.";
    public String deathGeneric = "died.";
    public String deathExplosion = "blew up.";
    public String deathMagic = "was killed by magic.";
    public String deathEntity = "was slain by +killer.";
    public String deathMobFormat = "a +killer";
    public String deathArrow = "was shot by +killer.";
    public String deathFireball = "was fireballed by +killer.";
    public String deathThrown = "was pummeled by +killer.";
    public String hMasterT = "The Great";
    public String hMasterF = "The Squire";

    // Strings
    public String eBroadcastIP = "ANY";

    // Booleans
    public Boolean spoutEnabled = true;
    public Boolean healthNotify = false;
    public Boolean healthAchievement = true;
    public Boolean spoutB = false;
    public Boolean mAFKHQ = true;
    public Boolean mChatEB = false;
    public Boolean useAFKList = false;
    public Boolean mChatPB = false;
    public Boolean spoutPM = false;
    public Boolean sJoinB = false;
    public Boolean sDeathB = false;
    public Boolean sQuitB = false;
    public Boolean sKickB = false;
    public Boolean useIPRestrict = true;
    public Boolean useGroupedList = true;
    public Boolean eBroadcast = false;

    // Numbers
    public Integer AFKTimer = 30;
    public Integer AFKKickTimer = 120;
    public Integer sJoinI = 30;
    public Integer sDeathI = 30;
    public Integer sQuitI = 30;
    public Integer sKickI = 30;
    public Integer eBroadcastPort = 1940;
    public Integer cLockRange = 3;

    // Other Config Stuff
    public Double chatDistance = -1.0;
    public String cLVars = "default,Default";
    public String listVar = "group";

    // Timers
    long sTime1;
    long sTime2;
    long sDiff;

    // Maps
    public HashMap<String, Location> AFKLoc = new HashMap<String, Location>();

    public HashMap<String, Boolean> chatt = new HashMap<String, Boolean>();
    public HashMap<String, Boolean> isAFK = new HashMap<String, Boolean>();
    public HashMap<String, Boolean> isConv = new HashMap<String, Boolean>();
    public HashMap<String, Boolean> isShouting = new HashMap<String, Boolean>();
    public HashMap<String, Boolean> isSpying = new HashMap<String, Boolean>();
    public HashMap<String, Boolean> isMuted = new HashMap<String, Boolean>();

    public HashMap<String, String> lastPMd = new HashMap<String, String>();
    public HashMap<String, String> getInvite = new HashMap<String, String>();
    public HashMap<String, String> chatPartner = new HashMap<String, String>();
    public HashMap<String, String> mPrefix = new HashMap<String, String>();

    public HashMap<String, Long> lastMove = new HashMap<String, Long>();

    public TreeMap<String, String> cVarMap = new TreeMap<String, String>();

    // Lists
    public ArrayList<Socket> queryList = new ArrayList<Socket>();

    public void onEnable() {
        // 1st Startup Timer
        sTime1 = new Date().getTime();

        // Initialize Plugin Data
        pm = getServer().getPluginManager();
        pdfFile = getDescription();

        // Initialize Configs
        initializeConfigs();

        // First we kill EssentialsChat
        killEss();

        // ChannelManager
        channelManager = new ChannelManager(this);

        // Initialize Class Relays
        initializeClasses();

        // Setup Configs
        setupConfigs();

        // Setup Plugins
        setupPlugins();

        // Setup Permissions
        setupPerms();

        // Register Events
        registerEvents();

        // Setup Tasks
        setupTasks();

        // Setup Commands
        setupCommands();

        // External Messaging
        bMessage = new BroadcastMessage(this);

        if (eBroadcast)
            if (bMessage.connect())
                bMessage.startListeners();

        // Add All Players To Info.yml
        if (useAddDefault)
            for (Player players : getServer().getOnlinePlayers())
                if (info.get("users." + players.getName()) == null)
                    getWriter().addBase(players.getName(), mIDefaultGroup);

        if (mChatEB) {
            for (Player players : getServer().getOnlinePlayers()) {
                isAFK.put(players.getName(), false);
                chatt.put(players.getName(), false);
                lastMove.put(players.getName(), new Date().getTime());

                if (spoutB) {
                    SpoutPlayer sPlayers = (SpoutPlayer) players;
                    sPlayers.setTitle(getParser().parsePlayerName(players.getName(), players.getWorld().getName()));
                }
            }
        }

        // Check for Automatic Factions Support
        setupFactions();

        // 2nd Startup Timer
        sTime2 = new Date().getTime();

        // Calculate Startup Timer
        sDiff = (sTime2 - sTime1);

        Messanger.log("[" + pdfFile.getName() + "] " + pdfFile.getName() + " v" + pdfFile.getVersion() + " is enabled! [" + sDiff + "ms]");
    }

    public void onDisable() {
        // Shutdown Timer
        String shutdown;
        sTime1 = new Date().getTime();

        getServer().getScheduler().cancelTasks(this);

        // 2nd Shutdown Timer
        sTime2 = new Date().getTime();
        sDiff = (sTime2 - sTime1);
        shutdown = "[Sched: " + sDiff + "ms, ";

        if (eBroadcast && bMessage != null)
            bMessage.disconnect();

        // 2st Shutdown Timer
        sTime1 = new Date().getTime();
        sDiff = (sTime1 - sTime2);
        shutdown += "Ext Msg: " + sDiff + "ms]";

        Messanger.log("[" + pdfFile.getName() + "] " + pdfFile.getName() + " v" + pdfFile.getVersion() + " is disabled!" + shutdown);
    }

    void registerEvents() {
        if (!mAPIOnly) {
            pm.registerEvents(new PlayerListener(this), this);

            pm.registerEvents(new BlockListener(this), this);

            pm.registerEvents(new EntityListener(this), this);

            pm.registerEvents(new ChannelEventListener(this), this);

            pm.registerEvents(new CommandListener(this), this);

            if (spoutB)
                pm.registerEvents(new CustomListener(this), this);
        }
    }

    void setupPerms() {
        Plugin permTest;

        permTest = pm.getPlugin("PermissionsBukkit");
        if (permTest != null) {
            PermissionBuB = true;
            Messanger.log("[" + pdfFile.getName() + "] <Permissions> " + permTest.getDescription().getName() + " v" + permTest.getDescription().getVersion() + " hooked!.");
            return;
        }

        permTest = pm.getPlugin("bPermissions");
        if (permTest != null) {
            bPermB = true;
            Messanger.log("[" + pdfFile.getName() + "] <Permissions> " + permTest.getDescription().getName() + " v" + permTest.getDescription().getVersion() + " hooked!.");
            return;
        }

        permTest = pm.getPlugin("PermissionsEx");
        if (permTest != null) {
            pexPermissions = PermissionsEx.getPermissionManager();
            PEXB = true;
            Messanger.log("[" + pdfFile.getName() + "] <Permissions> " + permTest.getDescription().getName() + " v" + permTest.getDescription().getVersion() + " hooked!.");
            return;
        }

        permTest = pm.getPlugin("GroupManager");
        if (permTest != null) {
            gmPermissionsB = true;
            gmPermissionsWH = ((GroupManager) permTest).getWorldsHolder();
            Messanger.log("[" + pdfFile.getName() + "] <Permissions> " + permTest.getDescription().getName() + " v" + permTest.getDescription().getVersion() + " hooked!.");
            return;
        }

        Messanger.log("[" + pdfFile.getName() + "] <Permissions> SuperPerms hooked!.");
    }

    Boolean setupPlugin(String pluginName) {
        Plugin plugin = pm.getPlugin(pluginName);

        if (plugin != null) {
            Messanger.log("[" + pdfFile.getName() + "] <Plugin> " + plugin.getDescription().getName() + " v" + plugin.getDescription().getVersion() + " hooked!.");
            return true;
        }

        return false;
    }

    void setupPlugins() {
        // Setup MobDisguise
        mobD = setupPlugin("MobDisguise");

        // Setup Factions
        factionsB = setupPlugin("Factions");

        // Setup Heroes
        heroesB = setupPlugin("Heroes");

        if (heroesB)
            heroes = (Heroes) pm.getPlugin("Heroes");

        spoutB = setupPlugin("Spout");

        vaultB = setupPlugin("Vault");

        if (vaultB) {
            RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(Permission.class);

            if (permissionProvider != null)
                vPerm = permissionProvider.getProvider();

            vaultB = vPerm != null;
        }

        if (!spoutEnabled)
            spoutB = false;
    }

    void setupFactions() {
        if (factionsB)
            if (!(Conf.chatTagInsertIndex == 0))
                getServer().dispatchCommand(getServer().getConsoleSender(), "f config chatTagInsertIndex 0");
    }

    void killEss() {
        Plugin plugin = pm.getPlugin("EssentialsChat");

        if (plugin != null)
            pm.disablePlugin(plugin);
    }

    void initializeConfigs() {
        censorF = new File(getDataFolder(), "censor.yml");
        channelsF = new File(getDataFolder(), "channels.yml");
        configF = new File(getDataFolder(), "config.yml");
        infoF = new File(getDataFolder(), "info.yml");
        localeF = new File(getDataFolder(), "locale.yml");

        censor = YamlConfiguration.loadConfiguration(censorF);
        channels = YamlConfiguration.loadConfiguration(channelsF);
        config = YamlConfiguration.loadConfiguration(configF);
        info = YamlConfiguration.loadConfiguration(infoF);
        locale = YamlConfiguration.loadConfiguration(localeF);

        censor.options().indent(4);
        channels.options().indent(4);
        config.options().indent(4);
        info.options().indent(4);
        locale.options().indent(4);
    }

    public void setupConfigs() {
        getMainConfig().load();

        getInfoConfig().load();

        getCensorConfig().load();

        getLocale().load();

        getChannelConfig().load();
    }

    public void reloadConfigs() {
        getMainConfig().reload();

        getInfoConfig().reload();

        getCensorConfig().reload();

        getLocale().reload();

        getChannelConfig().reload();
    }

    void setupTasks() {
        getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
            public void run() {
                if (!mChatEB)
                    return;

                if (AFKTimer < 1)
                    return;

                getMainConfig().reload();

                for (Player player : getServer().getOnlinePlayers()) {
                    if (isAFK.get(player.getName()) == null)
                        isAFK.put(player.getName(), false);

                    if (isAFK.get(player.getName()) ||
                            lastMove.get(player.getName()) == null ||
                            getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "mchat.bypass.afk"))
                        continue;

                    if (new Date().getTime() - (AFKTimer * 1000) > lastMove.get(player.getName())) {
                        getServer().dispatchCommand(getServer().getConsoleSender(), "mchatafkother " + player.getName() + " AutoAfk");
                    } else
                        isAFK.put(player.getName(), false);
                }
            }
        }, 20L * 5, 20L * 5);

        getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
            public void run() {
                if (!mChatEB)
                    return;

                if (AFKKickTimer < 1)
                    return;

                getMainConfig().reload();

                for (Player player : getServer().getOnlinePlayers()) {
                    if (getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "mchat.bypass.afkkick"))
                        continue;

                    if (!isAFK.get(player.getName()))
                        continue;

                    if (new Date().getTime() - (AFKKickTimer * 1000) > lastMove.get(player.getName()))
                        player.kickPlayer("mAFK Kick");
                }
            }
        }, 20L * 10, 20L * 10);
    }

    void setupCommands() {
        regCommands("mchat", new MChatCommand(this));
        regCommands("mchatafk", new AFKCommand(this));
        regCommands("mchatafkother", new AFKOtherCommand(this));

        regCommands("mchatlist", new ListCommand(this));

        regCommands("mchatme", new MeCommand(this));
        regCommands("mchatsay", new SayCommand(this));
        regCommands("mchatwho", new WhoCommand(this));
        regCommands("mchatshout", new ShoutCommand(this));
        regCommands("mchatmute", new MuteCommand(this));
        regCommands("mchatmessageprefix", new MessagePrefixCommand(this));

        regCommands("pmchat", new PMCommand(this));
        regCommands("pmchataccept", new AcceptCommand(this));
        regCommands("pmchatdeny", new DenyCommand(this));
        regCommands("pmchatinvite", new InviteCommand(this));
        regCommands("pmchatleave", new LeaveCommand(this));
        regCommands("pmchatreply", new ReplyCommand(this));

        regCommands("mchannel", new MChannelCommand(this));
    }
    
    void regCommands(String command, CommandExecutor executor) {
        if (getCommand(command) != null)
            getCommand(command).setExecutor(executor);
    }

    void initializeClasses() {
        api = new API(this);
        parser = new Parser(this);
        reader = new Reader(this);
        writer = new Writer(this);
    }


    // Main Config (config.yml)
    public MainConfig getMainConfig() {
        return new MainConfig(this);
    }

    // Info Config (info.yml)
    public InfoConfig getInfoConfig() {
        return new InfoConfig(this);
    }

    // Censor Config (censor.yml)
    public CensorConfig getCensorConfig() {
        return new CensorConfig(this);
    }

    // Channel Config (channels.yml)
    public ChannelConfig getChannelConfig() {
        return new ChannelConfig(this);
    }

    // Language Config
    public LocaleConfig getLocale() {
        return new LocaleConfig(this);
    }

    // API
    public API getAPI() {
        return api;
    }

    // Parser
    public Parser getParser() {
        return parser;
    }

    // Reader
    public Reader getReader() {
        return reader;
    }

    // Writer
    public Writer getWriter() {
        return writer;
    }

    // ChannelManager
    public ChannelManager getChannelManager() {
        return channelManager;
    }
}
