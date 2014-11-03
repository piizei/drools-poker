package pj

import drools.RuleHelper

class PokerClassifier {
	
	def kbase
	
	PokerClassifier(String rulefile) {
		kbase = RuleHelper.createBase(new File(rulefile).text)
		
	}
	
	ClassificationResult classify(hand) {
		def session = kbase.newStatelessKnowledgeSession()
					
		def result = new ClassificationResult()
		
		def toRuleEngine = []
		toRuleEngine << result
		toRuleEngine.addAll(hand.cards)
		
		session.execute( toRuleEngine )
		
		return result
	}

}
