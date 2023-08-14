package minecraft

import net.kronos.rkon.core.Rcon
import java.nio.charset.StandardCharsets

object MinecraftRcon {
    fun <T> withRcon(minecraft: (rcon: Rcon) -> T): T? {
        var rcon: Rcon? = null
        return try {
            rcon = Rcon(
                Config.MinecraftConnect.SERVER_IP,
                Config.MinecraftConnect.SERVER_RCON_PORT,
                Config.MinecraftConnect.SERVER_RCON_PASSWORD.toByteArray(StandardCharsets.UTF_8)
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