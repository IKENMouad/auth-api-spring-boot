package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.demo.dto.request.PermissionRequest;
import com.example.demo.models.Permission;
import com.example.demo.service.PermissionService;
import com.example.demo.utils.ErrorUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "", maxAge = 3600)
@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class PermissionController {

  private final PermissionService permissionService;

  @GetMapping(value = { "", "/" }, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> getPermissions() throws Exception {
    Map<String, Object> mapItems = new HashMap<String, Object>();
    try {
      List<Permission> permissions = permissionService.getPermissions();
      mapItems.put("items", permissions);
      mapItems.put("totalItems", permissions.size());
    } catch (Exception e) {
      mapItems.put("status", "failure :" + e.getMessage());
    }
    String jsonReponse = new ObjectMapper().writeValueAsString(mapItems);
    return new ResponseEntity<String>(jsonReponse, HttpStatus.OK);
  }

  @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> createPermission(@Valid @RequestBody PermissionRequest permissionRequest,
      BindingResult result)
      throws Exception {
    Map<String, Object> mapItems = new HashMap<String, Object>();
    if (result.hasErrors()) {
      String errors = ErrorUtils.customErrors(result.getAllErrors());
      mapItems.put("status", "failure");
      mapItems.put("errors", errors);
    } else {
      try {
        Permission permission = permissionService.createPermission(permissionRequest);
        mapItems.put("permission", permission);
        mapItems.put("status", permission == null ? "failure" : "success");
      } catch (Exception e) {
        mapItems.put("status", "failure :" + e.getMessage());
      }
    }
    String jsonReponse = new ObjectMapper().writeValueAsString(mapItems);
    return new ResponseEntity<String>(jsonReponse, HttpStatus.OK);
  }

}
