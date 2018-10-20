package org.kazi.authorizer.service;

import org.kazi.authorizer.model.Account;
import org.kazi.authorizer.model.Client;
import org.kazi.authorizer.model.GrantType;
import org.kazi.authorizer.model.Role;
import org.kazi.authorizer.repository.AccountRepository;
import org.kazi.authorizer.repository.ClientRepository;
import org.kazi.authorizer.repository.GrantTypeRepository;
import org.kazi.authorizer.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DataLoader {

    @Autowired
    DataLoader(AccountRepository accountRepo,
               RoleRepository roleRepo,
               ClientRepository clientRepo,
               GrantTypeRepository grantTypeRepo,
               BCryptPasswordEncoder encoder) {
        Role guest = new Role("GUEST");
        roleRepo.save(guest);
        Role editor = new Role("EDITOR");
        roleRepo.save(editor);
        Role auditor = new Role("AUDITOR");
        roleRepo.save(auditor);
        Role manager = new Role("MANAGER");
        roleRepo.save(manager);

        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Set<Role> roles = new HashSet<Role>();
        roles.add(guest);
        Account guestAccount = new Account("g1", encoder.encode("password"), "g1@email.com", roles);
        accountRepo.save(guestAccount);
        roles.clear();
        roles.add(editor);
        Account editorAccount = new Account("e1", encoder.encode("password"), "e1@email.com", roles);
        accountRepo.save(editorAccount);
        roles.clear();
        roles.add(auditor);
        Account auditorAccount = new Account("a1", encoder.encode("password"), "a1@email.com", roles);
        accountRepo.save(auditorAccount);
        roles.clear();
        roles.add(manager);
        Account managerAccount = new Account("m1", encoder.encode("password"), "m1@email.com", roles);
        accountRepo.save(managerAccount);

        GrantType authorizationCode = new GrantType("authorization_code");
        grantTypeRepo.save(authorizationCode);
        GrantType password = new GrantType("password");
        grantTypeRepo.save(password);
        GrantType refreshToken = new GrantType("refresh_token");
        grantTypeRepo.save(refreshToken);
        GrantType clientCredentials = new GrantType("client_credentials");
        grantTypeRepo.save(clientCredentials);

        Set<GrantType> grantTypes = new HashSet<>();
        grantTypes.add(password);
        //grantTypes.add(clientCredentials);

        Client client = new Client("the-first-client", encoder.encode("secret"), null, grantTypes);
        clientRepo.save(client);
        /*Client editorClient = new Client("client-2-editor", encoder.encode("secret"), null, grantTypes);
        clientRepo.save(editorClient);
        Client auditorClient = new Client("client-3-auditor", encoder.encode("secret"), null, grantTypes);
        clientRepo.save(auditorClient);*/

        String cid = clientRepo.findById("the-first-client").getId();
        System.out.println(" --- --- --- --- --- " + cid + " --- --- --- --- --- ---");

    }
}
