# Advent-Of-Code-9

Written in Java

Per http://adventofcode.com/day/9

Some testing is included (it all works) but is fragile against edge cases and nonsensical inputs. For example, if you add really huge values (ie. a quadrillion miles from point a to b) that will overflow the integer tracking the route size, it will loop back to a negative and be considered a 'small' trip instead.
