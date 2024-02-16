package br.com.noberto.upswing.util.verifications;

import br.com.noberto.upswing.dtos.address.AddressRequest;
import br.com.noberto.upswing.models.Address;
import br.com.noberto.upswing.models.BusinessArea;
import br.com.noberto.upswing.models.Company;
import br.com.noberto.upswing.repositories.*;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CompanyCheck extends AbstractCheckObject{

    public CompanyCheck(ZipCodeRepository zipCodeRepository, BusinessAreaRepository businessAreaRepository, StudentRepository
            studentRepository, ClassRepository classRepository, CompanyRepository companyRepository, CourseRepository courseRepository, EntityManager entityManager) {
        super(zipCodeRepository, businessAreaRepository, studentRepository, classRepository, companyRepository, courseRepository, entityManager);
    }

    @Override
    public Address checkZipCode(AddressRequest address) {
        return super.checkZipCode(address);
    }

    @Override
    public BusinessArea checkBusinessArea(UUID businessAreaId) {
        return super.checkBusinessArea(businessAreaId);
    }

    @Override
    public Company checkCompany(String companyId) {
        return super.checkCompany(companyId);
    }
}
