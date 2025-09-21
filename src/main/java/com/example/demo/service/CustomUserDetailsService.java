package com.example.demo.service;

import com.example.demo.exception.KorisnikNotFoundException;
import com.example.demo.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private KorisnikRepository korisnikRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return korisnikRepository.findByKorisnickoIme(username)
                .map(korisnik -> {
                    return User.builder()
                            .username(korisnik.getKorisnickoIme())
                            .password(korisnik.getSifra())
                            .authorities(korisnik.getTipKorisnika().getNaziv())
                            .build();
                })
                .orElseThrow(() -> {
                    throw new KorisnikNotFoundException("Korisnik nije pronadjen");
                });
    }
}
