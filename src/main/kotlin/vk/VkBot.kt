package vk

import Values
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor
import com.vk.api.sdk.httpclient.HttpTransportClient
import kotlin.random.Random

object VkBot {
    fun <T> withVkBot(vkApiClient: (client: VkApiClient, actor: GroupActor) -> T): T? {
        return try {
            val httpClient: HttpTransportClient = HttpTransportClient.getInstance()
            val vkClient = VkApiClient(httpClient)
            val groupActor = GroupActor(
                Values.config?.VK_GROUP_ID,
                Values.config?.VK_ACCESS_TOKEN,
            )
            vkApiClient(vkClient, groupActor)
        } catch (e: Exception) {
            println("Ошибка подключения к Minecraft RCON, проверьте правильность введеных данных в Config.MinecraftConnect")
            e.printStackTrace()
            null
        }
    }

    fun sendMessage(message: String, peerId: Int = Values.config?.VK_PRIMARY_CHAT_PEER_ID!!) {
        withVkBot { client, actor ->
            client.messages().send(actor)
                .message(message)
                .peerId(peerId)
                .randomId(Random.nextInt(10000))
                .execute()
        }
    }
}