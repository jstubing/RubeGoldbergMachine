# RubeGoldbergMachine

Skeleton app for a rube goldberg machine. The idea is to implement a fun idea such as playing an animation and/or sound for a brief period of time and then pass control to the next device in the chain via Firebase realtime database.

To participate, clone the repo and open up MainActivity. Update the TURN constant at the top of the file to your turn number. e.g. If you want to go first, this would be 1. Each participant needs a successive turn number.

Then, just implement your idea in the doTheWork() function. Try to keep the run time short (< 1 min) to keep the machine moving. After your turn is complete, the startNextTurn() function will trigger the next device to start its turn.
