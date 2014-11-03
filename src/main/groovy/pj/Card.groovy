package pj

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class Card  implements Comparable<Card> {
	
	int suit
	int rank
	
	int compareTo(Card other) {
		if (rank - other.rank == 0) return suit-other.suit
		else return rank-other.rank 
	}
 	
}
