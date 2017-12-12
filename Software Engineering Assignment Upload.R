
library(jsonlite)

library(httpuv)

library(httr)


oauth_endpoints("github")


GithubApp <- oauth_app(appname = "Software Engineering Assignment",
                       key = "908906573ab482b799db",
                       secret = "63be0fe2a7af04af714d3634f86cbfbc8121e036")

github_token1 <- oauth2.0_token(oauth_endpoints("github"), GithubApp)
myToken <- config(token = github_token1)

data1 <- fromJSON("https://api.github.com/users/fionawolfe/followers")
id = data1$login
UserIds = c(id)