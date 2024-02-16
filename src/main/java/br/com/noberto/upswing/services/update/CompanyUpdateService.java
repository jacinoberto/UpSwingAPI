package br.com.noberto.upswing.services.update;

import br.com.noberto.upswing.dtos.student.SocialNetworksUpdate;
import br.com.noberto.upswing.models.Company;
import br.com.noberto.upswing.models.SocialNetworks;
import br.com.noberto.upswing.models.Student;
import br.com.noberto.upswing.repositories.CompanyRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CompanyUpdateService {
    private final CompanyRepository companyRepository;

    public CompanyUpdateService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company companyUpdateSocialNetworks(String companyId, SocialNetworksUpdate socialNetworksUpdate){
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityExistsException("Empresa não encontrada!"));
        SocialNetworks socialNetworks = company.getSocialNetworks();

        socialNetworks.setSocialOne(socialNetworksUpdate.socialOne());
        socialNetworks.setSocialTwo(socialNetworksUpdate.socialTwo());
        socialNetworks.setSocialThree(socialNetworksUpdate.socialThree());
        socialNetworks.setSocialFour(socialNetworksUpdate.socialFour());

        company.setSocialNetworks(socialNetworks);
        return company;
    }
}
