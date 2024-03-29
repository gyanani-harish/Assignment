package harish.mvvmexample.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TrendingRepo(
    @SerializedName("username")
    @Expose
    var username: String,
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("type")
    @Expose
    var type: String,
    @SerializedName("url")
    @Expose
    var url: String,
    @SerializedName("avatar")
    @Expose
    var avatar: String,
    @SerializedName("repo")
    @Expose
    var repo: Repo
)