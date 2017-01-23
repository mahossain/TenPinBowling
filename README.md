
# TenPinBowling

## Description
A game consists of ten frames, which start with a full rack of ten pins. 
In each frame, you have two deliveries of your ball, in which to knock down as many of the ten pins as you can.
If you knock down all the pins on your first ball, it is called a <b>strike.</b> 
The score does not get added on straight away because for a strike, you get the values of your next two balls as a bonus. 
For example, if you score a strike in the first frame, then an 7 and 1 in the second frame, you would score 18 (10+7+1) for the first frame, and 8 for the second frame, making a total of 26 after two frames.
<br/>

If you knock down some of the pins on the first ball, and knocked down the remainder of the pins in the second ball,
it is known as a <b>spare</b>. Again, the score does not get added on straight away because for a spare, you get the values of your next ball as a bonus.

for more details visit: http://www.topendsports.com/sport/tenpin/scoring.htm

## Requirements
Java 1.8 <br/>


## How To Run

<b>mvn clean install<b> command will build and run the tests besides will generate the jar file(TenPinBowling-1.0.jar)
 in target folder.
<br/>
cd target <br/>

java -jar TenPinBowling-1.0.jar "9 1 9 1"

<br/>

if you don't provide any input by default it will use the following inputs
<br/>
"9 1 9 1"<br/>
"10 10 10 10 10 10 10 10 10 10 10 10" <br/>
"1 2 3 4" <br/>
"1 1 1 1 10 1 1" <br/>

<br/>
please go throw the Main.java file for better understanding

## Cloning a Repository
git clone https://github.com/mahossain/TenPinBowling.git
