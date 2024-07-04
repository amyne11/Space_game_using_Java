# Selfish (Space Edition)

## Overview
Selfish (Space Edition) is a thrilling strategic card game set in the vast expanse of space. Players must navigate through a perilous journey, managing their oxygen supply and making critical decisions to ensure their survival. The first astronaut to reach the ship wins, while others face the ultimate demise. This repository contains the Java implementation of the game, following the rules and structure outlined in the UML class diagram.

## Game Objective
The goal of the game is to be the first player to reach the ship while ensuring you have enough oxygen to survive. Use action cards strategically to hinder your opponents and gain an advantage.


## Game Setup
- **Select an Astronaut Card**: Each player chooses an astronaut card.
- **Position the Ship Card**: Place the ship card at the top of the playing area, 6 spaces away from the starting point.
- **Separate and Shuffle Cards**: Separate the oxygen cards from the action cards. Shuffle both decks.
- **Deal Cards**: Each player receives 1 double oxygen card and 4 single oxygen cards. Then, deal 4 action cards to each player.

## Game Phases
- **Card Pickup**: Draw a card from the game deck.
- **Actions**: Play any number of action cards.
- **Breathe or Travel**: Choose to stay put and breathe (consume oxygen) or move forward (consume oxygen and draw a space card).

## Winning the Game
- Reach the ship with at least 1 oxygen card remaining to win.
- If all players run out of oxygen, everyone loses.

## Class Diagram
The project adheres to the provided UML class diagram which details the structure of the game implementation. Key classes include:
- **GameEngine**: Manages game logic and state.
- **Astronaut**: Represents players.
- **Card, Deck, GameDeck, SpaceDeck**: Handle card functionalities and deck operations.
