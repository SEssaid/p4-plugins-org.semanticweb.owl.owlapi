package org.protege.service.owlapi.ontology;

import java.net.URI;
import java.util.Set;

import org.semanticweb.owl.model.OWLAnnotationAxiom;
import org.semanticweb.owl.model.OWLAntiSymmetricObjectPropertyAxiom;
import org.semanticweb.owl.model.OWLAxiom;
import org.semanticweb.owl.model.OWLAxiomAnnotationAxiom;
import org.semanticweb.owl.model.OWLClass;
import org.semanticweb.owl.model.OWLClassAssertionAxiom;
import org.semanticweb.owl.model.OWLClassAxiom;
import org.semanticweb.owl.model.OWLDataProperty;
import org.semanticweb.owl.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owl.model.OWLDataPropertyAxiom;
import org.semanticweb.owl.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owl.model.OWLDataPropertyExpression;
import org.semanticweb.owl.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owl.model.OWLDataSubPropertyAxiom;
import org.semanticweb.owl.model.OWLDeclarationAxiom;
import org.semanticweb.owl.model.OWLDifferentIndividualsAxiom;
import org.semanticweb.owl.model.OWLDisjointClassesAxiom;
import org.semanticweb.owl.model.OWLDisjointDataPropertiesAxiom;
import org.semanticweb.owl.model.OWLDisjointObjectPropertiesAxiom;
import org.semanticweb.owl.model.OWLDisjointUnionAxiom;
import org.semanticweb.owl.model.OWLEntity;
import org.semanticweb.owl.model.OWLEntityAnnotationAxiom;
import org.semanticweb.owl.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owl.model.OWLEquivalentDataPropertiesAxiom;
import org.semanticweb.owl.model.OWLEquivalentObjectPropertiesAxiom;
import org.semanticweb.owl.model.OWLException;
import org.semanticweb.owl.model.OWLFunctionalDataPropertyAxiom;
import org.semanticweb.owl.model.OWLFunctionalObjectPropertyAxiom;
import org.semanticweb.owl.model.OWLImportsDeclaration;
import org.semanticweb.owl.model.OWLIndividual;
import org.semanticweb.owl.model.OWLIndividualAxiom;
import org.semanticweb.owl.model.OWLInverseFunctionalObjectPropertyAxiom;
import org.semanticweb.owl.model.OWLInverseObjectPropertiesAxiom;
import org.semanticweb.owl.model.OWLIrreflexiveObjectPropertyAxiom;
import org.semanticweb.owl.model.OWLLogicalAxiom;
import org.semanticweb.owl.model.OWLNamedObjectVisitor;
import org.semanticweb.owl.model.OWLNegativeDataPropertyAssertionAxiom;
import org.semanticweb.owl.model.OWLNegativeObjectPropertyAssertionAxiom;
import org.semanticweb.owl.model.OWLObjectProperty;
import org.semanticweb.owl.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owl.model.OWLObjectPropertyAxiom;
import org.semanticweb.owl.model.OWLObjectPropertyChainSubPropertyAxiom;
import org.semanticweb.owl.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owl.model.OWLObjectPropertyExpression;
import org.semanticweb.owl.model.OWLObjectPropertyRangeAxiom;
import org.semanticweb.owl.model.OWLObjectSubPropertyAxiom;
import org.semanticweb.owl.model.OWLObjectVisitor;
import org.semanticweb.owl.model.OWLOntology;
import org.semanticweb.owl.model.OWLOntologyAnnotationAxiom;
import org.semanticweb.owl.model.OWLOntologyManager;
import org.semanticweb.owl.model.OWLPropertyAxiom;
import org.semanticweb.owl.model.OWLReflexiveObjectPropertyAxiom;
import org.semanticweb.owl.model.OWLSameIndividualsAxiom;
import org.semanticweb.owl.model.OWLSubClassAxiom;
import org.semanticweb.owl.model.OWLSymmetricObjectPropertyAxiom;
import org.semanticweb.owl.model.OWLTransitiveObjectPropertyAxiom;
import org.semanticweb.owl.model.SWRLRule;
import org.semanticweb.owl.model.UnknownOWLOntologyException;

public class ChainedOWLOntologyAdapter extends ChainedOWLOntology {
    
    public ChainedOWLOntologyAdapter(OWLOntology delegate) {
        super(delegate);
    }

	public boolean containsAxiom(OWLAxiom ax) {
		return getDelegate().containsAxiom(ax);
	}

