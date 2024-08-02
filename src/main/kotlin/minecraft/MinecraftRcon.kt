package minecraft

import BotConfig
import net.kronos.rkon.core.Rcon
import java.nio.charset.StandardCharsets

object MinecraftRcon {
    fun <T> withRcon(minecraft: (rcon: Rcon) -> T): T? {
        var rcon: Rcon? = null
        return try {
            rcon = Rcon(
                Values.config?.MINECRAFT_SERVER_IP!!,
                Values.config?.MINECRAFT_SERVER_RCON_PORT!!,
                Values.config?.MINECRAFT_SERVER_RCON_PASSWORD?.toByteArray(StandardCharsets.UTF_8)!!
            )
            minecraft(rcon)
        } catch (e: Exception) {
            println("Ошибка подключения к Minecraft RCON, проверьте правильность введеных данных в Config.MinecraftConnect")
            e.printStackTrace()
            null
        } finally {
            rcon?.disconnect()
        }
    }
}