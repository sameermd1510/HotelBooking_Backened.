package com.javaProject.lakesidehotel.controller;

import com.javaProject.lakesidehotel.exception.RoleAlreadyExistException;
import com.javaProject.lakesidehotel.model.Role;
import com.javaProject.lakesidehotel.model.User;
import com.javaProject.lakesidehotel.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.FOUND;

/**
 * @author Mohammad Sameer
 */

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final IRoleService roleService;

    @GetMapping("/all-roles")
    public ResponseEntity<List<Role>> getAllRoles(){
        return new ResponseEntity<>(roleService.getRoles(), FOUND);
    }

    @PostMapping("/create-new-role")
    public ResponseEntity<String> createRole(@RequestBody Role theRole){
        try{
            roleService.createRole(theRole);
            return ResponseEntity.ok("New role created successfully!");
        }catch(RoleAlreadyExistException re){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(re.getMessage());

        }
    }
    @DeleteMapping("/delete/{roleId}")
    public ResponseEntity<String> deleteRole(@PathVariable("roleId") Long roleId){
        try {
            roleService.deleteRole(roleId);
            return ResponseEntity.ok("Role deleted successfully!");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User Not Found");
        }
    }

    // will work on this in Future.
    @PostMapping("/remove-all-users-from-role/{roleId}")
    public Role removeAllUsersFromRole(@PathVariable("roleId") Long roleId){
        return roleService.removeAllUsersFromRole(roleId);
    }

    // will work on this in Future.
    @PostMapping("/remove-user-from-role")
    public User removeUserFromRole(
            @RequestParam("userId") Long userId,
            @RequestParam("roleId") Long roleId){
        return roleService.removeUserFromRole(userId, roleId);
    }
    // will work on this in Future.
    @PostMapping("/assign-user-to-role")
    public User assignUserToRole(
            @RequestParam("userId") Long userId,
            @RequestParam("roleId") Long roleId){
        return roleService.assignRoleToUser(userId, roleId);
    }
}
