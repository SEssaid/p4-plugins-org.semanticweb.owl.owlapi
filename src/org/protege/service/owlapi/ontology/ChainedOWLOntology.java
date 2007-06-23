package org.protege.service.owlapi.ontology;

import org.semanticweb.owl.model.OWLOntology;

public abstract class ChainedOWLOntology implements OWLOntology {
	
	protected OWLOntology delegate;
	
	protected OWLOntology getDelegate() {
		return delegate;
	}
	
	public ChainedOWLOntology(OWLOntology delegate) {
		this.delegate = delegate;
	}

}
