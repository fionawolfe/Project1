#Install Packages
library(jsonlite)
library(httpuv)
library(httr)

#Interrogate Github
oauth_endpoints("github")

#Access Github App
GithubApp = oauth_app(appname = "Software Engineering Assignment",
                      key = "908906573ab482b799db",
                      secret = "63be0fe2a7af04af714d3634f86cbfbc8121e036")

# Get OAuth credentials
github_token1 = oauth2.0_token(oauth_endpoints("github"), GithubApp)

# Use API
myToken = config(token = github_token1)



#Access User 'Defunkt' Following to get list of users
DefunktFollowingData <-
  GET("https://api.github.com/users/defunkt/following", myToken)
DefunktFollowingDataContent = content(DefunktFollowingData)

#Save users' data in a dataframe
DefunktFollowingDataFrame = jsonlite::fromJSON(jsonlite::toJSON(DefunktFollowingDataContent))

#Retrieve usernames and save in a vector
Id = DefunktFollowingDataFrame$login
UserIds = c(Id)

#Create empty vectors and data frame
AllUsers = c()
AllUsersDF = data.frame(
  Username = integer(),
  Following = integer(),
  Followers = integer(),
  Repositories = integer()
)



#Loop through list of usernames to find users to add to the list
for (i in 1:length(UserIds))
{
  #Retrieve an individual users following list
  followingurl = paste("https://api.github.com/users/",
                       UserIds[i],
                       "/following",
                       sep = "")
  following = GET(followingurl, myToken)
  followingcontent = content(following)
  
  #Add followings to a dataframe and retrieve usernames
  followingDF = jsonlite::fromJSON(jsonlite::toJSON(followingcontent))
  followinglogin = followingDF$login
  
  #Loop through 'following' users
  for (j in 1:length(followinglogin))
  {
    #Check that the user is not already in the list of users
    if (is.element(followinglogin[j], totalusers) == FALSE)
    {
      #Add user to list of users
      totalusers[length(totalusers) + 1] = followinglogin[j]
      
      #Retrieve data on each user
      followingurl2 = paste("https://api.github.com/users/", followinglogin[j], sep = "")
      following2 = GET(followersurl, myToken)
      followerscontent2 = content(followers)
      followingDF2 = jsonlite::fromJSON(jsonlite::toJSON(followerscontent))
      
      #Retrieve each users following
      followingnumber = followingDF2$following
      
      #Retrieve each users followers
      followersnumber = followingDF2$followers
      
      #Retrieve each users number of repositories
      reposnumber = followingDF2$public_repos
      
      #Add users data to a new row in dataframe
      totalusersDF[nrow(totalusersDF) + 1, ] = c(followinglogin[j],
                                                 followingnumber,
                                                 followersnumber,
                                                 reposnumber)
      
    }
    next
  }
  next
}




