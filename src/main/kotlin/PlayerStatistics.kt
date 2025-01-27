import com.google.gson.annotations.SerializedName

data class PlayerStatistics(
    @SerializedName("playerName") var playerName: String,
    @SerializedName("timeOnServer") var timeOnServer: Long,
)