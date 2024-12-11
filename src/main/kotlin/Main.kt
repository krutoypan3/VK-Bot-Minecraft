import com.google.gson.Gson
import vk.VkServer
import java.io.File

fun main() {
    val file = File("config.txt")
    val content = file.readText()
    println("Load config from file: ${file.absolutePath}")
    val botConfig = Gson().fromJson(content, BotConfig::class.java)

    Values.config = botConfig

    println("Config loaded")

    Scheduler().start()
    VkServer().start()
}