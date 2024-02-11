package br.com.noberto.upswing.util.verifications;

import br.com.noberto.upswing.dtos.address.AddressRequest;
import br.com.noberto.upswing.models.Address;
import br.com.noberto.upswing.models.BusinessArea;

import java.util.UUID;

public interface ICheckObjectStrategy {

    Address checkZipCode(AddressRequest address);
    BusinessArea checkBusinessArea(UUID businessAreaId);
}
