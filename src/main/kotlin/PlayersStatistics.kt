import com.google.gson.annotations.SerializedName

data class PlayersStatistics(
    @SerializedName("data") var data: List<PlayerStatistics>
)