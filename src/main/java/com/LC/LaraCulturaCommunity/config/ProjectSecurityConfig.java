package com.LC.LaraCulturaCommunity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class ProjectSecurityConfig  {


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

            http.csrf().ignoringAntMatchers("/saveMsg")
            .ignoringAntMatchers("/activity/**")
            .ignoringAntMatchers("/addEpisode/**")
            .ignoringAntMatchers("/updateProfile")
            .ignoringAntMatchers("/article_display")
            
            
            .and()
            .authorizeRequests()
            .mvcMatchers("/updateProfile").authenticated()
                .mvcMatchers("/displayProfil").authenticated()
                .mvcMatchers("/article_display").authenticated()
                .mvcMatchers("/dashboard").authenticated()
                .mvcMatchers("/addEpisode").authenticated()
                .mvcMatchers("/activity").permitAll()
                .mvcMatchers("/element").permitAll()
                .mvcMatchers("/contact").permitAll()
                .mvcMatchers("/msgs").hasRole("ADMIN")
                // .mvcMatchers("/activity/newArticle").hasRole("ADMIN")
                .mvcMatchers("/saveMsg").permitAll()
                .mvcMatchers("/speakes").permitAll()
                .mvcMatchers("/generic").permitAll()
                .mvcMatchers("/activities").permitAll()
                .mvcMatchers("/activity/**").permitAll()
                .mvcMatchers("/messages").authenticated()
                .mvcMatchers("/about").permitAll()
                .mvcMatchers("/login").permitAll()
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll()
                .and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
                .and().httpBasic();

            return http.build();
    }

    // @Bean
    // public InMemoryUserDetailsManager userDetailsService() {

    //     UserDetails admin = User.withDefaultPasswordEncoder()
    //             .username("user")
    //             .password("12345")
    //             .roles("USER")
    //             .build();
    //     UserDetails user = User.withDefaultPasswordEncoder()
    //             .username("admin")
    //             .password("54321")
    //             .roles("USER","ADMIN")
    //             .build();
    //     return new InMemoryUserDetailsManager(user, admin);
    // }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
