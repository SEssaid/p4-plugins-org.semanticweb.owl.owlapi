package org.protege.service.owlapi.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogService;
import org.protege.service.owlapi.Activator;
import org.protege.service.owlapi.pub.OntologyServiceUtil;
import org.semanticweb.owl.apibinding.OWLManager;
import org.semanticweb.owl.model.OWLOntology;
import org.semanticweb.owl.model.OWLOntologyManager;

public class BundleServlet extends HttpServlet {
    public static final String SHORT_NAME_PARAM   = "ShortName";
    public static final String URI_PARAM          = "URI";
    public static final String DOIT_PARAM         = "Execute";
    public static final String ADD_ONTOLOGY_VALUE = "Add Ontology";
    
    
    private BundleContext context;
    private String bundle_name;
    private OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
    
    public BundleServlet(BundleContext context) {
        this.context = context;
        bundle_name = (String) context.getBundle().getHeaders().get(Constants.BUNDLE_NAME);
    }
    

    
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
        String title = "Configuration for " + bundle_name + " bundle";
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">");
        out.println("<HTML>\n" +
                    "<HEAD><TITLE>" + title + " </TITLE></HEAD>\n");
        out.println("<BODY>");
        out.println("<CENTER>" + title + "</CENTER><BR>");

        try {
            ServiceReference[] srs = context.getServiceReferences(OWLOntology.class.getName(), null);
            if (srs == null || srs.length == 0) {
                out.println("No ontologies currently loaded - try http://www.co-ode.org/ontologies/pizza/2005/10/18/pizza.owl");
            }
            else {
                out.println("Currently loaded ontologies: <UL>");
                for (ServiceReference sr : srs) {
                    Object name = sr.getProperty(OntologyServiceUtil.ONTOLOGY_NAME_PROPERTY);
                    Object uri = sr.getProperty(OntologyServiceUtil.ONTOLOGY_URI_PROPERTY);
                    out.println("<LI> " + name + " (<a href=\"" + OntologyServiceUtil.SERVLET_URL_LOCATION + "/" + name + "\">" + uri + "</a>)");
                }
            }
        } catch (InvalidSyntaxException e) {
            e.printStackTrace();
        }
        out.println("</UL>");
        out.println("<FORM METHOD=\"POST\">");
        out.println("<TABLE BORDER=\"1\">");
        out.println("<TR><TD>Ontology Name:</TD><TD><INPUT  TYPE=\"TEXT\" NAME=\"" + SHORT_NAME_PARAM + "\"></TD><TR>");
        out.println("<TR><TD>Ontology URI:</TD><TD><INPUT TYPE=\"TEXT\" NAME=\"" + URI_PARAM + "\"></TD></TR>");
        out.println("</TABLE>");
        out.println("<INPUT TYPE=\"SUBMIT\" NAME=\""+ DOIT_PARAM + "\" VALUE=\"" + ADD_ONTOLOGY_VALUE + "\"><BR>");
        out.println("</FORM>");
        out.println("</BODY>");
        out.println("</HTML>");
    }
    
    protected void doPost(HttpServletRequest req, 
                          HttpServletResponse resp) throws IOException {
        String action = req.getParameter(DOIT_PARAM);
        if (action.equals(ADD_ONTOLOGY_VALUE)) {
            String shortName = req.getParameter(SHORT_NAME_PARAM);
            String uri = req.getParameter(URI_PARAM);
            OWLOntology ontology;
            try {
                ontology = manager.loadOntology(new URI(uri));
            } catch (Exception e) {
                IOException ioe = new IOException(e.getMessage());
                ioe.initCause(e);
                Activator.log.log(LogService.LOG_ERROR, "Exception caught reading url", ioe);
                throw ioe;
            }
            OntologyServiceUtil.registerOntology(context, shortName, ontology);
        }
        doGet(req, resp);
    }

}
