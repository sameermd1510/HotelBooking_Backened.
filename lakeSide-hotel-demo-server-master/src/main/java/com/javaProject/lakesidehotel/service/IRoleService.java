package com.javaProject.lakesidehotel.service;

import com.javaProject.lakesidehotel.model.Role;
import com.javaProject.lakesidehotel.model.User;

import java.util.List;

/**
 * @author Mohammad Sameer
 */

public interface IRoleService {
    List<Role> getRoles();
    Role createRole(Role theRole);

    void deleteRole(Long id);
    Role findByName(String name);

    User removeUserFromRole(Long userId, Long roleId);
    User assignRoleToUser(Long userId, Long roleId);
    Role removeAllUsersFromRole(Long roleId);
}
