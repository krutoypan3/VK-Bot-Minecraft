package vk

import Config
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor
import com.vk.api.sdk.httpclient.HttpTransportClient

class VkServer {
    fun start() {
        try {
            val httpClient: HttpTransportClient = HttpTransportClient.getInstance()
            val vk = VkApiClient(httpClient)

            val groupActor = GroupActor(
                Config.VkConnect.GROUP_ID,
                Config.VkConnect.ACCESS_TOKEN,
            )

            vk.groupsLongPoll().setLongPollSettings(groupActor, Config.VkConnect.GROUP_ID)
                .enabled(true)
                .messageNew(true)
                .execute()

            val handler = VkEventHandler(vk, groupActor, 1)
            handler.run()
        } catch (e: Exception) {
            println("Ошибка подключения к ВК, проверьте правильность введеных данных в Config.VkConnect")
            e.printStackTrace()
        }
    }
}