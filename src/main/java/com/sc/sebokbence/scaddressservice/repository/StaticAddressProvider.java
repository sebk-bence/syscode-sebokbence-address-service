package com.sc.sebokbence.scaddressservice.repository;

import com.sc.sebokbence.scaddressservice.model.*;
import java.util.*;
import org.springframework.stereotype.*;

@Component
public class StaticAddressProvider {
  private final List<Address> data = List.of(
      new Address(UUID.fromString("eb281107-c753-44c5-a64b-9cbdf5705f4e"), "Budapest, Street 1"),
      new Address(UUID.fromString("ab8e72e4-7118-46c8-b607-5a555313ef93"), "Budapest, Street 2"),
      new Address(UUID.fromString("2ae8d80c-f532-4d55-8508-a56f9c9f0be5"), "Budapest, Street 3")
  );

  public Optional<Address> getAddress(UUID id) {
    return data
        .stream()
        .filter(address -> address.getId().equals(id))
        .findFirst();
  }
}
