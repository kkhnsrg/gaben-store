package com.kokhan.gabenstore;

import java.util.ArrayList;
import java.util.List;

public final class DataStorage {
    private final List<Game> cartGameList;
    private final List<Game> catalogGameList;


    private static final DataStorage ourInstance = new DataStorage();

    public static DataStorage getInstance() {
        return ourInstance;
    }

    private DataStorage() {
        cartGameList = new ArrayList<>();
        catalogGameList = new ArrayList<>();
        catalogGameList.add(new Game("Assasins Creed 3", "Assassin's Creed III is a third-person action-adventure title set in an open world in which the player uses a combination of stealth, parkour, and combat to complete various missions. The player takes the role of three characters during the course of the game.", R.drawable.assasinscreed3, 8, 10));
        catalogGameList.add(new Game("Dark Souls 3", "Dark Souls III is an action role-playing video game developed by FromSoftware and published by Bandai Namco Entertainment for PlayStation 4, Xbox One, and Microsoft Windows. An entry in the Souls series, Dark Souls III was released in Japan in March 2016 and worldwide in April 2016.", R.drawable.darksouls3, 17, 32));
        catalogGameList.add(new Game("Flatout 2", "FlatOut 2 is a racing video game developed by Bugbear Entertainment and published by Empire Interactive. It is the sequel to the 2004 game FlatOut. This game is themed more on the street racing/import tuner scene than its predecessor.", R.drawable.faltout2, 3, 7));
        catalogGameList.add(new Game("Grand Than Auto 5", "Grand Theft Auto V (also known as Grand Theft Auto Five, GTA 5 or GTA V) is a video game developed by Rockstar North. It is the fifteenth instalment in the Grand Theft Auto series and the successor of Grand Theft Auto IV. The original edition was released on September 17th, 2013 for the Xbox 360 and PlayStation 3.", R.drawable.gta5, 36, 29));
        catalogGameList.add(new Game("Mortal Kombat 11", "Mortal Kombat 11 is an upcoming fighting video game developed by NetherRealm Studios and published by Warner Bros. Interactive Entertainment. Running on the Unreal Engine 3, it is the eleventh main installment in the Mortal Kombat series and a sequel to 2015's Mortal Kombat X. Announced at The Game Awards 2018, the game is set to be released in North America and Europe on April 23, 2019 for Microsoft Windows, Nintendo Switch, PlayStation 4, and Xbox One.", R.drawable.mk11, 3, 40));
        catalogGameList.add(new Game("Amazing Spider Man", "The Amazing Spider Man is a fun and cool platform game that takes inspiration from the legendary Spiderman comic series and films. This game truly allows your to experience what it is like to be Spiderman - you can run on rooftops, shoot your web, swing from buildings and eliminate the bad guys!", R.drawable.spiderman, 5, 27));
        catalogGameList.add(new Game("Assasins Creed Origins", "Assassin's Creed Origins is an action-adventure video game developed by Ubisoft Montreal and published by Ubisoft. ... The game is set in Ancient Egypt near the end of the Ptolemaic period and recounts the secret fictional history of real-world events.", R.drawable.acorigins, 19, 29));
        catalogGameList.add(new Game("Batman Arkham Origins", "Batman: Arkham Origins is a 2013 action-adventure video game developed by WB Games Montréal and published by Warner Bros. Interactive Entertainment for Microsoft Windows and the PlayStation 3, Wii U and Xbox 360 video game consoles.", R.drawable.batman, 5, 13));
        catalogGameList.add(new Game("Bioshock Infinite", "BioShock Infinite. BioShock Infinite is a first-person shooter video game developed by Irrational Games and published by 2K Games. It was released worldwide for the Microsoft Windows, PlayStation 3, Xbox 360, and OS X platforms in 2013, and a Linux port was released in 2015.", R.drawable.bioshockinfinite, 13, 16));
        catalogGameList.add(new Game("Borderlands 2", "Borderlands 2 is an action role-playing game played from a first-person perspective. The gameplay revolves around the completion of missions and the collection of randomly generated \"loot\" (such as weapons, shields, skins, and other items) with various rarities, statistics, and elemental effects.", R.drawable.borderlands2, 5, 21));
        catalogGameList.add(new Game("Dishonored 2", "Dishonored 2 is an action-adventure game with stealth elements played from a first-person perspective. ... Players can choose whether to play stealthily or not, and can finish the game without taking a life.", R.drawable.dishonored2, 5, 24));
        catalogGameList.add(new Game("Doom (2016)", "Doom is a first-person shooter video game developed by id Software and published by Bethesda Softworks. A reboot of the Doom franchise, it is the fourth title in the main series and the first major installment since Doom 3 in 2004. It was released worldwide on Microsoft Windows, PlayStation 4, and Xbox One in May 2016 and is powered by id Tech 6. A port for Nintendo Switch, co-developed with Panic Button, was released in November 2017.", R.drawable.doom, 3, 17));
        catalogGameList.add(new Game("Far Cry 5", "Far Cry 5 is an action-adventure first-person shooter video game developed by Ubisoft Montreal and Ubisoft Toronto and published by Ubisoft for Microsoft Windows, PlayStation 4 and Xbox One. It is the standalone successor to the 2014 video game Far Cry 4, and the fifth main installment in the Far Cry series.", R.drawable.farcry5, 5, 5));
        catalogGameList.add(new Game("Metal Gear Rising", "Metal Gear Rising: Revengeance is an action hack and slash video game developed by PlatinumGames and published by Konami Digital Entertainment. The game was originally being developed internally by Kojima Productions, who announced the game in 2009 under the title of Metal Gear Solid: Rising.", R.drawable.metalgearrising, 5, 5));
        catalogGameList.add(new Game("Resident Evil 2", "Resident Evil 2 is a remake of the 1998 game Resident Evil 2 released for the PlayStation. Unlike the original, which uses tank controls and fixed camera angles, the remake features \"over-the-shoulder\" third-person shooter gameplay similar to Resident Evil 4.", R.drawable.residentevel2, 16, 27));
        catalogGameList.add(new Game("Sword Art Online", "SAO is a video game developed by Aquria and published by Bandai Namco Entertainment for the PlayStation 4, PlayStation Vita, and Windows PC, based on the Japanese light novel series, Sword Art Online. It is the fourth video game in the series and the successor to Sword Art Online: Lost Song.", R.drawable.sao, 3, 15));
        catalogGameList.add(new Game("Naruto Shippuden: Ultimate Ninja Storm 4", "Following its very successful anime, manga, and video game adaptations, the series that has engaged fans all over the world for almost a decade continues its legacy with NARUTO SHIPPUDEN™: Ultimate Ninja® STORM 4! With more than 12 million NARUTO SHIPPUDEN: Ultimate Ninja STORM games sold worldwide, the STORM series has established its place at the pinnacle of videogames adapted from anime and manga.", R.drawable.narutostorm4, 2, 15));
        catalogGameList.add(new Game("Nier: Automata", "Nier: Automata is an action role-playing game developed by PlatinumGames and published by Square Enix. Gameplay combines role-playing elements with action-based combat and mixed genre gameplay similar to that of Nier.", R.drawable.nier, 12, 18));
        catalogGameList.add(new Game("The Elder Scrolls V: Skyrim", "The Elder Scrolls V: Skyrim is an action role-playing game, playable from either a first or third-person perspective. The player may freely roam over the land of Skyrim which is an open world environment consisting of wilderness expanses, dungeons, cities, towns, fortresses, and villages.", R.drawable.skyrim, 7, 20));
        catalogGameList.add(new Game("The Witcher 3: Wild Hunt", "The Witcher 3: Wild Hunt is a story-driven, next-generation open world role-playing game, set in a visually stunning fantasy universe, full of meaningful choices and impactful consequences. You play as Geralt of Rivia, a monster hunter tasked with finding a child from an ancient prophecy.", R.drawable.witcher3, 25, 26));
        catalogGameList.add(new Game("Wolfenstein", "Wolfenstein is a series of World War II-themed video games created by Muse Software. In 2001, the series was rebooted with Return to Castle Wolfenstein, developed by Gray Matter Interactive. It was followed in 2003 by Wolfenstein: Enemy Territory, by Splash Damage. Raven Software's Wolfenstein followed in 2009.", R.drawable.wolfenstein, 11, 17));

    }

    public List<Game> getCartGameList() {
        return cartGameList;
    }

    public List<Game> getCatalogGameList() {
        return catalogGameList;
    }

    public void addExistingGameToCatalog(Game game) {
        for (Game currentGame : catalogGameList) {
            if (currentGame.getTitle().equals(game.getTitle())) {
                currentGame.setCount(currentGame.getCount() + game.getCount());
                break;
            }
        }
    }
}
