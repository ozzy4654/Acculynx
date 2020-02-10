<H1>Acculynx</H1>

<H2>Overall Goal</H2>

Create an Android app that displays a list of recent Stack Overflow questions that have an accepted answer and contain more than 1 answer. Allow visitors to view all the answers for a selected question and try to guess which answer was the accepted answer. Save each guess to display previously guessed questions/answers.
We expect this to take about 3-8 hours. Please send us whatever you have completed after 1 week. You do not need to have all requirements completed.

Stackoverflow API
https://api.stackexchange.com/docs

<H2>Requirements</H2>

<p>
1. Display a list of recent questions that have an accepted answer and more than 1 answer a. Bonus: Add additional searching      and/or filtering<br>
2. Display list of recently guessed questions<br>
3. Select a question from either of the lists above to view all the answers for that question<br>
4. Allow visitors to guess which answer they think is the “accepted” answer.<br>
5. After guessing, show which answer is the accepted answer.<br>
6. Bonus: Formulate and display some kind of score for each guess.<br>
    a Example: Guessing the accepted answer or an answer with a lot of votes should get a better score than a guess on an           answer with less votes or negative votes.<br><br>

Upload your solution to a public repository on github.com and send us the link. Bonus​: Use git from the beginning to have a full commit history
</p>

<H2>Things to note</H2>
<p>
● The results of the API above are JSON and will contain different data almost every time it’s requested<br>
● Be warned: the StackExchange API is rate limited and will shut you off after a certain amount of
  requests in a time period. (Hint: look at "quota_remaining” in the request) You shouldn’t run into the
  quota limit, but just be aware of it.<br>
● Implement this as an Android app in Java/Kotlin and use any library or frameworks to help you along,
  but you must display the desired requirements above.<br>
● Include instructions on how to build and run locally.<br>
● Visual design will not be judged much, but we do expect clean, well organized, and a relatively intuitive
  user experience.<br>
● You do not need to have any authentication or user tracking.<br>
</p>

