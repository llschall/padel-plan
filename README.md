# Padel Plan

Generates a planning of Padel sessions based on the players subscriptions.

Padel is played with 2 players against 2 other players, so each Padel field can welcome **4 players**.

So a session where the amount of players is not a multiple of 4 will lead to a waiting list of **substitute players**.

This project intends to implement an algorithm producing an **optimized distribution of the substitutes**.

There is no need for a UI here:

* the input is a set of files with the players subscriptions
* the output is a html report with the planning optimized by the algorithm, similar to the one hereafter:

![Overview](https://github.com/llschall/padel-plan/blob/main/doc/planning.png?raw=true)
