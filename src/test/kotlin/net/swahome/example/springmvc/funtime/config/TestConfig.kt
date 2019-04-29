/*
  File: TestConfig

  Copyright 2019, Steven W. Anderson
*/
package net.swahome.example.springmvc.funtime.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["net.swahome.example.springmvc.funtime.dao",
    "net.swahome.example.springmvc.funtime.service"])
open class TestConfig {
}
