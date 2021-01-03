package org.saleslist.service.datajpa;

import org.saleslist.service.AbstractUserServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static org.saleslist.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserServiceTest {
}
