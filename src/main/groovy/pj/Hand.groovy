package pj;

import groovy.transform.ToString;
import org.apache.commons.lang.builder.HashCodeBuilder

@ToString
class Hand {
	
	Set cards = []
	def rank = 0
	
	public boolean equals(java.lang.Object other) {
		if(this.is(other)) return true
		if(!(other instanceof Hand)) return false
		return cards.sort() == other.cards.sort()	
	}
	
	def toKey() {
		return cards.sort().inject("", { key, card ->
			key += card.suit as String 
			key += card.rank as String
		})
	}

}
