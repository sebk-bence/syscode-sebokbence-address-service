package com.sc.sebokbence.scaddressservice.controller;

import com.sc.sebokbence.scaddressservice.exception.*;
import com.sc.sebokbence.scaddressservice.model.*;
import com.sc.sebokbence.scaddressservice.repository.*;
import java.util.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
  private final StaticAddressProvider staticAddressProvider;

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize("hasRole('ADDRESS_READ')")
  Address getAddressById(@PathVariable String id) {
    Optional<Address> address = staticAddressProvider.getAddress(UUID.fromString(id));
    if(address.isEmpty()) {
      throw new NotFoundException();
    }
    return address.get();
  }
}
