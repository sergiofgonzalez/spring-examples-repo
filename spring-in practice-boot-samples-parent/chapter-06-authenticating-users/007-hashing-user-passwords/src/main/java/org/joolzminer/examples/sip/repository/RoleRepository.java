package org.joolzminer.examples.sip.repository;

import org.joolzminer.examples.sip.domain.Role;
import org.springframework.data.repository.Repository;

public interface RoleRepository extends Repository<Role, Long> {
	Role findByName(String name);
}
