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
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.view.InternalResourceViewResolver
import org.springframework.web.servlet.view.JstlView
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.net.URL


@EnableWebMvc
@EnableSwagger2
@Configuration
@ComponentScan(basePackages = ["net.swahome.example.springmvc.funtime.web", "net.swahome.example.springmvc.funtime.rest"])
open class WebConfig: WebMvcConfigurerAdapter() {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/")

        registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/")

        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/")
    }

    @Bean
    open fun viewResolver(): ViewResolver {
        val bean = InternalResourceViewResolver()

        bean.setViewClass(JstlView::class.java)
        bean.setPrefix("/WEB-INF/view/")
        bean.setSuffix(".jsp")

        return bean
    }

    @Bean
    open fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .directModelSubstitute(URL::class.java, String::class.java)
            .select()
            .apis(RequestHandlerSelectors.basePackage("net.swahome.example.springmvc.funtime.rest"))
            .paths(PathSelectors.any())
            .build()
    }
}
