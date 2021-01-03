package org.saleslist.service.datajpa;

import org.saleslist.service.AbstractProductServiceTest;
import org.springframework.test.context.ActiveProfiles;

import static org.saleslist.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
public class DataJpaProductServiceTest extends AbstractProductServiceTest {
}
