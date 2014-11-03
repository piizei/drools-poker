/*
 *
 * @author piizei, @date 11.9.2014 12:46
 */

import spock.lang.*
import spock.lang.Unroll;
import pj.*

class PokerSpec extends Specification{
	
	@Shared pc = new PokerClassifier('src/main/resources/classification-rules.drl')
    
	@Unroll
	def "Given cards should give Rank #rank "() {
		when: "5 cards in hand"
			def hand = new Hand()
			hand.cards =  [
				new Card(rank: rank1, suit: suit1 ),
				new Card(rank: rank2, suit: suit2 ),	
				new Card(rank: rank3, suit: suit3 ),
				new Card(rank: rank4, suit: suit4 ),
				new Card(rank: rank5, suit: suit5 )]
		then: "Hand should have rank #rank" 	

			pc.classify(hand).rank == rank
			 
			
		where:
			rank1 | suit1 | rank2 | suit2 | rank3 | suit3 | rank4 | suit4 | rank5 | suit5 | rank
			  1   |  1    |  10   |  1    |  11   |  1    |  12   |  1    |  13   |  1    | 9
			  9   |  1    |  10   |  1    |  11   |  1    |  12   |  1    |  13   |  1    | 8
			  1   |  1    |  1    |  2    |  1    |  3    |  1    |  4    |  13   |  1    | 7
			  1   |  1    |  1    |  2    |  1    |  3    |  2    |  4    |  2    |  1    | 6
			  1   |  1    |  2    |  1    |  5    |  1    |  6    |  1    |  7    |  1    | 5
			  1   |  2    |  10   |  1    |  11   |  3    |  12   |  1    |  13   |  1    | 4
			  1   |  1    |  1    |  2    |  1    |  3    |  6    |  1    |  7    |  1    | 3
			  1   |  1    |  1    |  2    |  9    |  3    |  9    |  1    |  7    |  1    | 2
			  1   |  1    |  1    |  2    |  9    |  3    |  6    |  1    |  7    |  1    | 1
		
	}
	
	def "Should classify correct all 250k cases"() {
		setup:
			File test = new File('src/main/resources/poker-hand-training-true.data')
		when:
			def hands = []
			test.eachLine {
				def hand = new Hand()
				def row = it.split(',')
				1.step(10,2) { step ->
					def card  = new Card( suit: row[step-1] as int, rank: row[step] as int )
					hand.cards << card
				}
				hand.rank = row[9] as int
				hands << hand
			}
		then:
			hands.each { hand ->
				pc.classify(hand) == hand.rank
			}	
	}
	
}
