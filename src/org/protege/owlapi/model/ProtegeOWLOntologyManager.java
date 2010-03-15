package org.protege.owlapi.model;

import java.util.ArrayList;
import java.util.List;

import org.protege.owlapi.concurrent.WriteSafeOWLOntology;
import org.protege.owlapi.concurrent.WriteSafeOWLOntologyImpl;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLMutableOntology;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyFactory;

import uk.ac.manchester.cs.owl.owlapi.EmptyInMemOWLOntologyFactory;
import uk.ac.manchester.cs.owl.owlapi.OWLOntologyManagerImpl;
import uk.ac.manchester.cs.owl.owlapi.ParsableOWLOntologyFactory;



public class ProtegeOWLOntologyManager extends OWLOntologyManagerImpl {
    private boolean useWriteSafety = false;
    private List<OWLOntologyFactory> ontologyFactories = new ArrayList<OWLOntologyFactory>();
    private OWLOntologyFactory inMemoryOntologyFactory = new EmptyInMemOWLOntologyFactory();
    private OWLOntologyFactory parsableOWLOntologyFactory = new ParsableOWLOntologyFactory();
    
    public ProtegeOWLOntologyManager(OWLDataFactory factory) {
        super(factory);
    }
    
    public void setUseWriteSafety(boolean useWriteSafety) {
        this.useWriteSafety = useWriteSafety;
    }
    
    public boolean isUseWriteSafety() {
        return useWriteSafety;
    }
    
    @Override
    public void ontologyCreated(OWLOntology ontology) {
        if (useWriteSafety 
                && ontology instanceof OWLMutableOntology 
                && !(ontology instanceof WriteSafeOWLOntology)) {
            ontology = new WriteSafeOWLOntologyImpl((OWLMutableOntology) ontology);
        }
        super.ontologyCreated(ontology);
    }
    
    public void useStandardFactories() {
        clearOntologyFactories();
        addOntologyFactory(inMemoryOntologyFactory);
        addOntologyFactory(parsableOWLOntologyFactory);
    }
    
    public OWLOntologyFactory getInMemoryOntologyFactory() {
        return inMemoryOntologyFactory;
    }

    public OWLOntologyFactory getParsableOWLOntologyFactory() {
        return parsableOWLOntologyFactory;
    }

    @Override
    public void addOntologyFactory(OWLOntologyFactory factory) {
        super.addOntologyFactory(factory);
        ontologyFactories.add(0, factory);
    }
    
    @Override
    public void removeOntologyFactory(OWLOntologyFactory factory) {
        super.removeOntologyFactory(factory);
        ontologyFactories.remove(factory);
    }
    
    public void clearOntologyFactories() {
        for (OWLOntologyFactory factory : ontologyFactories) {
            removeOntologyFactory(factory);
        }
    }
}
