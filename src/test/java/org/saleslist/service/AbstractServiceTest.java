package org.saleslist.service;

import org.junit.AfterClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.ExternalResource;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.saleslist.ActiveDbProfileResolver;
import org.saleslist.TimingRules;
import org.slf4j.Logger;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Test for use with HSQLDB only!
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/hsqldb_populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles(resolver = ActiveDbProfileResolver.class)
abstract public class AbstractServiceTest {

    @ClassRule
    public static ExternalResource summary = TimingRules.SUMMARY;

    @Rule
    public final Stopwatch stopwatch = TimingRules.STOPWATCH;
}
