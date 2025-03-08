package com.apidemo.controller;

import com.apidemo.entity.Regstration;
import com.apidemo.payload.RegistrationDto;
import com.apidemo.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createRegistration(@Valid @RequestBody RegistrationDto registrationDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        RegistrationDto registration = registrationService.createRegistration(registrationDto);
        return ResponseEntity.status(HttpStatus.CREATED).header("Custom-Header", "Value").body(registration);
    }

    @DeleteMapping(value = "/delete", produces = "application/json")
    public ResponseEntity<String> deleteRegistration(@RequestParam("id") Long id) {
        registrationService.deleteRegistration(id);
        return new ResponseEntity<>("Registration deleted successfully. " + id, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> updateRegistration(@PathVariable Long id, @RequestBody RegistrationDto registrationDto) {
        registrationService.updateRegistration(id, registrationDto);
        return ResponseEntity.ok("Registration updated successfully with ID: " + id);
    }

    @GetMapping(value = "/get", produces = "application/json")
    public ResponseEntity<List<RegistrationDto>> getAllRegistration(
            @RequestParam(name = "pageNo", required = false, defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize", required = false, defaultValue = "3") int pageSize,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "sortDir", required = false, defaultValue = "asc") String sortDir) {
        List<RegistrationDto> registrationsDto = registrationService.getAllRegistrations(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(registrationsDto, HttpStatus.OK);
    }

    @GetMapping(value = "/byId", produces = "application/json")
    public ResponseEntity<Regstration> getRegistration(@RequestParam("id") long id) {
        Regstration registrationById = registrationService.getRegistrationById(id);
        return new ResponseEntity<>(registrationById, HttpStatus.OK);
    }
}
