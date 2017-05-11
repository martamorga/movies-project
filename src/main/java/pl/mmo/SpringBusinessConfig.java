package pl.mmo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackages = { "pl.mmo" }, excludeFilters = {
        @Filter(type = FilterType.REGEX, pattern = "pl\\.mmo\\.web\\..*") })
public class SpringBusinessConfig {

}
