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
DefunktFollowingData = GET("https://api.github.com/users/defunkt/following", myToken)
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
  Repositories = integer(),
  DateCreated = integer()
)



#Loop through list of usernames to find users to add to the list
for (i in 1:length(UserIds))
{
  #Retrieve an individual users following list
  FollowingUrl = paste("https://api.github.com/users/", UserIds[i], "/following", sep = "")
  Following = GET(FollowingUrl, myToken)
  FollowingContent = content(Following)
  
  #Skip the user if they do not follow anybody
  if (length(FollowingContent) == 0)
  {
    next
  }
  
  #Add followings to a dataframe and retrieve usernames
  FollowingDF = jsonlite::fromJSON(jsonlite::toJSON(FollowingContent))
  FollowingLogin = FollowingDF$login
  
  #Loop through 'following' users
  for (j in 1:length(FollowingLogin))
  {
    #Check that the user is not already in the list of users
    if (is.element(FollowingLogin[j], AllUsers) == FALSE)
    {
      #Add user to list of users
      AllUsers[length(AllUsers) + 1] = FollowingLogin[j]
      
      #Retrieve data on each user
      FollowingUrl2 = paste("https://api.github.com/users/", FollowingLogin[j], sep = "")
      Following2 = GET(FollowingUrl2, myToken)
      FollowingContent2 = content(Following2)
      FollowingDF2 = jsonlite::fromJSON(jsonlite::toJSON(FollowingContent2))
      
      #Retrieve each users following
      FollowingNumber = FollowingDF2$following
      
      #Retrieve each users followers
      FollowersNumber = FollowingDF2$followers
      
      #Retrieve each users number of repositories
      ReposNumber = FollowingDF2$public_repos
      
      #Retrieve year which each user joined Github
      YearCreated = substr(FollowingDF2$created_at, start = 1, stop = 4)
      
      #Add users data to a new row in dataframe
      AllUsersDF[nrow(AllUsersDF) + 1, ] = c(FollowingLogin[j], FollowingNumber, FollowersNumber, ReposNumber, YearCreated)
      
    }
    #Stop when there are more than 400 users
    if(length(AllUsers) > 400)
    {
      break
    }
    next
  }
  next
}