	public boolean containsClassReference(URI uri) {
		return getDelegate().containsClassReference(uri);
	}

	public boolean containsDataPropertyReference(URI property) {
		return getDelegate().containsDataPropertyReference(property);
	}

	public boolean containsDataTypeReference(URI uri) {
        return getDelegate().containsDataTypeReference(uri);
	}

	public boolean containsEntityDeclaration(OWLEntity decl) {
        return getDelegate().containsEntityDeclaration(decl);
	}

	public boolean containsEntityReference(OWLEntity entity) {
        return getDelegate().containsEntityReference(entity);
	}

	public boolean containsIndividualReference(URI individual) {
        return getDelegate().containsIndividualReference(individual);
	}

	public boolean containsObjectPropertyReference(URI property) {
        return getDelegate().containsObjectPropertyReference(property);
	}

	public Set<OWLAnnotationAxiom> getAnnotationAxioms() {
	    return getDelegate().getAnnotationAxioms();
	}

	public Set<URI> getAnnotationURIs() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLAxiomAnnotationAxiom> getAnnotations(OWLAxiom axiom) {
        return getDelegate().getAnnotations(axiom);
	}

	public Set<OWLOntologyAnnotationAxiom> getAnnotations(OWLOntology ont) {
        return getDelegate().getAnnotations(ont);
	}

	public OWLAntiSymmetricObjectPropertyAxiom getAntiSymmetricObjectPropertyAxiom(OWLObjectPropertyExpression property) {
        return getDelegate().getAntiSymmetricObjectPropertyAxiom(property);
	}

	public Set<OWLAxiom> getAxioms() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLClassAxiom> getAxioms(OWLClass cls) {
        return getDelegate().getAxioms(cls);
	}

	public Set<OWLObjectPropertyAxiom> getAxioms(OWLObjectPropertyExpression property) {
        return getDelegate().getAxioms(property);
	}

	public Set<OWLDataPropertyAxiom> getAxioms(OWLDataProperty dataproperty) {
        return getDelegate().getAxioms(dataproperty);
	}

	public Set<OWLIndividualAxiom> getAxioms(OWLIndividual individual) {
        return getDelegate().getAxioms(individual);
	}

	public Set<OWLClassAssertionAxiom> getClassAssertionAxioms(OWLIndividual individual) {
        return getDelegate().getClassAssertionAxioms(individual);
	}

