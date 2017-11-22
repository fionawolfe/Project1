
library(jsonlite)

library(httpuv)

library(httr)


oauth_endpoints("github")


GithubApp <- oauth_app(appname = "Software Engineering Assignment",
                       key = "908906573ab482b799db",
                       secret = "63be0fe2a7af04af714d3634f86cbfbc8121e036")



