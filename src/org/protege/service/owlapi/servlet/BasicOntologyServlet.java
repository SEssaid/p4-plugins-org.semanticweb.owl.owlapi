package org.protege.service.owlapi.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.coode.owl.rdf.rdfxml.RDFXMLRenderer;
import org.semanticweb.owl.model.OWLDataFactory;
import org.semanticweb.owl.model.OWLOntology;
import org.semanticweb.owl.model.OWLOntologyManager;

import uk.ac.manchester.cs.owl.OWLDataFactoryImpl;
import uk.ac.manchester.cs.owl.OWLOntologyManagerImpl;

public class BasicOntologyServlet extends HttpServlet {
    private String name;
    private OWLOntology ontology;
    private OWLOntologyManager ontologyManager;
    
    public BasicOntologyServlet(String name, OWLOntology ontology) {
        this.name = name;
        this.ontology = ontology;
        OWLDataFactory factory = new OWLDataFactoryImpl();
        ontologyManager = new OWLOntologyManagerImpl(factory);
    }
    
    public String getName() {
        return name;
    }
    
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws IOException {
        new RDFXMLRenderer(ontologyManager, ontology, resp.getWriter()).render();
    }
}
