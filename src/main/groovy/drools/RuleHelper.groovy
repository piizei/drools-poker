package drools

import org.drools.KnowledgeBase
import org.drools.KnowledgeBaseConfiguration
import org.drools.conf.*	
import org.drools.KnowledgeBaseFactory
import org.drools.builder.KnowledgeBuilderFactory
import org.drools.builder.ResourceType
import org.drools.io.ResourceFactory


class RuleHelper {

	public static KnowledgeBase createBase( String ruleFile ) throws Exception {

		def builder = KnowledgeBuilderFactory.newKnowledgeBuilder()
		
		def config = KnowledgeBaseFactory.newKnowledgeBaseConfiguration()
		config.setOption( AssertBehaviorOption.EQUALITY );
		config.setOption( RemoveIdentitiesOption.YES );
		
		def base = KnowledgeBaseFactory.newKnowledgeBase(config)

		builder.add(
				ResourceFactory.newByteArrayResource(ruleFile.getBytes("ISO-8859-1")),
				ResourceType.DRL );

		if (builder.hasErrors())
			throw new RuntimeException("Rule compile errors in files=" + ruleFile + " errors=" + builder.getErrors());
		base.addKnowledgePackages(builder.getKnowledgePackages());

		return base;
	}
}
