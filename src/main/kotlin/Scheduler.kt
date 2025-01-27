import com.google.gson.Gson
import minecraft.MinecraftRcon
import vk.VkBot
import java.io.File
import kotlin.random.Random

class Scheduler: Thread() {

    override fun run() {
        super.run()

        while (true) {
            if (Values.config?.VK_IS_LIVE_STATUS!!) {
                MinecraftRcon.withRcon { rcon ->
                    val result = rcon.command("list")
                    // Result example
                    // There are 3 of a max of 20 players online: filleyka, DiamondBelongHer, DREAMKUSTIK
                    //
                    // Result empty example
                    // There are 0 of a max of 20 players online:
                    val playersString = result.split(": ").last()
                    if (playersString.isNotBlank() || Values.PLAYERS_ON_THE_SERVER.isNotEmpty()) {
                        val players = playersString.split(", ").filter { it.isNotBlank() }.toMutableSet()
                        if (players != Values.PLAYERS_ON_THE_SERVER) {
                            val leaveFromServer = mutableSetOf<String>()
                            leaveFromServer.addAll(Values.PLAYERS_ON_THE_SERVER)
                            leaveFromServer.removeAll(players)

                            val enterToServer = mutableSetOf<String>()
                            enterToServer.addAll(players)
                            enterToServer.removeAll(Values.PLAYERS_ON_THE_SERVER)

                            var message = ""
                            if (leaveFromServer.isNotEmpty()) message += "\nВышли с сервера: $leaveFromServer"
                            if (enterToServer.isNotEmpty()) message += "\nВошли на сервер: $enterToServer"

                            Values.PLAYERS_ON_THE_SERVER = players.toMutableSet()

                            if (Values.PLAYERS_ON_THE_SERVER.isNotEmpty()) {
                                val playersCount = Values.PLAYERS_ON_THE_SERVER.count()
                                message += "\nТекущий онлайн $playersCount: ${Values.PLAYERS_ON_THE_SERVER}"
                            } else {
                                message += "\nСервер пустует... 🙁"
                            }
                            message = message
                                .removePrefix("\n")
                                .replace("[", "")
                                .replace("]", "")

                            VkBot.withVkBot { client, actor ->
                                client.messages().send(actor)
                                    .message(message)
                                    .peerId(Values.config?.VK_PRIMARY_CHAT_PEER_ID!!)
                                    .randomId(Random.nextInt(10000))
                                    .execute()
                            }
                            println(message)
                        }
                    }
                    Values.PLAYERS_ON_THE_SERVER.forEach { playerName ->
                        val statFile = File("vk_bot_stats.txt")
                        if (!statFile.exists()) statFile.createNewFile()
                        var statContent = statFile.readText()
                        if (statContent.isBlank()) statContent = "\"data\":[{\"playerName\":\"aaaaa\", \"timeOnServer\":1}]"
                        val statistics = Gson().fromJson(statContent, PlayersStatistics::class.java) ?: PlayersStatistics(listOf())
                            .data.first { it.playerName == playerName }
                                .apply { timeOnServer += Values.config?.VK_LIVE_STATUS_UPDATE_TIME_MS!! }
                        statFile.writeText(Gson().toJson(statistics))
                    }
                }
                sleep(Values.config?.VK_LIVE_STATUS_UPDATE_TIME_MS!!)
            }
        }
    }
}