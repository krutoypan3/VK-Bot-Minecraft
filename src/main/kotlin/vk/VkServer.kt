package vk

import Config

class VkServer {
    fun start() {
        try {
            VkBot.withVkBot { client, actor ->
                client.groupsLongPoll().setLongPollSettings(actor, Config.VkConnect.GROUP_ID)
                    .enabled(true)
                    .messageNew(true)
                    .execute()
                val handler = VkEventHandler(client, actor, 1)
                handler.run()
            }
        } catch (e: Exception) {
            println("Ошибка подключения к ВК, проверьте правильность введеных данных в Config.VkConnect")
            e.printStackTrace()
        }
    }
}