/*
  File: ServletApplicationInitializer

  Copyright 2019, Steven W. Anderson
*/
package net.swahome.example.springmvc.funtime.config

import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet
import javax.servlet.ServletContext

open class ServletApplicationInitializer: WebApplicationInitializer {
    override fun onStartup(servletContext: ServletContext) {
        val rootContext = AnnotationConfigWebApplicationContext()
        rootContext.register(RootConfig::class.java)

        servletContext.addListener(ContextLoaderListener(rootContext))

        val dispatcherContext = AnnotationConfigWebApplicationContext()
        dispatcherContext.register(WebConfig::class.java)

        val dispatcher = servletContext.addServlet("dispatcher", DispatcherServlet(dispatcherContext))
        dispatcher.setLoadOnStartup(1)
        dispatcher.addMapping("/")
    }

}
