package org.protege.service.owlapi.pub;

import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.semanticweb.owl.model.OWLOntology;

public class OntologyServiceUtil {
    public final static String ONTOLOGY_URI_PROPERTY  = "owl.ontology.uri";
    public final static String ONTOLOGY_NAME_PROPERTY = "owl.ontology.name";
    public final static String SERVLET_URL_LOCATION   = "/owlapi";
    
    public static Set<String> getOntologyNames(BundleContext context) {
        Set<String> results = new HashSet<String>();
        try {
            for (ServiceReference sr : context.getServiceReferences(OWLOntology.class.getName(), null)) {
                Object o = sr.getProperty(ONTOLOGY_NAME_PROPERTY);
                if (o != null && o instanceof String) {
                    results.add((String) o);
                }
            }
        } catch (InvalidSyntaxException e) {
            e.printStackTrace();
        }
        return results;
    }

    @SuppressWarnings("unchecked")
    public static ServiceRegistration registerOntology(BundleContext context, String name, OWLOntology ontology) {
        Dictionary d = new Hashtable();
        d.put(ONTOLOGY_URI_PROPERTY, ontology.getURI().toString());
        d.put(ONTOLOGY_NAME_PROPERTY, name);
        
        return context.registerService(OWLOntology.class.getName(), ontology, d);
    }


}
