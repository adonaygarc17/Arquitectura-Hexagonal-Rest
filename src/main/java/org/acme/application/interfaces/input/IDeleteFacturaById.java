package org.acme.application.interfaces.input;

import jakarta.ws.rs.core.Response;

import java.util.UUID;

public interface IDeleteFacturaById {

    String deleteFacturaById(UUID id);
}