	public Set<OWLClassAssertionAxiom> getClassAssertionAxioms(OWLClass arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLClassAxiom> getClassAxioms() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLDataPropertyAssertionAxiom> getDataPropertyAssertionAxioms(OWLIndividual individual) {
        return getDelegate().getDataPropertyAssertionAxioms(individual);
	}

	public Set<OWLPropertyAxiom> getDataPropertyAxioms() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLDataPropertyDomainAxiom> getDataPropertyDomainAxioms(OWLDataProperty dataproperty) {
        return getDelegate().getDataPropertyDomainAxioms(dataproperty);
	}

	public Set<OWLDataPropertyRangeAxiom> getDataPropertyRangeAxiom(OWLDataProperty dataproperty) {
        return getDelegate().getDataPropertyRangeAxiom(dataproperty);
	}

	public Set<OWLDataSubPropertyAxiom> getDataSubPropertyAxiomsForLHS(OWLDataProperty dataproperty) {
        return getDelegate().getDataSubPropertyAxiomsForLHS(dataproperty);
	}

	public Set<OWLDataSubPropertyAxiom> getDataSubPropertyAxiomsForRHS(OWLDataPropertyExpression dataproperty) {
        return getDelegate().getDataSubPropertyAxiomsForRHS(dataproperty);
	}

	public Set<OWLDeclarationAxiom> getDeclarationAxioms() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLDeclarationAxiom> getDeclarationAxioms(OWLEntity entity) {
        return getDelegate().getDeclarationAxioms(entity);
	}

	public Set<OWLDifferentIndividualsAxiom> getDifferentIndividualAxioms(OWLIndividual individual) {
        return getDelegate().getDifferentIndividualAxioms(individual);
	}

	public Set<OWLDisjointClassesAxiom> getDisjointClassesAxioms(OWLClass cls) {
        return getDelegate().getDisjointClassesAxioms(cls);
	}

	public Set<OWLDisjointDataPropertiesAxiom> getDisjointDataPropertiesAxiom(OWLDataProperty dataproperty) {
        return getDelegate().getDisjointDataPropertiesAxiom(dataproperty);
	}

	public Set<OWLDisjointObjectPropertiesAxiom> getDisjointObjectPropertiesAxiom(OWLObjectPropertyExpression dataproperty) {
        return getDelegate().getDisjointObjectPropertiesAxiom(dataproperty);
	}

	public Set<OWLDisjointUnionAxiom> getDisjointUnionAxioms(OWLClass cls) {
        return getDelegate().getDisjointUnionAxioms(cls);
	}

	public Set<OWLEntityAnnotationAxiom> getEntityAnnotationAxioms(OWLEntity entity) {
        return getDelegate().getEntityAnnotationAxioms(entity);
	}

	public Set<OWLEquivalentClassesAxiom> getEquivalentClassesAxioms(OWLClass cls) {
        return getDelegate().getEquivalentClassesAxioms(cls);
	}

	public Set<OWLEquivalentDataPropertiesAxiom> getEquivalentDataPropertiesAxiom(
			OWLDataProperty arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLEquivalentObjectPropertiesAxiom> getEquivalentObjectPropertiesAxioms(
			OWLObjectPropertyExpression arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public OWLFunctionalDataPropertyAxiom getFunctionalDataPropertyAxiom(
			OWLDataPropertyExpression arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public OWLFunctionalObjectPropertyAxiom getFunctionalObjectPropertyAxiom(
			OWLObjectPropertyExpression arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLClassAxiom> getGeneralClassAxioms() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLOntology> getImports(OWLOntologyManager arg0)
			throws UnknownOWLOntologyException {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLImportsDeclaration> getImportsDeclarations() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLIndividualAxiom> getIndividualAxioms() {
		// TODO Auto-generated method stub
		return null;
	}

	public OWLInverseFunctionalObjectPropertyAxiom getInverseFunctionalObjectPropertyAxiom(
			OWLObjectPropertyExpression arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLInverseObjectPropertiesAxiom> getInverseObjectPropertyAxioms(
			OWLObjectPropertyExpression arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public OWLIrreflexiveObjectPropertyAxiom getIrreflexiveObjectPropertyAxiom(
			OWLObjectPropertyExpression arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLLogicalAxiom> getLogicalAxioms() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLNegativeDataPropertyAssertionAxiom> getNegativeDataPropertyAssertionAxioms(
			OWLIndividual arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLNegativeObjectPropertyAssertionAxiom> getNegativeObjectPropertyAssertionAxioms(
			OWLIndividual arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLObjectPropertyAssertionAxiom> getObjectPropertyAssertionAxioms(
			OWLIndividual arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLPropertyAxiom> getObjectPropertyAxioms() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLObjectPropertyDomainAxiom> getObjectPropertyDomainAxioms(
			OWLObjectPropertyExpression arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLObjectPropertyRangeAxiom> getObjectPropertyRangeAxioms(
			OWLObjectPropertyExpression arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLObjectSubPropertyAxiom> getObjectSubPropertyAxiomsForLHS(
			OWLObjectPropertyExpression arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLObjectSubPropertyAxiom> getObjectSubPropertyAxiomsForRHS(
			OWLObjectPropertyExpression arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLObjectPropertyChainSubPropertyAxiom> getPropertyChainSubPropertyAxioms() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLClass> getReferencedClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLDataProperty> getReferencedDataProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLIndividual> getReferencedIndividuals() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLObjectProperty> getReferencedObjectProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLAxiom> getReferencingAxioms(OWLEntity arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public OWLReflexiveObjectPropertyAxiom getReflexiveObjectPropertyAxiom(
			OWLObjectPropertyExpression arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<SWRLRule> getRules() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLSameIndividualsAxiom> getSameIndividualAxioms(
			OWLIndividual arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLSubClassAxiom> getSubClassAxiomsForLHS(OWLClass arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<OWLSubClassAxiom> getSubClassAxiomsForRHS(OWLClass arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public OWLSymmetricObjectPropertyAxiom getSymmetricObjectPropertyAxiom(
			OWLObjectPropertyExpression arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public OWLTransitiveObjectPropertyAxiom getTransitiveObjectPropertyAxiom(
			OWLObjectPropertyExpression arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isPunned(URI arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public void accept(OWLNamedObjectVisitor arg0) throws OWLException {
		// TODO Auto-generated method stub

	}

	public URI getURI() {
		// TODO Auto-generated method stub
		return null;
	}

	public void accept(OWLObjectVisitor arg0) {
		// TODO Auto-generated method stub

	}

}
