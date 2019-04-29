/*
  File: WebConfig

  Copyright 2019, Steven W. Anderson
*/
package net.swahome.example.springmvc.funtime.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.view.InternalResourceViewResolver
import org.springframework.web.servlet.view.JstlView

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = ["net.swahome.example.springmvc.funtime.web"])
open class WebConfig {
    @Bean
    open fun viewResolver(): ViewResolver {
        val bean = InternalResourceViewResolver()

        bean.setViewClass(JstlView::class.java)
        bean.setPrefix("/WEB-INF/view/")
        bean.setSuffix(".jsp")

        return bean
    }
}
