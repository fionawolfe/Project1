
library(jsonlite)

library(httpuv)

library(httr)


oauth_endpoints("github")


GithubApp = oauth_app(appname = "Software Engineering Assignment",
                      key = "908906573ab482b799db",
                      secret = "63be0fe2a7af04af714d3634f86cbfbc8121e036")

github_token1 = oauth2.0_token(oauth_endpoints("github"), GithubApp)
myToken = config(token = github_token1)

data1 = GET("https://api.github.com/users/fionawolfe/followers", myToken)
content1 = content(data1)
followersDF1 = jsonlite::fromJSON(jsonlite::toJSON(content1))
id = followersDF1$login
UserIds = c(id)
AllUsers = c()
AllUsersDF = data.frame(Username = integer(), Followers = integer(), Following = integer(), NumberOfRepositories = integer())


for (i in 1:length(UserIds))
{
  url2 = paste("https://api.github.com/users/", UserIds[i], "/followers", sep = "")
  data2 = GET(url2, myToken)
  content2 = content(data2)
  followersDF2 = jsonlite::fromJSON(jsonlite::toJSON(content2))
  usernames = followersDF2$login
  
  followers = followersDF2$followers
  following = followersDF2$following
  for (j in 1:length(usernames))
  {
    if(is.element(usernames[j], AllUsers) == FALSE)
    {
      url3 = paste("https://api.github.com/users/", usernames[j], sep = "")
      data3 = GET(url3, myToken)
      content3 = content(data3)
      followersDF3 = jsonlite::fromJSON(jsonlite::toJSON(content3))
      followers = followersDF3$followers
      following = followersDF3$following
      reposnumber = followersDF3$public_repos
      AllUsers[length(AllUsers) +1] = usernames[j]
      AllUsersDF[nrow(AllUsersDF)+1,] = c(usernames[j], followers, following, reposnumber)
      
    }
    next
  }
  next
}



