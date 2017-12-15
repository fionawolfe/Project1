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
    next
  }
  #Stop when there are more than 400 users
  if(length(AllUsers) > 400)
  {
    break
  }
  next
}

#Install necessary plotting packages
require(devtools)
library(plotly)


#Produce a scatter plot of Followers vs Following
MyPlot = plot_ly(data = AllUsersDF, x = ~Following, y = ~Followers, text = ~paste("Following: ", Following, 
                                                                                    "<br>Followers: ", Followers))
MyPlot

#Upload the plot to Plotly
Sys.setenv("plotly_username" = "fwolfe")
Sys.setenv("plotly_api_key" = "GvQOPfqabuX8DRNmewQ8")
api_create(MyPlot, filename = "Following vs Followers")



#Produce a scatter plot of Followers vs Number of Repositories for each user,
#colour coded by the data which they joined Github
MyPlot1 = plot_ly(data = AllUsersDF, x = ~Repositories, y = ~Followers, 
                  text = ~paste("Followers: ", Followers, "<br>Repositories: ", 
                                Repositories, "<br>Date Created:", DateCreated), color = ~DateCreated)
MyPlot1

#Upload the plot to Plotly
Sys.setenv("plotly_username" = "fwolfe")
Sys.setenv("plotly_api_key" = "GvQOPfqabuX8DRNmewQ8")
api_create(MyPlot1, filename = "Followers vs Repositories by Date")

#Sums of columns for the AllUsersDF dataframe
colSums(Filter(is.numeric, AllUsersDF))



#LANGUAGES
#The following code finds the most popular language for each user

#Create empty vector
Languages = c()

#Loop through all the users
for (i in 1:length(AllUsers))
{
  #Access each users repositories and save in a dataframe
  RepositoriesUrl = paste("https://api.github.com/users/", AllUsers[i], "/repos", sep = "")
  Repositories = GET(RepositoriesUrl, myToken)
  RepositoriesContent = content(Repositories)
  RepositoriesDF = jsonlite::fromJSON(jsonlite::toJSON(RepositoriesContent))
  
  #Find names of all the repositories for the given user
  RepositoriesNames = RepositoriesDF$name

  #Loop through all the repositories of an individual user
  for (j in 1: length(RepositoriesNames))
  {
    #Find all repositories and save in data frame
    RepositoriesUrl2 = paste("https://api.github.com/repos/", AllUsers[i], "/", RepositoriesNames[j], sep = "")
    Repositories2 = GET(RepositoriesUrl2, myToken)
    RepositoriesContent2 = content(Repositories2)
    RepositoriesDF2 = jsonlite::fromJSON(jsonlite::toJSON(RepositoriesContent2))
    
    #Find the language which each repository was written in
    Language = RepositoriesDF2$language
    
    #Skip a repository if it has no language
    if (length(Language) != 0 && Language != "<NA>")
    {
      #Add the languages to a list
      Languages[length(Languages)+1] = Language
    }
    next
  }
  next
}

#Save the top 20 languages in a table
LanguageTable = sort(table(Languages), increasing=TRUE)
LanguageTableTop20 = LanguageTable[(length(LanguageTable)-19):length(LanguageTable)]

#Save this table as a data frame
LanguageDF = as.data.frame(LanguageTableTop20)

#Plot the data frame of languages
MyPlot2 = plot_ly(data = LanguageDF, x = LanguageDF$Languages, y = LanguageDF$Freq, type = "bar")
MyPlot2

#Upload the plot to Plotly
Sys.setenv("plotly_username" = "fwolfe")
Sys.setenv("plotly_api_key" = "GvQOPfqabuX8DRNmewQ8")
api_create(MyPlot2, filename = "20 Most Popular Languages")


