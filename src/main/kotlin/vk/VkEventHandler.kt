package vk

import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor
import com.vk.api.sdk.events.Events
import com.vk.api.sdk.events.longpoll.GroupLongPollApi
import com.vk.api.sdk.objects.callback.messages.CallbackMessage
import com.vk.api.sdk.objects.messages.Message
import minecraft.MinecraftRcon
import kotlin.random.Random

class VkEventHandler(
    private val client: VkApiClient,
    private val actor: GroupActor,
    private val waitTime: Int,
) : GroupLongPollApi(client, actor, waitTime) {

    private fun messagePrepare(message: Message) {
        val text = message.text.lowercase()
        when {
            text == "начать" || text == "start" -> {
                VkBot.sendMessage("Искренне вас приветствую!", message.peerId)
            }

            text.startsWith("/list") -> {
                MinecraftRcon.withRcon { rcon ->
                    val result = rcon.command(text.removePrefix("/"))
                    println(result)
                    VkBot.sendMessage(result)
                }
            }
        }
    }

    override fun parse(message: CallbackMessage): String? {
        if (message.type == Events.MESSAGE_NEW) {
            gson.fromJson(message.getObject(), VkMessage::class.java).message?.let {
                this.messagePrepare(it)
            }
            return "OK"
        }
        return super.parse(message)
    }
}