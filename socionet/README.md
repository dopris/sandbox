# SocioNet
A console-based social networking application

## How to build and run
Prerequisites:
* Java 8
* Maven

Build command:
mvn clean install
Note. The application jar with its dependencies will be created in ./target folder: socionet.jar

Run command:
java -jar target/socionet.jar

## Example of usage and commands:

$java -jar target/socionet.jar
Simple SocioNet Application
----------------------------
The following commands are supported:
Posting: <user name> -> <message>               Post <message> to a personal timeline of <user name>
Reading: <user name>                            Read <user name> timeline messages
Following: <user name> follows <another user>   Subscribe <user name> to <another user> timeline
Wall: <user name> wall                          View aggregate list of all subscriptions to <user name>
Quit: :q                                        Quit the application.
----------------------------
>Alice -> I love the weather today
>Bob -> Damn! We lost!
>Bob -> Good game though.
>Alice
I love the weather today (25 seconds ago)
>Bob
Good game though. (11 seconds ago)
Damn! We lost! (21 seconds ago)
>Charlie -> I'm in New York today! Anyone want to have a coffee?
>Charlie follows Alice
>Charlie wall
Charlie - I'm in New York today! Anyone want to have a coffee? (19 seconds ago)
Alice - I love the weather today (1 minute ago)
>Charlie follows Bob
>Charlie wall
Charlie - I'm in New York today! Anyone want to have a coffee? (49 seconds ago)
Bob - Good game though. (1 minute ago)
Bob - Damn! We lost! (1 minute ago)
Alice - I love the weather today (1 minute ago)
>:q
