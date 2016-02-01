/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.muenchen.kfz.zulassungsservice.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 *
 * @author robert.jasny
 */
@Component
public class HelloBean {

    public HelloBean() {

    }

    @Bean
    String hello() {
        return "I'm Hello Spring bean!";
    }

}
