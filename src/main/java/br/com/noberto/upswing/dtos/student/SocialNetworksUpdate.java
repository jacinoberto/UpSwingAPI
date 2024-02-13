package br.com.noberto.upswing.dtos.student;

import br.com.noberto.upswing.models.SocialNetworks;

public record SocialNetworksUpdate(
        String socialOne,
        String socialTwo,
        String socialThree,
        String socialFour
) {
    public SocialNetworksUpdate(SocialNetworks socialNetworks) {
        this(
                socialNetworks.getSocialOne(),
                socialNetworks.getSocialTwo(),
                socialNetworks.getSocialThree(),
                socialNetworks.getSocialFour()
        );
    }
}
