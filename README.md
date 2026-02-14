# OIB-GUESS-A-NUMBER
Guess Number Competition (using Java)

The Guess Number Competition is a console-based Java application that simulates a competitive number guessing game with two distinct modes: Judge Mode and Participant Mode. The system is designed to allow a judge to configure game settings and monitor results, while participants compete by guessing a randomly generated number within a limited number of attempts.

In Judge Mode, access is protected by a predefined password to ensure administrative control. The judge can set the number of attempts allowed for each participant, view all recorded scores, and check individual participant results. The number of attempts is stored in a configuration file so that the setting persists across program executions.

In Participant Mode, users enter their name and attempt to guess a randomly generated number between 1 and 100. The system provides feedback for each guess, indicating whether the guess is too high or too low. If the participant guesses the correct number within the allowed attempts, a score is calculated based on the number of remaining attempts. If all attempts are exhausted, the correct number is revealed and the participant receives a score of zero.

All participant scores are stored in a text file, allowing the judge to review competition results at any time. The project demonstrates the use of core Java concepts including conditional logic, loops, file handling, random number generation, and structured program design. It serves as a practical example of a simple competitive game system implemented using console-based interaction and persistent file storage.
