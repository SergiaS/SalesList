package org.saleslist.service.jdbc;

import org.saleslist.service.AbstractUserServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static org.saleslist.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {
}
