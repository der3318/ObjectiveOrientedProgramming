## **Report (Homework 4)**
#### **Student ID and Name**
* B03902015
* Â²Þ³¼w
    
    
#### **The Player's Strategy**
* If the player's opened-card is **not** `ACE`, he will buy the insurance.
* The player **never** surrenders, since he considers confidence important.
* The player will doulbe down only when `10` < the value of the current hand < `16`
* If the total value of the current hand is **lower** than `16`, the player will split his current hand.
* The player won't stop hitting until the total value is **more** than `15`
* If the player loses, he will bet **more** in the next round, in order to earn the money back.
    
    
#### **Framework**
![Image Not Found](http://i.imgur.com/5amRhDy.jpg)
* `PlayerB03902015.java`: The player that I implemented
* `Blackjack.java`: The **Game Instance**, which can be started by calling public method `start()`
* `CardDeck.java`: It represents a deck of cards. A random card is available with `getRandCard()`
* `CardSet.java`: It maintains the imformation of a hand.
* `POOCasion.java`: The start point. It will deal with the `args` and start the `Blackjack` correctly.
* `Printer.java`: It consists of some static methods, which are responsible for output messages.
    
    
#### **The Result of the Duel**
* Classmate's Name and ID: ¾G¼wÄÉ B03902007
* Rounds = `100`
* Initial Chips = `1000`
![img](http://i.imgur.com/6b3ukPy.jpg)
    
#### **Bonus**
* I did nothing worth the bonus points.
* However, I tried my best to make my codes readable, including adding comments on every section.
    
