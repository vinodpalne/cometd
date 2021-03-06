/*
 * Copyright (c) 2008-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cometd.examples.spring.boot;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.cometd.annotation.AnnotationCometDServlet;
import org.cometd.examples.ChatService;
import org.cometd.examples.CometDDemoServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;

@SpringBootApplication
public class CometDApplication implements ServletContextInitializer {
    public static void main(String[] args) {
        SpringApplication.run(CometDApplication.class, args);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        ServletRegistration.Dynamic cometdServlet = servletContext.addServlet("cometd", AnnotationCometDServlet.class);
        cometdServlet.addMapping("/cometd/*");
        cometdServlet.setAsyncSupported(true);
        cometdServlet.setLoadOnStartup(1);
        cometdServlet.setInitParameter("services", ChatService.class.getName());

        ServletRegistration.Dynamic demoServlet = servletContext.addServlet("demo", CometDDemoServlet.class);
        demoServlet.addMapping("/demo");
        demoServlet.setAsyncSupported(true);
        demoServlet.setLoadOnStartup(2);
    }
}
