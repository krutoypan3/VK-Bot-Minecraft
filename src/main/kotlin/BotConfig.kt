import com.google.gson.annotations.SerializedName

data class BotConfig(
    @SerializedName("MINECRAFT_SERVER_IP") val MINECRAFT_SERVER_IP: String,
    @SerializedName("MINECRAFT_SERVER_RCON_PORT") val MINECRAFT_SERVER_RCON_PORT: Int,
    @SerializedName("MINECRAFT_SERVER_RCON_PASSWORD") val MINECRAFT_SERVER_RCON_PASSWORD: String,
    @SerializedName("VK_GROUP_ID") val VK_GROUP_ID: Int,
    @SerializedName("VK_ACCESS_TOKEN") val VK_ACCESS_TOKEN: String,
    @SerializedName("VK_PRIMARY_CHAT_PEER_ID") val VK_PRIMARY_CHAT_PEER_ID: Int,
    @SerializedName("VK_IS_LIVE_STATUS") val VK_IS_LIVE_STATUS: Boolean,
    @SerializedName("VK_LIVE_STATUS_UPDATE_TIME_MS") val VK_LIVE_STATUS_UPDATE_TIME_MS: Long,
    @SerializedName("VK_ADMIN_IDS") val VK_ADMIN_IDS: List<Int>,
)
