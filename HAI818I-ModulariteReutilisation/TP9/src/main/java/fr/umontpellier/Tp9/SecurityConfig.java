package fr.umontpellier.Tp9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/login*").permitAll()
                .antMatchers("/register*").permitAll()
                .antMatchers("/doRegister").permitAll()
                .antMatchers("/userConfirm").permitAll()
                .antMatchers("/static/css/**", "/static/js/**", "/images/**").permitAll()
                .antMatchers("/index*").permitAll()
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/doLogin")
                .failureUrl("/login?error=true").permitAll()
                .defaultSuccessUrl("/", true)

                .and()
                .rememberMe()
                .key("cleSuperSecrete")
                .tokenRepository(tokenRepository())

                .and()
                .logout()
                .logoutSuccessUrl("/login?logout=true")
                .logoutRequestMatcher(new AntPathRequestMatcher("/doLogout", "GET"))
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        // auth.inMemoryAuthentication().withUser("bob").password(passwordEncoder().encode("password")).roles("USER");
        auth.jdbcAuthentication().passwordEncoder(passwordEncoder()).dataSource(dataSource)
        /*.withUser("admin").password(passwordEncoder().encode("admin")).disabled(false).roles("USER", "ADMIN")*/;
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository tokenRepository()
    {
        JdbcTokenRepositoryImpl token = new JdbcTokenRepositoryImpl();
        token.setDataSource(dataSource);
        return token;
    }
}
