package com.ontology.utilization.generator;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.VCARD;
import com.ontology.utilization.domain.BooleanChromosome;
import com.ontology.utilization.domain.representation.Range;
import com.ontology.utilization.domain.representation.RuleRepresentation;
import com.ontology.utilization.generator.components.BreastCancerAttribute;
import com.ontology.utilization.generator.components.BreastCancerAttributeRange;
import com.ontology.utilization.generator.enums.BreastCancerType;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OntologyGenerator {

	private final String DELIMITER = "-";

	public Model generateOntology(BooleanChromosome bestBooleanChromosome, boolean malignantRule, int minAttributeValue, int maxAttributeValue) {

            List<BreastCancerAttribute> rule = createRepresentation(bestBooleanChromosome, malignantRule, minAttributeValue, maxAttributeValue);
            
            
		Model rdfModel = ModelFactory.createDefaultModel();
		String NS = "http://breast-cancer.com/";
		Property predicateSubClass = rdfModel.createProperty(NS + "Subclass");
		Property predicateSubsumption = rdfModel.createProperty(NS
				+ "Subsumption");
		Property predicateAnd = rdfModel.createProperty(NS + "And");
		Property predicateOr = rdfModel.createProperty(NS + "Or");

		Resource breastCancerModel = rdfModel.createResource(NS
				+ "BreastCancer");

		Resource breastCancerResource = rdfModel.createResource(NS
				+ "Breast Cancer");
		// Resource attributes = rdfModel.createResource(NS + "Attribute");
		breastCancerModel.addProperty(VCARD.CLASS, breastCancerResource);
		// breastCancer.addProperty(VCARD.CLASS, attributes);

		for (BreastCancerAttribute ruleAttribute : rule) {

			for (BreastCancerAttributeRange range : ruleAttribute.getRanges()) {

				// Check if the consequence (breast cancer type) is already in
				// breastCancerResource. If not then add.
				BreastCancerType type = range.getCancerType();
				Resource cancerType = null;
				StmtIterator iter = breastCancerResource.listProperties();
				while (iter.hasNext()) {
					Statement stmt = iter.nextStatement();
					Resource subject = stmt.getSubject();

					if (subject.hasURI(NS + type.getValue())) {
						cancerType = subject;
					}

				}
				if (cancerType == null) {
					cancerType = rdfModel.createResource(NS + type.getValue());
					breastCancerResource.addProperty(predicateSubClass,
							cancerType);
				}

				// Add concepts of attributes to breast cancer type.
				Resource breastcancerAttribute = null;
				iter = cancerType.listProperties();
				while (iter.hasNext()) {
					Statement stmt = iter.nextStatement();
					Resource subject = stmt.getSubject();

					if (subject.hasURI(NS + ruleAttribute.getAttributeName())) {
						breastcancerAttribute = subject;
					}

				}
				if (breastcancerAttribute == null) {
					breastcancerAttribute = rdfModel.createResource(NS
							+ ruleAttribute.getAttributeName() + DELIMITER
							+ type.getValue());
					cancerType.addProperty(predicateSubsumption,
							breastcancerAttribute);
				}

				// Create and add sub-concept to concept.
				Property from = rdfModel.createProperty(NS + "From");
				Property to = rdfModel.createProperty(NS + "To");
				Resource subAttribute = rdfModel.createResource(NS
						+ range.getRangePrefix() + DELIMITER
						+ ruleAttribute.getAttributeName());
				subAttribute.addProperty(from, String.valueOf(range.getFrom()),
						XSDDatatype.XSDint);
				subAttribute.addProperty(to, String.valueOf(range.getTo()),
						XSDDatatype.XSDint);

				breastcancerAttribute.addProperty(predicateSubsumption,
						subAttribute);
			}
		}

		// ADD OR statements between the subconcepts in each concept.
		StmtIterator iter = breastCancerResource.listProperties();
		while (iter.hasNext()) {
			Statement stmt = iter.nextStatement();
			RDFNode breastCancerTypeNode = stmt.getObject();
			Resource breastCancerType = (Resource) breastCancerTypeNode;

			StmtIterator iter2 = breastCancerType.listProperties();
			while (iter2.hasNext()) {

				Statement stmt2 = iter2.nextStatement();
				RDFNode breastCancerAttributeNode = stmt2.getObject();
				Resource breastCancerAttribute = (Resource) breastCancerAttributeNode;

				StmtIterator subiter = breastCancerAttribute.listProperties();
				while (subiter.hasNext()) {
					Statement substmt = subiter.nextStatement();
					RDFNode breastCancerSubattributeNode = substmt.getObject();
					Resource breastCancerSubattribute = (Resource) breastCancerSubattributeNode;

					StmtIterator subiter2 = breastCancerAttribute
							.listProperties();
					while (subiter2.hasNext()) {
						Statement substmt2 = subiter2.nextStatement();
						RDFNode breastCancerSubattributeNode2 = substmt2
								.getObject();
						Resource breastCancerSubattribute2 = (Resource) breastCancerSubattributeNode2;

						if (!(breastCancerSubattribute.getURI()
								.equals(breastCancerSubattribute2.getURI()))) {
							Statement st = rdfModel.createStatement(
									breastCancerSubattribute, predicateOr,
									breastCancerSubattribute2);
							rdfModel.add(st);
						}
					}
				}
			}
		}

		// ADD AND statements between the concepts in each cancer type concept.
		iter = breastCancerResource.listProperties();
		while (iter.hasNext()) {
			Statement stmt = iter.nextStatement();
			RDFNode breastCancerTypeNode = stmt.getObject();
			Resource breastCancerType = (Resource) breastCancerTypeNode;

			StmtIterator iter2 = breastCancerType.listProperties();
			while (iter2.hasNext()) {

				Statement stmt2 = iter2.nextStatement();
				RDFNode breastCancerAttributeNode = stmt2.getObject();
				Resource breastCancerAttribute = (Resource) breastCancerAttributeNode;

				StmtIterator iter3 = breastCancerType.listProperties();
				while (iter3.hasNext()) {
					Statement stmt3 = iter3.nextStatement();
					RDFNode breastCancerAttributeNode2 = stmt3.getObject();
					Resource breastCancerAttribute2 = (Resource) breastCancerAttributeNode2;

					if (!(breastCancerAttribute.getURI()
							.equals(breastCancerAttribute2.getURI()))) {
						Statement st = rdfModel.createStatement(
								breastCancerAttribute, predicateAnd,
								breastCancerAttribute2);
						rdfModel.add(st);
					}
				}
			}
		}

                System.out.println(rdfModel.toString());
                
		return rdfModel;
	}
        
        private List<BreastCancerAttribute> createRepresentation(BooleanChromosome bestBooleanChromosome, boolean malignantRule, int minAttributeValue, int maxAttributeValue) {
            		BreastCancerType thisRule = null;
		BreastCancerType theOtherRule = null;
		if (malignantRule) {
			thisRule = BreastCancerType.MALIGNANT;
			theOtherRule = BreastCancerType.BENIGN;
		} else {
			thisRule = BreastCancerType.BENIGN;
			theOtherRule = BreastCancerType.MALIGNANT;
		}

		RuleRepresentation ruleRepresentation = bestBooleanChromosome
				.getChromosomeRuleRepresentation();
		Map<Integer, List<Range>> classifiers = ruleRepresentation
				.getClassifiers();

		// assert map has enough keys

		List<BreastCancerAttribute> result = new LinkedList<BreastCancerAttribute>();

		for (int i = 0; i < 9; i++) {
			List<Range> ranges = classifiers.get(i);

			String name = null;
			switch (i) {
			case 0:
				name = "Clump Thickness";
				break;
			case 1:
				name = "Uniformity of Cell Size";
				break;
			case 2:
				name = "Uniformity of Cell Shape";
				break;
			case 3:
				name = "Marginal Adhesion";
				break;
			case 4:
				name = "Single Epithelial Cell Size";
				break;
			case 5:
				name = "Bare Nuclei";
				break;
			case 6:
				name = "Bland Chromatin";
				break;
			case 7:
				name = "Normal Nucleoli";
				break;
			case 8:
				name = "Mitoses";
				break;
			}

			BreastCancerAttribute attribute = new BreastCancerAttribute(name);

			if (ranges == null || ranges.isEmpty()) {
				attribute.addRange(new BreastCancerAttributeRange(
						minAttributeValue, maxAttributeValue, theOtherRule));
			} else {

				for (int j = 0; j < ranges.size(); j++) {
					Range ran = ranges.get(j);
					Range r = new Range();
					r.setFrom(ran.getFrom() + 1);
					r.setTo(ran.getTo() + 1);

					if (j == 0) {
						if (r.getFrom() > minAttributeValue) {
							attribute.addRange(new BreastCancerAttributeRange(
									minAttributeValue, r.getFrom() - 1,
									theOtherRule));
						}

						attribute.addRange(new BreastCancerAttributeRange(r
								.getFrom(), r.getTo(), thisRule));

					} else {

						Range previousRange = ranges.get(j - 1);
						if (r.getFrom() > previousRange.getTo() + 1) {
							attribute.addRange(new BreastCancerAttributeRange(
									previousRange.getTo() + 1 + 1,
									r.getFrom() - 1, theOtherRule));
						}

						attribute.addRange(new BreastCancerAttributeRange(r
								.getFrom(), r.getTo(), thisRule));

					}

					if (j == (ranges.size() - 1)) {
						if (r.getTo() < maxAttributeValue) {
							attribute.addRange(new BreastCancerAttributeRange(r
									.getTo() + 1, maxAttributeValue,
									theOtherRule));
						}
					}

				}
			}

			result.add(attribute);

		}

		return result;
        }

}
