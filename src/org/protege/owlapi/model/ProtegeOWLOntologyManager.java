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
    private OWLOntologyFactory inMemoryOntologyFactory = new EmptyInMemOWLOntologyFactory();
    private OWLOntologyFactory parsableOWLOntologyFactory = new ParsableOWLOntologyFactory();
    
    public ProtegeOWLOntologyManager(OWLDataFactory factory) {
        super(factory);
    }
    
    /**
     * This doesn't work yet...
     * 
     * @param useWriteSafety
     */
    public void setUseWriteSafety(boolean useWriteSafety) {
        this.useWriteSafety = useWriteSafety;
    }
    
    public boolean isUseWriteSafety() {
        return useWriteSafety;
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
    
    public List<OWLOntologyFactory> getOWLOntologyFactories() {
        return new ArrayList<OWLOntologyFactory>(ontologyFactories);
    }

    @Override
    public void addOntologyFactory(OWLOntologyFactory factory) {
        if (useWriteSafety) {
            factory = new WriteSafeOWLOntologyFactory(factory);
        }
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
