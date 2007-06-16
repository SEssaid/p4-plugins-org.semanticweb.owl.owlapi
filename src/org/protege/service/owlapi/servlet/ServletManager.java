package org.protege.service.owlapi.servlet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.protege.service.owlapi.pub.OntologyServiceUtil;
import org.semanticweb.owl.model.OWLOntology;

public class ServletManager {
    private BundleContext context;
    private BundleServlet configure;
    private Map<String, BasicOntologyServlet> ontologyServlets = new HashMap<String, BasicOntologyServlet>();
    private Set<HttpService> httpServers = new HashSet<HttpService>();
    
    public ServletManager(BundleContext context) {
        this.context = context;
        configure = new BundleServlet(context);
        initialize();
        context.addServiceListener(new HttpListener());
        context.addServiceListener(new OntologyListener());
    }
    
    public void destroy() {
        
    }
        
    private void initialize() {
        try {
            ServiceReference[] httpReferences = context.getServiceReferences(HttpService.class.getName(), null);
            if (httpReferences != null) {
                for (ServiceReference sr : httpReferences) {
                    HttpService http = (HttpService) context.getService(sr);
                    newHttpService(http);
                }
            }
            ServiceReference[] ontReferences = context.getServiceReferences(OWLOntology.class.getName(), null);
            if (ontReferences != null) {
                for (ServiceReference sr : ontReferences) {
                    String name = (String) sr.getProperty(OntologyServiceUtil.ONTOLOGY_NAME_PROPERTY);
                    OWLOntology ontology = (OWLOntology) context.getService(sr);
                    newOntology(name, ontology);
                }
            }
        }
        catch (InvalidSyntaxException ise) {
            ise.printStackTrace();
        }
    }
    
    private void newHttpService(HttpService server) {
        httpServers.add(server);
        try {
            server.registerServlet(OntologyServiceUtil.SERVLET_URL_LOCATION, configure, new Hashtable(), null);
            for (BasicOntologyServlet servlet : ontologyServlets.values()) {
                registerServlet(server, servlet);
            }
        }
        catch (Exception  e) {
            e.printStackTrace();
        }
    }
    
    private void newOntology(String name, OWLOntology ontology) {
        BasicOntologyServlet servlet = new BasicOntologyServlet(name, ontology);
        ontologyServlets.put(name, servlet);
        for (HttpService server : httpServers) {
            registerServlet(server, servlet);
        }
    }
    
    private void registerServlet(HttpService http, BasicOntologyServlet servlet) {
        try {
            http.registerServlet(OntologyServiceUtil.SERVLET_URL_LOCATION + "/" + servlet.getName(), 
                                 servlet,
                                 new Hashtable(), http.createDefaultHttpContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private class HttpListener implements ServiceListener {

        public void serviceChanged(ServiceEvent event) {
            ServiceReference sr = event.getServiceReference();
            Object o = context.getService(sr);
            if (o instanceof HttpService) {
                HttpService server = (HttpService) o;
                if (event.getType() == ServiceEvent.REGISTERED) {
                    newHttpService(server);
                }
                else if (event.getType() == ServiceEvent.UNREGISTERING) {
                    httpServers.remove(server);
                }
            }
            else context.ungetService(sr);
        }
    }
    
    private class OntologyListener implements ServiceListener {

        public void serviceChanged(ServiceEvent event) {
            ServiceReference sr = event.getServiceReference();
            Object o = context.getService(sr);
            if (o instanceof OWLOntology) {
                if (event.getType() == ServiceEvent.REGISTERED) {
                    String name = (String) sr.getProperty(OntologyServiceUtil.ONTOLOGY_NAME_PROPERTY);
                    OWLOntology ontology = (OWLOntology) o;
                    newOntology(name, ontology);
                }
                else if (event.getType() == ServiceEvent.UNREGISTERING) {
                    String name = (String) sr.getProperty(OntologyServiceUtil.ONTOLOGY_NAME_PROPERTY);
                    ontologyServlets.remove(name);
                    for (HttpService server : httpServers) {
                        server.unregister(OntologyServiceUtil.SERVLET_URL_LOCATION + "/" + name);
                    }
                }

            }
            else context.ungetService(sr);
        }

    }
}
