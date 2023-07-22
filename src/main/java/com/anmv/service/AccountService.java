package com.anmv.service;

import com.anmv.createForm.CreatingAccountForm;
import com.anmv.entity.Account;
import com.anmv.repository.IAccountRepository;
import com.anmv.specification.AccountSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService{
    @Autowired
    private IAccountRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Account> getAllAccounts(String search, String departmentName) {
        Specification<Account> specification = AccountSpecification.buildWhere(search, departmentName);
        return repository.findAll(specification);
    }

    @Override
    public void createNewAccount(CreatingAccountForm form) {
        TypeMap<CreatingAccountForm, Account> typeMap = modelMapper.getTypeMap(CreatingAccountForm.class, Account.class);

        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<CreatingAccountForm, Account>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }

        Account account = modelMapper.map(form, Account.class);

        repository.save(account);
    }


}
