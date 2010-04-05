package org.protege.owlapi.model;

import java.util.ArrayList;
import java.util.List;

import org.protege.owlapi.concurrent.WriteSafeOWLOntologyFactory;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntologyFactory;

import uk.ac.manchester.cs.owl.owlapi.EmptyInMemOWLOntologyFactory;
import uk.ac.manchester.cs.owl.owlapi.OWLOntologyManagerImpl;
import uk.ac.manchester.cs.owl.owlapi.ParsableOWLOntologyFactory;



public class ProtegeOWLOntologyManager extends OWLOntologyManagerImpl {
    private boolean useWriteSafety = false;
    private List<OWLOntologyFactory> ontologyFactories = new ArrayList<OWLOntologyFactory>();

    
    public ProtegeOWLOntologyManager(OWLDataFactory factory) {
        super(factory);
    }
    
    public void setUseWriteSafety(boolean useWriteSafety) {
        this.useWriteSafety = useWriteSafety;
    }
    
    public boolean isUseWriteSafety() {
        return useWriteSafety;
    }
    
    
    public List<OWLOntologyFactory> getOWLOntologyFactories() {
        return new ArrayList<OWLOntologyFactory>(ontologyFactories);
    }

    @Override
    public void addOntologyFactory(OWLOntologyFactory factory) {
        factory = wrapFactory(factory);
        super.addOntologyFactory(factory);
        ontologyFactories.add(0, factory);
    }
    
    @Override
    public void removeOntologyFactory(OWLOntologyFactory factory) {
        factory = wrapFactory(factory); // otherwise .equals won't work in both directions
        super.removeOntologyFactory(factory);
        ontologyFactories.remove(factory);
    }
    
    private OWLOntologyFactory wrapFactory(OWLOntologyFactory factory) {
        if (useWriteSafety && !(factory instanceof WriteSafeOWLOntologyFactory)) {
            factory = new WriteSafeOWLOntologyFactory(factory);
        }
        return factory;
    }
    
    public void clearOntologyFactories() {
        for (OWLOntologyFactory factory : new ArrayList<OWLOntologyFactory>(ontologyFactories)) {
            removeOntologyFactory(factory);
        }
    }
}
