# Mini Bloomberg Terminal
## Bloomberg mentorship programme 2020

### Implemented features
* [User](src/user): users profile page
* [News](src/news): news stories page
* [Calendar](src/calendar): a calendar with appointments
* [FX](src/fx): a foreign exchange function
* [Stock](src/stock): an information page about given security

#### USER
Displays user profile and performs actions related to it.
Each user profile has these values: a unique ID, name, email address, phone number and
Mini Bloomberg Terminal joining day.

**Methods:**
* ```addNewUserProfile(name, emailAddress, phone_number)``` - adds a new user to the system. Generates a unique ID and uses current date for joining date
* ```displayUserProfile(userID)``` - prints user information in a nicely formatted way
* ```changeUserName(userID, newName)``` - updates username
* ```changeUserEmailAddress(userID, newEmailAddress)``` - updates user email address
* ```changeUserPhoneNumber(userID, newPhoneNumber)``` - updates user phone number

#### NEWS
Allows to see headlines of top worldwide stories or that have certain tags / filters attached to them.

**Methods:**
* ```addStory(author, title, text, tags) ``` - adds new story to the system with given author, title, text and tags. It uses current time as story publish time and also generates a unique identifier for each added story
* ```markStoryAsRead(storyID)``` - marks that a story with that ID has been read once
* ```displayTopTenNews()``` - prints top 10 stories based on the number of times they were read
* ```displayStoriesForAuthor(author)``` - prints all stories for a given author
* ```displayStoriesWithTags(listOfTags)``` - prints all stories which have one or more of the given tags in the list. Also, the order of stories takes into account how many tags have been matched (the more tags matched, the higher the story should be)

#### CALENDAR
Each user can add all of their meetings, events and training into a calendar and have an easy access view to see everything they have to do on a certain day. Users can see other users' calendars and see what meetings they have or add a meeting which is shared between a few different Bloomberg users.

**Methods:**
* ```addMeeting(listOfUsers, startTime, endTime, topic)``` - add a meeting to all users calendars from listOfUsers with given startTime, endTime, and topic
* ```displayUsersDay(userID)``` - displays all the meetings the user has for a current day in an organized way (from earliest to the latest)
* ```displayUsersCalendarForGivenDay(userID, calendarDay)``` - displays all the meetings the user has for a given day (from earliest to latest)
* ```meetingTimeSuggestion(organisingUser, calendarDay, earliestTime, latestTime, timeInterval)``` - prints out all available time slots for a user to put a meeting in. It has to start at or after earliestTime and finish at or before latestTime. Moreover, it should take the amount of time given in timeInterval
* ```meetingTimeScheduler(listOfuserID, calendaryDay, earliestTime, latestTime, timeInterval)``` - similar method as meetingTimeSuggestion, but instead of checking only one user availability, checks all users in the given list and print out available times only if a time slot is available for all of the users

#### FX
Foreign exchange function which tells users the price of different currencies at different points in time.

**Methods:**
* ```addForeignExchangeValue(fromCurrency, toCurrency, price, timestamp)``` - adds price at timestamp time for fromCurrency to toCurrency
* ```displayTop10RecentPrices()``` - prints 10 most recent currency prices in the system
* ```displayTopMostRecentPricesForCurrenycyPair(fromCurrency, toCurrency, numberOfPrices)``` - prints the given Number of most recent price updates for the given current pair
* ```displayBestPriceToBuyCurrency(fromCurrency, toCurrency)``` - using latest existing prices in the system, returns the best possible price to buy toCurrency using fromCurrency. toCurrency can be bought using fromCurrency, or using another currency that was bought using fromCurrency

#### STOCK
Displays information about different securities, giving details like what industry it belongs to, who are the board members, how much its stock value is

**Methods:**
* ```addsBasicSecurityInfo(securityID, industry, description, listOfBoardMembers)``` - adds security with its basic information to the system
* ```addTick(securityID, price, timestamp)``` - adds price for a specific security which occured on a given time
* ```allTimeHigh(securityID)``` - returns the highest price the given security has ever had
* ```allTimeLow(securityID)``` - returns the lowest price the given security has ever had
* ```getAllPriceHistory(securityID, startTime, endTime)``` - prints out formatted price history of given security between two timestamps, sorted based on timestamps
