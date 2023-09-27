package com.kCalControl.controller;

import com.kCalControl.dto.creation.NewUserDTO;
import com.kCalControl.dto.search.SearchParamsDTO;
import com.kCalControl.model.Role;
import jakarta.annotation.security.RolesAllowed;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.kCalControl.model.Role.ADMIN_ID;


@RestController
@RequestMapping("api")
@RolesAllowed("ADMIN")
public interface AdminController {

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("admin/addUser")
    ResponseEntity<Void> createAdminUser(@RequestBody NewUserDTO dto);

    //TODO re-evaluate pagination method
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("admin/listUser")
    String getUsersList(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int pageSize); //these values have lower priority than the JS ones

    //TODO fix pagination: page entity adds the search result to the existing pageable
    //TODO check parameters of JS functions passed to the server. Probably I have to include searchParams info into requests
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("admin/listUser")
    String searchUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int pageSize,
                       SearchParamsDTO dto);
}
