package org.protege.service.owlapi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogService;
import org.protege.service.owlapi.servlet.ServletManager;
import org.semanticweb.owl.model.OWLOntology;

public class Activator implements BundleActivator {
    public static LogService log;
    private BundleContext context;
    private ServletManager manager;

    public void start(BundleContext context) throws Exception {
        this.context = context;

        setupLogger();
        manager = new ServletManager(context);  
        log.log(LogService.LOG_INFO, "Bundle started");
    }

    public void stop(BundleContext context) throws Exception {
        manager.destroy();
        manager = null;
        this.context = null;
        log.log(LogService.LOG_INFO, "Bundle stopped");
    }

    public BundleContext getContext() {
        return context;
    }
    
    private void setupLogger() {
        ServiceReference ref = context.getServiceReference(LogService.class.getName());
        log = (LogService) context.getService(ref);
    }
}
