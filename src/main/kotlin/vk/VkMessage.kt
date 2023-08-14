package vk

import com.google.gson.annotations.SerializedName
import com.vk.api.sdk.objects.messages.Message

class VkMessage {
    @SerializedName("message")
    val message: Message? = null
}